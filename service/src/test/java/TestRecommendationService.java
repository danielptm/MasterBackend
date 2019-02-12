import com.globati.dbmodel.Property;
import com.globati.dbmodel.Recommendation;
import com.globati.service.PropertyService;
import com.globati.service.JwtService;
import com.globati.service.RecommendationService;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import com.globati.service_beans.guest.PropertyAndItems;
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
public class TestRecommendationService extends SuperTest {

    @Autowired
    RecommendationService recommendationService;

    @InjectMocks
    @Autowired
    PropertyService propertyService;

    @Mock
    JwtService jwtService;

    Property commonProperty;


    @Before
    public void prep() throws ServiceException, UserNameIsNotUniqueException, UserDoesNotExistException, IllegalUserNameException {
        String randomString = UUID.randomUUID().toString();
        MockitoAnnotations.initMocks(this);
        this.commonProperty = propertyService.createProperty(getUniquePropertyInstance());
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

        Property e = propertyService.getPropertyById(commonProperty.getId());
        Recommendation rec = recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", images, "DINNER");


        Recommendation re3 = recommendationService.getRecommendationById(rec.getId());
        re3.getImages().get(0).setImagePath("asdfasdf");
        re3.setCity("sdsdf");

        recommendationService.updateRecommendation(re3);

        PropertyAndItems propertyAndItems = propertyService.getItemsForProperty(e.getGlobatiUsername());


        Assert.assertEquals(1, propertyAndItems.getProperty().getRecommendations().size());
        Assert.assertEquals(3, propertyAndItems.getProperty().getRecommendations().get(0).getImages().size());
    }

    @Test
    public void updateRecommendationAndRecommendationImages() throws ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        Property employee = propertyService.createProperty(getUniquePropertyInstance());

        List<String> images = new ArrayList<>();

        String image1 = "image1/url";
        String image2 = "image2/url";
        String image3 = "image3/url";

        images.add(image1);
        images.add(image2);
        images.add(image3);

        Property e = propertyService.getPropertyById(employee.getId());
        Recommendation re = recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", images, "DINNER");

        re.setCity("hithere");
        re.getImages().get(0).setImagePath("hithere");

        recommendationService.updateRecommendation(re);

        PropertyAndItems propertyAndItems = propertyService.getItemsForPropertyButNoWebToken(e.getGlobatiUsername());

        Assert.assertEquals(3, propertyAndItems.getProperty().getRecommendations().get(0).getImages().size());
        Assert.assertEquals("hithere", propertyAndItems.getProperty().getRecommendations().get(0).getImages().get(0).getImagePath());


    }

    @Test
    public void updateRecommendationAndRecommendationImagesWithParameters() throws ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        Property employee = propertyService.createProperty(getUniquePropertyInstance());

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

        Property e = propertyService.getPropertyById(employee.getId());
        Recommendation re = recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", images, "DINNER");

        Recommendation updated = recommendationService.updateRecommendation(re.getId(), newTitle, newDescription, newImages);

        PropertyAndItems propertyAndItems = propertyService.getItemsForPropertyButNoWebToken(e.getGlobatiUsername());


        Assert.assertEquals(3, propertyAndItems.getProperty().getRecommendations().get(0).getImages().size());
        Assert.assertEquals(1, propertyAndItems.getProperty().getRecommendations().size());
        Assert.assertEquals(newImage1, propertyAndItems.getProperty().getRecommendations().get(0).getImages().get(0).getImagePath());
        Assert.assertEquals(newImage2, propertyAndItems.getProperty().getRecommendations().get(0).getImages().get(1).getImagePath());
        Assert.assertEquals(newImage3, propertyAndItems.getProperty().getRecommendations().get(0).getImages().get(2).getImagePath());


    }

    @Test
    public void getRecommendationsFromItemsOfProperty() throws ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        Property e = propertyService.createProperty(getUniquePropertyInstance());

        Property employee =  propertyService.getPropertyById(e.getId());

        List<String> images = new ArrayList<>();

        images.add("image1/url");
        images.add("image2/url");
        images.add("image3/url");

        recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", images, "DINNER" );
        recommendationService.createRecommendation(e.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", images, "DINNER" );

        List<Recommendation> recommendationList = recommendationService.getRecommendationByPropertyId(e.getId());

        PropertyAndItems e3 = propertyService.getItemsForProperty(e.getGlobatiUsername());

        Assert.assertEquals(2, recommendationList.size());
        Assert.assertEquals(2, e3.getProperty().getRecommendations().size());
        Assert.assertEquals(3, e3.getProperty().getRecommendations().get(0).getImages().size());

    }




}
