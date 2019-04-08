package dynamodb;

import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.DynamoRecommendation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-context.xml"})
@ActiveProfiles("test")
public class TestDynamoRecommendationService extends SuperTest {

    @Test
    public void updateRecommendation() {

        DynamoRecommendation dynamoRecommendation = dynamoRecommendationService.updateRecommendation(this.recommendation);

        Assert.assertEquals(updatedCity, dynamoRecommendation.getCity());
    }

    @Test
    public void deleteRecommendation() {
        int initialSize = dynamoProperty.getDynamoRecommendations().size();
        DynamoProperty dynamoProperty = dynamoRecommendationService.deleteRecommendation(
                this.dynamoProperty.getEmail(),
                this.dynamoProperty.getDynamoRecommendations().get(0).getId() );

        Assert.assertEquals(initialSize -1, dynamoProperty.getDynamoRecommendations().size());
    }

    @Test
    public void testCreateRecommendation(){
        DynamoRecommendation dynamoRecommendation = dynamoRecommendationService.createRecommendation(this.recommendation);
        Assert.assertEquals("title", dynamoRecommendation.getTitle());
    }

    @Test
    public void testGetRecommendationById() {
        String id = dynamoProperty.getDynamoRecommendations().get(0).getId();
        DynamoRecommendation dynamoRecommendation = dynamoRecommendationService.getRecommendationById(id);
        Assert.assertEquals(id, dynamoRecommendation.getId());

    }



}
