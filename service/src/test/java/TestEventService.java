
import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Event;
import com.globati.service.EmployeeService;
import com.globati.service.EventService;
import com.globati.service.JwtService;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import com.globati.service_beans.guest.EmployeeAndItems;
import com.globati.utildb.GlobatiUtilException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

/**
 * Created by daniel on 12/21/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TestEventService {

    @Autowired
    EventService eventService;

    @InjectMocks
    @Autowired
    EmployeeService employeeService;

    @Mock
    JwtService jwtService;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        when(jwtService.buildJwt(Mockito.anyString())).thenReturn("mockapistring!");
    }

    @Test
    public void createEventInDatabase() throws FileNotFoundException, ServiceException, UserDoesNotExistException, GlobatiUtilException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        List<String> images = new ArrayList<>();

        String image1 = "image1/url";
        String image2 = "image2/url";
        String image3 = "image3/url";

        images.add(image1);
        images.add(image2);
        images.add(image3);


        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        Date date = new Date();
        Event e = eventService.createEvent(employee, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", images);

    }


    @Test
    public void createEvent() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        List<String> images = new ArrayList<>();

        String image1 = "image1/url";
        String image2 = "image2/url";
        String image3 = "image3/url";

        images.add(image1);
        images.add(image2);
        images.add(image3);

        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        Date date = new Date();
        Event e = eventService.createEvent(employee, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", images);

        Event e2 = eventService.getEventById(e.getId());
        Assert.assertEquals(e.getTitle(), "title");
        Assert.assertEquals(3, e2.getEventimages().size());
    }

    @Test
    public void updateEvent() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        List<String> images = new ArrayList<>();

        String image1 = "image1/url";
        String image2 = "image2/url";
        String image3 = "image3/url";

        images.add(image1);
        images.add(image2);
        images.add(image3);

        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        Date date = new Date();
        Event e = eventService.createEvent(employee, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", images);

        e.setCity("hithere");
        e.getEventimages().get(0).setPath("testvalue");

        eventService.updateEvent(e);

        EmployeeAndItems employeeAndItems = employeeService.getItemsForEmployee(employee.getGlobatiUsername());

        Assert.assertEquals(1, employeeAndItems.getEmployee().getEvents().size());
        Assert.assertEquals(3, employeeAndItems.getEmployee().getEvents().get(0).getEventimages().size());
        Assert.assertEquals("testvalue", employeeAndItems.getEmployee().getEvents().get(0).getEventimages().get(0).getPath());

    }

    @Test
    public void getEventsByEmployeeId() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        List<String> images = new ArrayList<>();

        String image1 = "image1/url";
        String image2 = "image2/url";
        String image3 = "image3/url";

        images.add(image1);
        images.add(image2);
        images.add(image3);


        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");
        Date date = new Date();
        Event e = eventService.createEvent(employee, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", images);
        Event e2 = eventService.createEvent(employee, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", images);

        EmployeeAndItems eai = employeeService.getItemsForEmployee(employee.getGlobatiUsername());

        Assert.assertEquals(3, eai.getEmployee().getEvents().get(0).getEventimages().size());

    }

    @Test
    public void getAllActiveEvents() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        createEventInDatabase();
        createEventInDatabase();
        List<Event> events = eventService.getAllActiveEvents();
        Assert.assertNotNull(events);
        Assert.assertTrue(events.size()>0);

        for(Event e: events){
            Assert.assertTrue(e.isActive());
        }
    }

}
