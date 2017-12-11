package com.globati.service;

import com.globati.dbmodel.Employee;
import com.globati.dbmodel.FlightBooking;
import com.globati.enums.TicketPaidStatus;
import com.globati.google_sheets.FlightBookingRow;
import com.globati.repository.FlightBookingRepository;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.GoogleSheets;
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

    @Scheduled(cron = "0 30 18 * * ?")
    public boolean getDataFromGoogleDriveAndCreateBookings() throws Exception {

        List<FlightBookingRow> bookings = GoogleSheets.getGoogleDocument();

        for(FlightBookingRow booking: bookings){
            createFlightBooking(
                    booking.getDateBooked(),
                    booking.getTimeBooked(), booking.getPaidStatus(), booking.getCostOfTicket(),
                    booking.getGlobatiCommission(), booking.getFlightPlan(),
                    booking.getNumberOfPeople(), booking.getDepartureDate(), booking.getReturnDate(),
                    booking.getGlobatiMarker(),
                    booking.getCompanyBookedWith()
            );
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



    public List<FlightBooking> getFlightBookingsByEmployeeId(Long employeeId) throws ServiceException {
        try{
            return flightBookingRepository.getFlightBookingByEmployeeId(employeeId);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getFlightBookingsByEmployeeId(): ");
            e.printStackTrace();
            throw new ServiceException("An exception occured when retrieving flight information from the google drive");
        }
    }

    public List<FlightBookingRow> getFlightBookingRowsFromGoogleDoc() throws ServiceException {
        List<FlightBookingRow> flightBookingRows = null;
        try {
            return GoogleSheets.getGoogleDocument();
        } catch (Exception e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getFlightBookingRowsFromGoogleDoc(): ");
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

}
