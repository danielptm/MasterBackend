import com.globati.dbmodel.Employee;
import com.globati.dbmodel.FlightBooking;
import com.globati.google_sheets.FlightBookingRow;
import com.globati.service.EmployeeService;
import com.globati.service.FlightBookingService;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
    FlightBookingRow row2;

    FlightBooking flightBooking1;
    FlightBooking flightBooking2;

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
        String marker = "153839."+employee.getId();
        String markerWithOutId = "153839";
        String company = "Kissandfly";

        row = new FlightBookingRow(
                time, paidStatus, cost, comission, flightPlan, passengers,
                departureDate, returnDate, marker, company
        );

        row2 = new FlightBookingRow(
                time, paidStatus, cost, comission, flightPlan, passengers,
                departureDate, returnDate, markerWithOutId, company
        );


        //FlightBooking with employeeId
        flightBooking1 = flightBookingService.createFlightBooking(
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


        //FlightBooking with no employeeId
        flightBooking2 = flightBookingService.createFlightBooking(
                row2.getTimeBooked(),
                row2.getPaidStatus(),
                row2.getCostOfTicket(),
                row2.getGlobatiCommission(),
                row2.getFlightPlan(),
                row2.getNumberOfPeople(),
                row2.getDepartureDate(),
                row2.getReturnDate(),
                row2.getGlobatiMarker(),
                row2.getCompanyBookedWith()

        );

    }

    @Test
    public void getFlightBookingById() throws ServiceException {
        FlightBooking fb = flightBookingService.getFlightBookingById(flightBooking1.getId());
        FlightBooking fb2 = flightBookingService.getFlightBookingById(flightBooking2.getId());
        Assert.assertEquals(1, fb.getEmployee().getId().longValue());
        Assert.assertEquals(null, fb.getEmployee());
    }


    @Test
    public void testGetFlightBooking() throws ServiceException {
        Assert.assertEquals(1, flightBookingService.getFlightBookingsByEmployeeId(employee.getId()).size());
    }

    @Test
    public void idParser(){
        String marker = "153839."+employee.getId();
        Long id = employee.getId();
        Assert.assertEquals(id, flightBookingService.getEmployeeIdFromMarker(marker));
    }

    @Ignore
    public void idParserWithGreaterValue(){
        Long id = 23422L;
        String marker = "153839."+id;
        Assert.assertEquals(id, flightBookingService.getEmployeeIdFromMarker(marker));
    }

    @Test
    public void googleSheetsIntegration() throws Exception {
        System.out.println(employee.getId());
        Assert.assertTrue(flightBookingService.getDataFromGoogleDriveAndCreateBookings());
    }

}
