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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

        Mockito.when(dynamoPropertyRepository.findOne(Mockito.anyString()))
                .thenReturn(this.dynamoProperty);

        Mockito.when(dynamoPropertyRepository.save((DynamoProperty) Mockito.anyObject()))
                .thenReturn(null);
    }

    @Test
    public void updateRecommendation() {
        String updatedCity = "Seattle";
        String updatedDescription = "updatedDescription";
        com.globati.request.Recommendation recommendation = new Recommendation();
        recommendation.setPropertyEmail("daniel@cbp.com");

        recommendation.setCity(updatedCity);
        recommendation.setDescription(updatedDescription);

        DynamoProperty dynamoProperty = dynamoRecommendationService.updateRecommendation(recommendation);

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
}
