import com.globati.dbmodel.Employee;
import com.globati.google_sheets.FlightBookingRow;
import com.globati.service.EmployeeService;
import com.globati.service.FlightBookingService;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TestFlightBookingService {

    @Autowired
    FlightBookingService flightBookingService;

    @Autowired
    EmployeeService employeeService;

    FlightBookingRow row;

    String uid;
    Employee employee;

    @Before
    public void setup() throws ServiceException, UserNameIsNotUniqueException, UserDoesNotExistException, IllegalUserNameException {

        uid = UUID.randomUUID().toString();

        employee = employeeService.createEmployee(
                "hi",
                "daniel@me.com",
                        uid,
                "asdf",
                23.23,
                23,
                "asdf",
                "2308",
                "seattle",
                "usa"
        );

        String time = "14:34";
        String paidStatus = "PAID";
        String cost = "$726.22";
        String comission = "$5.96";
        String flightPlan = "Stockholm (STO) - Seattle (SEA)";
        String passengers = "1";
        String departureDate = "2017.12.19";
        String returnDate = "2017.12.28";
        String marker = "153839";
        String company = "Kissandfly";

        row = new FlightBookingRow(
                time, paidStatus, cost, comission, flightPlan, passengers,
                departureDate, returnDate, marker, company
        );

        row.setEmployeeId(employee.getId());

        flightBookingService.createFlightBooking(
                row.getEmployeeId(),
                row.getTimeBooked(),
                row.getPaidStatus(),
                row.getCostOfTicket(),
                row.getGlobatiCommission(),
                row.getFlightPlan(),
                row.getNumberOfPeople(),
                row.getDepartureDate(),
                row.getReturnDate(),
                row.getGlobatiMarker(),
                row.getCompanyBookedWith()
        );
    }


    @Test
    public void testGetFlightBooking() throws ServiceException {
        Assert.assertEquals(1, flightBookingService.getFlightBookingsByEmployeeId(employee.getId()).size());
    }
}
