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
public class TestDynamoRecommendationService extends SuperTest{

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

        Assert.assertEquals(1, dynamoProperty.getDynamoRecommendations().size());
    }

    @Test
    public void testCreateRecommendation(){
        DynamoProperty dynamoProperty = dynamoRecommendationService.createRecommendation(this.recommendation);
        Assert.assertEquals(this.email, dynamoProperty.getEmail());
        Assert.assertEquals(1, dynamoProperty.getDynamoRecommendations().get(2).getImages().size());
    }

    @Test
    public void testGetRecommendationById() {
        String id = dynamoProperty.getDynamoRecommendations().get(0).getId();
        DynamoRecommendation dynamoRecommendation = dynamoRecommendationService.getRecommendationById(id);
        Assert.assertEquals(id, dynamoRecommendation.getId());

    }



}
