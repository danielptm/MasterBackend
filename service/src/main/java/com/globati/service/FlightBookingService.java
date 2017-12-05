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

    public FlightBooking createFlightBooking(
            Long employeeId, String timeBooked, String paidStatus,
            Double costOfTicket, Double globatiCommission, String flightPlan,
            Integer numberOfPeople, Date departureDate, Date returnDate, String globatiMarker,
            String companyBookedWith) throws ServiceException {
        try{
            Employee employee = employeeService.getEmployeeById(employeeId);

            TicketPaidStatus ticketPaidStatus = TicketPaidStatus.valueOf(paidStatus);
            FlightBooking flightBooking = new FlightBooking(timeBooked,
                    ticketPaidStatus, costOfTicket, globatiCommission, flightPlan,
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
}
