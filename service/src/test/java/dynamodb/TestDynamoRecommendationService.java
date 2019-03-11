package dynamodb;

import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import com.globati.request.Recommendation;
import com.globati.service.dynamodb.DynamoRecommendationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-context.xml"})
@ActiveProfiles("test")
public class TestDynamoRecommendationService {

    @Mock
    DynamoPropertyRepository dynamoPropertyRepository;

    @InjectMocks
    DynamoRecommendationService dynamoRecommendationService;

    DynamoProperty dynamoProperty;

    com.globati.request.Recommendation recommendation;

    String updatedCity = "Seattle";
    String updatedDescription = "updatedDescription";
    String email = "daniel@cbp.com";

    @Before
    public void setup() {
        DynamoProperty dynamoProperty = new DynamoProperty();
        dynamoProperty.setEmail("daniel@cbp.com");
        dynamoProperty.setName("CBP");
        dynamoProperty.setDescription("AboutCbp");
        dynamoProperty.setCity("Stockholm");

        DynamoRecommendation dynamoRecommendation = new DynamoRecommendation();

        dynamoRecommendation.setTitle("title");
        dynamoRecommendation.setDescription("description");


        List<DynamoRecommendation> dynamoRecommendations = new ArrayList<>();
        dynamoRecommendations.add(dynamoRecommendation);
        dynamoProperty.setDynamoRecommendations(dynamoRecommendations);

        this.dynamoProperty = dynamoProperty;

        this.recommendation = new Recommendation();

        //Set the id like this after creation because for the sake of testing these are the same recommendation
        //just being updated.
        this.recommendation.setId(dynamoRecommendation.getId());
        this.recommendation.setPropertyEmail("daniel@cbp.com");
        this.recommendation.setCity(updatedCity);
        this.recommendation.setDescription(updatedDescription);
        this.recommendation.setPropertyEmail(this.email);

        this.recommendation.setImages(new ArrayList<>());
        this.recommendation.getImages().add("image1");

        Mockito.when(dynamoPropertyRepository.findOne(Mockito.anyString()))
                .thenReturn(this.dynamoProperty);

        Mockito.when(dynamoPropertyRepository.save((DynamoProperty) Mockito.anyObject()))
                .thenReturn(null);

    }

    @Test
    public void updateRecommendation() {

        DynamoProperty dynamoProperty = dynamoRecommendationService.updateRecommendation(this.recommendation);

        Assert.assertEquals(updatedCity, dynamoProperty.getDynamoRecommendations().get(0).getCity());
        Assert.assertEquals(updatedDescription, dynamoProperty.getDynamoRecommendations().get(0).getDescription());
    }

    @Test
    public void deleteRecommendation() {
        DynamoProperty dynamoProperty = dynamoRecommendationService.deleteRecommendation(
                this.dynamoProperty.getEmail(),
                this.dynamoProperty.getDynamoRecommendations().get(0).getId() );

        Assert.assertEquals(0, dynamoProperty.getDynamoRecommendations().size());
    }

    @Test
    public void testCreateRecommendation(){
        DynamoProperty dynamoProperty = dynamoRecommendationService.createRecommendation(this.recommendation);
        System.out.println(dynamoProperty);
        Assert.assertEquals(this.email, dynamoProperty.getEmail());
//        Assert.assertEquals(2, dynamoProperty.getDynamoRecommendations().get(0).getImages().size());
    }

    @Test
    public void testGetRecommendationById() {

    }

    @Test
    public void testGetRecommendaitonsByEmployeeName() {

    }


}
