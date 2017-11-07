import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Recommendation;
import com.globati.service.EmployeeService;
import com.globati.service.Hello;
import com.globati.service.RecommendationService;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockitoAnnotationsMockAnnotationProcessor;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by daniel on 12/21/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TestRecommendationService {

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    @InjectMocks
    RecommendationService mockRecommendationService;

    @Mock
    Hello hello;

    @Before
    public void setup(){
        this.hello = new Hello("hjola");
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void createReccomendation() throws ServiceException, FileNotFoundException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );

        InputStream fis = new FileInputStream(file);
        InputStream fis2 = new FileInputStream(file);


        Employee employee = employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        Employee e = employeeService.getEmployeeById(employee.getId());
        Recommendation rec = recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", "image1", "image2", "image3");

        Assert.assertEquals(rec.getTitle(), "title");
    }

    @Test
    public void updateRecommendation() throws ServiceException, FileNotFoundException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );

        InputStream fis = new FileInputStream(file);
        InputStream fis2 = new FileInputStream(file);


        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");


        Employee e = employeeService.getEmployeeById(employee.getId());
        Recommendation re = recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", "image1", "image2", "image3");
        Recommendation rec = recommendationService.getRecommendationById(re.getId());
        rec.setActive(true);
        recommendationService.updateRecommendation(rec);
        Assert.assertEquals(recommendationService.getRecommendationById(re.getId()).isActive(), true);
    }

    @Test
    public void getRecommendationsByEmployeeId() throws ServiceException, FileNotFoundException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );

        InputStream fis = new FileInputStream(file);
        InputStream fis2 = new FileInputStream(file);
        InputStream fis3 = new FileInputStream(file);

        Employee e = employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        String country = "Sweden";

        Employee employee =  employeeService.getEmployeeById(e.getId());

        recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", "image1", "image2", "image3");
        recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", "image1", "image2", "image3");

        List<Recommendation> recommendationList = recommendationService.getRecommendationByEmployeeId(employee.getId());
        Assert.assertEquals(2, recommendationList.size());

    }


    @Test
    public void testCreateRecommendation(){

         when(hello.sayHi()).thenReturn("zzzzz");

         Assert.assertEquals(mockRecommendationService.whatup(), "zzzzz");


    }

}
