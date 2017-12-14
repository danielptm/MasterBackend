package com.globati.service;

import com.globati.dbmodel.Employee;
import com.globati.dbmodel.FlightBooking;
import com.globati.enums.TicketPaidStatus;
import com.globati.google_sheets.FlightBookingRow;
import com.globati.repository.FlightBookingRepository;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.GoogleSheets;
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
public class FlightBookingService {

    private static final Logger log = LogManager.getLogger(FlightBookingService.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    FlightBookingRepository flightBookingRepository;

    @Scheduled(cron = "0 29 11 * * ?")
    public boolean getFlightBookingFroms3() throws Exception {
        log.info("** GLOBATI SCHEDULED TASK INITIALIZING ... GETTING flightbookings.csv from S3");

        try {
            List<FlightBookingRow> bookings = ImageHandler.getFlightBookingRowsFromFile(ImageHandler.getFlightBookingsFromS3());

            for (FlightBookingRow booking : bookings) {
                createFlightBooking(
                        booking.getDateBooked(),
                        booking.getTimeBooked(), booking.getPaidStatus(), booking.getCostOfTicket(),
                        booking.getGlobatiCommission(), booking.getFlightPlan(),
                        booking.getNumberOfPeople(), booking.getDepartureDate(), booking.getReturnDate(),
                        booking.getGlobatiMarker(),
                        booking.getCompanyBookedWith()
                );
            }
            ImageHandler.deleteBookingFileFromS3();
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getFlightBookingFroms3(): ");
            e.printStackTrace();
            String message = "There was an error when running getFlightBookingFroms3(): This method is called automatically from the server to get data from " +
                    "The globati bookings spread sheets on the google drive. It is possible that a google spreadsheet was deleted, or " +
                    "a different server error ocurred. Therefore it cannot be garunteed that booking data has been persisted " +
                    "properly in the database. <br><br><br> If the flightbookings.csv file is not created properly, that could be a reason that this error iwas thrown"+"<br> <br> <br>"+"Here is the error message : "+"<br>"+"<br>"+"<br>"+
                    org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(e.fillInStackTrace());
            SendMail.sendCustomMailToGlobatiStaff("daniel@globati.com", message);
            throw new ServiceException("An exception occured when getting the flight bookings file from S3");
        }
        return true;
    }

    public FlightBooking createFlightBooking(

            Date dateBooked,
            String timeBooked, String paidStatus,
            Double costOfTicket, Double globatiCommission, String flightPlan,
            Integer numberOfPeople, Date departureDate, Date returnDate, String globatiMarker,
            String companyBookedWith) throws ServiceException {

        try{
            Long id = null;
            Employee employee = null;
            if(globatiMarker.length()>6) {
                id = getEmployeeIdFromMarker(globatiMarker);
                employee = employeeService.getEmployeeById(id);
            }
            TicketPaidStatus ticketPaidStatus = TicketPaidStatus.valueOf(paidStatus.toUpperCase());
            FlightBooking flightBooking = new FlightBooking(dateBooked, timeBooked,
                    ticketPaidStatus, costOfTicket, globatiCommission, calculateEmployeeComission(globatiCommission), flightPlan,
                    numberOfPeople, departureDate, returnDate, globatiMarker, companyBookedWith,
                    employee
                    );

            return flightBookingRepository.save(flightBooking);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: createFlightBooking(): ");
            e.printStackTrace();
            throw new ServiceException("An exception occured when creating a new FlightBooking in the globati DB");
        }
    }

    public FlightBooking getFlightBookingById(Long flightBookingId) throws ServiceException {
        try{
            return flightBookingRepository.findOne(flightBookingId);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getFlightBookingById(): "+flightBookingId);
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

    public List<FlightBooking> getFlightBookingsByEmployeeId(Long employeeId) throws ServiceException {
        try{
            return flightBookingRepository.getFlightBookingByEmployeeId(employeeId);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getFlightBookingsByEmployeeId(): ");
            e.printStackTrace();
            throw new ServiceException("An exception occured when retrieving flight information from the google drive");
        }
    }

    public Long getEmployeeIdFromMarker(String marker) {
        System.out.println(marker);
        return Long.valueOf(marker.substring(7));
    }


    public Double calculateEmployeeComission(Double globaticomission){
        return globaticomission/2;
    }

    public List<FlightBooking> getFlightBookingsByEmployeeIdAndPaidStatus(Long employeeId) throws ServiceException {
        try{
            return flightBookingRepository.getFlightBookingByEmployeeIdAndPaidStatus(employeeId, TicketPaidStatus.PAID);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getFlightBookingsByEmployeeId(): ");
            e.printStackTrace();
            throw new ServiceException("An exception occured when retrieving FlightBooking");
        }

    }

}
