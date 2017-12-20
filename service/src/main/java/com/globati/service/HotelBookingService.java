package com.globati.service;


import com.globati.dbmodel.Employee;
import com.globati.dbmodel.FlightBooking;
import com.globati.dbmodel.HotelBooking;
import com.globati.enums.GlobatiPaymentStatus;
import com.globati.enums.TicketPaidStatus;
import com.globati.repository.FlightBookingRepository;
import com.globati.repository.HotelBookingRepository;
import com.globati.s3.FlightBookingRow;
import com.globati.s3.HotelBookingRow;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.ImageHandler;
import com.globati.utildb.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HotelBookingService {


    private static final Logger log = LogManager.getLogger(HotelBookingService.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    HotelBookingRepository hotelBookingRepository;

    @Scheduled(cron = "0 30 4 * * ?")
    public boolean getHotelBookingsFromS3() throws Exception {
        log.info("** GLOBATI SCHEDULED TASK INITIALIZING ... GETTING flightbookings.csv from S3");

        try {
            List<HotelBookingRow> bookings = ImageHandler.getHotelBookingRowsFromFile(ImageHandler.getHotelBookingsFromS3());

            for (HotelBookingRow booking : bookings) {
                createHotelBooking(
                        booking.getDateBooked(),
                        booking.getTimeBooked(), booking.getPaidStatus(), booking.getCostOfTicket(),
                        booking.getGlobatiComission(), booking.getFlightPlan(),
                        booking.getDepartureDate(), booking.getReturnDate(),
                        booking.getGlobatiMaker(),
                        booking.getCompanyBookedWith()
                );
            }
            ImageHandler.deleteBookingFileFromS3();
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getHotelBookingsFromS3(): ");
            e.printStackTrace();
            String message = "There was an error when running getHotelBookingsFromS3(): This method is called automatically from the server to get data from " +
                    "The globati bookings spread sheets on the google drive. It is possible that a google spreadsheet was deleted, or " +
                    "a different server error ocurred. Therefore it cannot be garunteed that booking data has been persisted " +
                    "properly in the database. <br><br><br> If the flightbookings.csv file is not created properly, that could be a reason that this error was thrown. Check date is correct and no extra symbols with numbers."+"<br> <br> <br>"+"Here is the error message : "+"<br>"+"<br>"+"<br>"+
                    org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(e.fillInStackTrace());
            SendMail.sendCustomMailToGlobatiStaff("daniel@globati.com", message);
            throw new ServiceException("An exception occured when getting the flight bookings file from S3");
        }
        return true;
    }

    public HotelBooking createHotelBooking(

            Date dateBooked,
            String timeBooked, String paidStatus,
            Double costOfTicket, Double globatiCommission, String flightPlan,
            Date departureDate, Date returnDate, String globatiMarker,
            String companyBookedWith) throws ServiceException {

        try{
            Long id = null;
            Employee employee = null;
            if(globatiMarker.length()>6) {
                id = getEmployeeIdFromMarker(globatiMarker);
                employee = employeeService.getEmployeeById(id);
            }
            TicketPaidStatus ticketPaidStatus = TicketPaidStatus.valueOf(paidStatus.toUpperCase());
            HotelBooking hotelBooking = new HotelBooking(dateBooked, timeBooked,
                    ticketPaidStatus, costOfTicket, globatiCommission, calculateEmployeeComission(globatiCommission), flightPlan,
                    departureDate, returnDate, globatiMarker, companyBookedWith,
                    employee
            );

            return hotelBookingRepository.save(hotelBooking);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: createFlightBooking(): ");
            e.printStackTrace();
            throw new ServiceException("An exception occured when creating a new FlightBooking in the globati DB");
        }
    }

    public HotelBooking getHotelBookingById(Long hotelBookingId) throws ServiceException {
        try{
            return hotelBookingRepository.findOne(hotelBookingId);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getHotelBookingById(): "+hotelBookingId);
            e.printStackTrace();
            throw new ServiceException("An exception occured when retrieving a FlightBooking by id");
        }
    }


    /**
     * Returns all flight bookings for an employee, whether the transaction was paid or cancelled.
     * @param employeeId
     * @return
     * @throws ServiceException
     */

    public List<HotelBooking> getHotelBookingsByEmployeeId(Long employeeId) throws ServiceException {
        try{
            return hotelBookingRepository.getHotelBookingByEmployeeId(employeeId);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getFlightBookingsByEmployeeId(): ");
            e.printStackTrace();
            throw new ServiceException("An exception occured when retrieving flight information from the google drive");
        }
    }

    public Long getEmployeeIdFromMarker(String marker) {
        return Long.valueOf(marker.substring(7));
    }


    public Double calculateEmployeeComission(Double globaticomission){
        return globaticomission/2;
    }

    public List<HotelBooking> getHotelBookingsByEmployeeIdAndPaidStatus(Long employeeId) throws ServiceException {
        try{
            return hotelBookingRepository.getFlightBookingByEmployeeIdAndPaidStatus(
                    employeeId, TicketPaidStatus.PAID, GlobatiPaymentStatus.NOT_PAID);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: calculateEmployeeComission(): ");
            e.printStackTrace();
            throw new ServiceException("An exception occured when retrieving FlightBooking");
        }

    }

}
