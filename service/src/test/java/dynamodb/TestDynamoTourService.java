package dynamodb;


import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.tour.DynamoTour;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-context.xml"})
@ActiveProfiles("test")
public class TestDynamoTourService extends SuperTest{


    @Test
    public void testCreateTour() {
        DynamoProperty dynamoProperty = dynamoTourService.createTour(tourRequest);
        Assert.assertEquals(1, dynamoProperty.getDynamoTours().size());
    }

    @Test
    public void testUpdateTour() {
        DynamoProperty dynamoProperty = dynamoTourService.createTour(tourRequest);
        tourRequest.setId(dynamoProperty.getDynamoTours().get(0).getId());
        tourRequest.setCountry("Argentina");
        DynamoProperty dynamoProperty2 = dynamoTourService.updateTour(tourRequest);
        Assert.assertEquals("Argentina", dynamoProperty2.getDynamoTours().get(0).getCountry());
    }

    @Test
    public void testDeleteTour() {
        DynamoProperty dynamoProperty = dynamoTourService.createTour(tourRequest);

        Assert.assertEquals(1, dynamoProperty.getDynamoTours().size());

        DynamoProperty dynamoProperty1 = dynamoTourService.deleteTour(dynamoProperty.getEmail(), dynamoProperty.getDynamoTours().get(0).getId());

        Assert.assertEquals(0, dynamoProperty.getDynamoTours().size());



    }

    @Test
    public void testGetTourById() {

    }

    @Test
    public void getTourStopById(){

    }

    @Test
    public void testCreateTourStop(){


    }

    @Test
    public void testDeleteTourStop() {

    }

    @Test
    public void testUpdateTourStop(){

    }
}
