
import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Event;
import com.globati.service.EmployeeService;
import com.globati.service.EventService;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.utildb.GlobatiUtilException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 12/21/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TestEventService {

    @Autowired
    EventService eventService;

    @Autowired
    EmployeeService employeeService;

    @Test
    public void createEventInDatabase() throws FileNotFoundException, ServiceException, UserDoesNotExistException, GlobatiUtilException {
        String uid = UUID.randomUUID().toString();


        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );

        InputStream fis2 = new FileInputStream(file);

        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        Date date = new Date();
        Event e = eventService.createEvent(employee, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", "A description", "imageNam2", "imageName3");



    }


    @Test
    public void createEvent() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException {
        String uid = UUID.randomUUID().toString();


        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        InputStream fis2 = new FileInputStream(file);

        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        Date date = new Date();
        Event e = eventService.createEvent(employee, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", "A description", "imageNam2", "imageName3");


        Assert.assertEquals(e.get_title(), "title");
    }

    @Test
    public void updateEvent() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException {
        String uid = UUID.randomUUID().toString();

        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        InputStream fis2 = new FileInputStream(file);

        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        Date date = new Date();
        Event e = eventService.createEvent(employee, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", "A description", "imageNam2", "imageName3");
        Event e2 = eventService.getEventById(e.get_id());
        e2.set_active(false);
        Assert.assertEquals(eventService.updateEvent(e2).is_active(), false);

    }

    @Test
    public void getEventsByEmployeeId() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException {
        String uid = UUID.randomUUID().toString();
        String imageToReplace = "35/d0/cc-5b6e-4941-ab82-3b1881fc94d0image.png"; //Make sure this file exists, otherwise the test will fail!!!!
        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        InputStream fis2 = new FileInputStream(file);

        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");
        Date date = new Date();
        Event e = eventService.createEvent(employee, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", "A description", "imageNam2", "imageName3");
        Employee employee1 = employeeService.getEmployeeById(employee.get_id());
        List<Event> events = eventService.getEventsByEmployeeId(employee1.get_id());
        Assert.assertEquals(1, events.size() );
    }

    @Test
    public void getAllActiveEvents() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException {
        createEventInDatabase();
        createEventInDatabase();
        List<Event> events = eventService.getAllActiveEvents();
        Assert.assertNotNull(events);
        Assert.assertTrue(events.size()>0);

        for(Event e: events){
            Assert.assertTrue(e.is_active());
        }



    }

}
