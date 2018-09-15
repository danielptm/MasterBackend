import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Recommendation;
import com.globati.service.EmployeeService;
import com.globati.service.JwtService;
import com.globati.service.RecommendationService;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import com.globati.service_beans.guest.EmployeeAndItems;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

    @InjectMocks
    @Autowired
    EmployeeService employeeService;

    @Mock
    JwtService jwtService;

    Employee commonEmployee;


    @Before
    public void prep() throws ServiceException, UserNameIsNotUniqueException, UserDoesNotExistException, IllegalUserNameException {
        String randomString = UUID.randomUUID().toString();
        MockitoAnnotations.initMocks(this);
        this.commonEmployee = employeeService.createEmployee(
                "check this2", randomString+"@me.com", randomString, "secret password",
                59.336038, 18.055268, "image", "2308 n 44 st", "seattle", "usa"
        );
        when(jwtService.buildJwt(Mockito.anyString())).thenReturn("mockApiToken");
    }


    @Test
    public void createReccomendation() throws ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        List<String> images = new ArrayList<>();

        String image1 = "image1/url";
        String image2 = "image2/url";
        String image3 = "image3/url";

        images.add(image1);
        images.add(image2);
        images.add(image3);

        Employee e = employeeService.getEmployeeById(commonEmployee.getId());
        Recommendation rec = recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", images, "DINNER");


        Recommendation re3 = recommendationService.getRecommendationById(rec.getId());
        re3.getRecommendationimages().get(0).setPath("asdfasdf");
        re3.setCity("sdsdf");

        recommendationService.updateRecommendation(re3);

        EmployeeAndItems employeeAndItems = employeeService.getItemsForEmployee(e.getGlobatiUsername());


        Assert.assertEquals(1, employeeAndItems.getEmployee().getRecommendations().size());
        Assert.assertEquals(3, employeeAndItems.getEmployee().getRecommendations().get(0).getRecommendationimages().size());
    }

    @Test
    public void updateRecommendationAndRecommendationImages() throws ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        List<String> images = new ArrayList<>();

        String image1 = "image1/url";
        String image2 = "image2/url";
        String image3 = "image3/url";

        images.add(image1);
        images.add(image2);
        images.add(image3);

        Employee e = employeeService.getEmployeeById(employee.getId());
        Recommendation re = recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", images, "DINNER");

        re.setCity("hithere");
        re.getRecommendationimages().get(0).setPath("hithere");

        recommendationService.updateRecommendation(re);

        EmployeeAndItems employeeAndItems = employeeService.getItemsForEmployeeButNoWebToken(e.getGlobatiUsername());

        Assert.assertEquals(3, employeeAndItems.getEmployee().getRecommendations().get(0).getRecommendationimages().size());
        Assert.assertEquals("hithere", employeeAndItems.getEmployee().getRecommendations().get(0).getRecommendationimages().get(0).getPath());


    }

    @Test
    public void updateRecommendationAndRecommendationImagesWithParameters() throws ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        List<String> images = new ArrayList<>();
        List<String> newImages = new ArrayList<>();

        String image1 = "image1/url";
        String image2 = "image2/url";
        String image3 = "image3/url";

        String newImage1 = "newImage1";
        String newImage2 = "newImage2";
        String newImage3 = "newImage3";
        String newTitle = "newTitle";
        String newDescription = "newDescription";


        images.add(image1);
        images.add(image2);
        images.add(image3);

        newImages.add(newImage1);
        newImages.add(newImage2);
        newImages.add(newImage3);

        Employee e = employeeService.getEmployeeById(employee.getId());
        Recommendation re = recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", images, "DINNER");

        Recommendation updated = recommendationService.updateRecommendation(re.getId(), newTitle, newDescription, newImages);

        EmployeeAndItems employeeAndItems = employeeService.getItemsForEmployeeButNoWebToken(e.getGlobatiUsername());


        Assert.assertEquals(3, employeeAndItems.getEmployee().getRecommendations().get(0).getRecommendationimages().size());
        Assert.assertEquals(1, employeeAndItems.getEmployee().getRecommendations().size());
        Assert.assertEquals(newImage1, employeeAndItems.getEmployee().getRecommendations().get(0).getRecommendationimages().get(0).getPath());
        Assert.assertEquals(newImage2, employeeAndItems.getEmployee().getRecommendations().get(0).getRecommendationimages().get(1).getPath());
        Assert.assertEquals(newImage3, employeeAndItems.getEmployee().getRecommendations().get(0).getRecommendationimages().get(2).getPath());


    }

    @Test
    public void getRecommendationsFromItemsOfEmployee() throws ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        Employee e = employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        Employee employee =  employeeService.getEmployeeById(e.getId());

        List<String> images = new ArrayList<>();

        images.add("image1/url");
        images.add("image2/url");
        images.add("image3/url");

        recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", images, "DINNER" );
        recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", images, "DINNER" );

        List<Recommendation> recommendationList = recommendationService.getRecommendationByEmployeeId(e.getId());

        EmployeeAndItems e3 = employeeService.getItemsForEmployee(e.getGlobatiUsername());

        Assert.assertEquals(2, recommendationList.size());
        Assert.assertEquals(2, e3.getEmployee().getRecommendations().size());
        Assert.assertEquals(3, e3.getEmployee().getRecommendations().get(0).getRecommendationimages().size());

    }




}
