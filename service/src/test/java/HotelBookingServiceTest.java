import com.globati.dbmodel.Employee;
import com.globati.dbmodel.FlightBooking;
import com.globati.dbmodel.HotelBooking;
import com.globati.s3.FlightBookingRow;
import com.globati.s3.HotelBookingRow;
import com.globati.service.EmployeeService;
import com.globati.service.HotelBookingService;
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

import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class HotelBookingServiceTest {

    @Autowired
    HotelBookingService hotelBookingService;

    @Autowired
    EmployeeService employeeService;

    HotelBookingRow hotelBookingrow1;
    HotelBookingRow hotelBookingRow2;

    HotelBooking hotelBooking1;
    HotelBooking hotelBooking2;

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

        String dateBooked = "2017-12-19";
        String time = "14:34";
        String paidStatus = "PAID";
        String paidStatus2 = "CANCELLED";
        String cost = "726.22";
        String comission = "5.96";
        String flightPlan = "Stockholm (STO) - Seattle (SEA)";
        String passengers = "1";
        String departureDate = "2017.12.19";
        String returnDate = "2017.12.28";
        String marker = "153839."+employee.getId();
        String markerWithOutId = "153839";
        String company = "Kissandfly";

        hotelBookingrow1 = new HotelBookingRow(
                dateBooked,
                time, paidStatus, cost, comission, flightPlan,
                departureDate, returnDate, marker, company
        );

        hotelBookingRow2 = new HotelBookingRow(
                dateBooked,
                time, paidStatus2, cost, comission, flightPlan,
                departureDate, returnDate, markerWithOutId, company
        );


        //FlightBooking with employeeId
        hotelBooking1 = hotelBookingService.createHotelBooking(
                hotelBookingrow1.getDateBooked(),
                hotelBookingrow1.getTimeBooked(),
                hotelBookingrow1.getPaidStatus(),
                hotelBookingrow1.getCostOfTicket(),
                hotelBookingrow1.getGlobatiComission(),
                hotelBookingrow1.getFlightPlan(),
                hotelBookingrow1.getDepartureDate(),
                hotelBookingrow1.getReturnDate(),
                hotelBookingrow1.getGlobatiMaker(),
                hotelBookingrow1.getCompanyBookedWith()
        );


        //FlightBooking with no employeeId
        hotelBooking2 = hotelBookingService.createHotelBooking(
                hotelBookingRow2.getDateBooked(),
                hotelBookingRow2.getTimeBooked(),
                hotelBookingRow2.getPaidStatus(),
                hotelBookingRow2.getCostOfTicket(),
                hotelBookingRow2.getGlobatiComission(),
                hotelBookingRow2.getFlightPlan(),
                hotelBookingRow2.getDepartureDate(),
                hotelBookingRow2.getReturnDate(),
                hotelBookingRow2.getGlobatiMaker(),
                hotelBookingRow2.getCompanyBookedWith()
        );
    }

    @Test
    public void getFlightBookingById() throws ServiceException {
        HotelBooking fb = hotelBookingService.getHotelBookingById(hotelBooking1.getId());
        HotelBooking fb2 = hotelBookingService.getHotelBookingById(hotelBooking2.getId());
        Assert.assertEquals(hotelBooking1.getEmployee().getId(), fb.getEmployee().getId());
        Assert.assertEquals(null, fb2.getEmployee());
    }


    @Test
    public void testGetFlightBooking() throws ServiceException {
        Assert.assertEquals(1, hotelBookingService.getHotelBookingsByEmployeeId(employee.getId()).size());
    }

    @Test
    public void idParser(){
        String marker = "153839."+employee.getId();
        Long id = employee.getId();
        Assert.assertEquals(id, hotelBookingService.getEmployeeIdFromMarker(marker));
    }

    @Test
    public void getFlightBookingAndPaidStatusEqualsPaid() throws ServiceException {
        List<HotelBooking> rows = hotelBookingService.getHotelBookingsByEmployeeIdAndPaidStatus(employee.getId());
        Assert.assertEquals(1, rows.size());
    }

    @Ignore
    public void idParserWithGreaterValue(){
        Long id = 23422L;
        String marker = "153839."+id;
        Assert.assertEquals(id, hotelBookingService.getEmployeeIdFromMarker(marker));
    }

    /**
     * This is an integration test with the globati.network accoutn with the sheets api. It will fail in a production environemnt.
     * Only do this when checking cross checking manually, and setting up the test properly.
     * @throws Exception
     */
    @Ignore
    public void s3fligthbookingintegration() throws Exception {
        System.out.println(employee.getId());
        Assert.assertTrue(hotelBookingService.getHotelBookingsFromS3());
    }




}
