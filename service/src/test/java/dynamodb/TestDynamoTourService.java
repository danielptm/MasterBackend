package dynamodb;


import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.dynamodb.tour.DynamoTourStop;
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
        DynamoProperty dynamoProperty = dynamoTourService.createTour(tourRequest);
        DynamoTour dynamoTour = dynamoTourService.getTourById(dynamoProperty.getDynamoTours().get(0).getId());

        Assert.assertEquals(dynamoProperty.getDynamoTours().get(0).getId(), dynamoTour.getId());
    }

    @Test
    public void getTourStopById(){
        DynamoProperty dynamoProperty = dynamoTourService.createTour(tourRequest);
        DynamoTourStop dynamoTourStopOriginal = dynamoProperty.getDynamoTours().get(0).getTourStops().get(0);
        DynamoTourStop dynamoTourStopToTest = dynamoTourService.getTourStopById(dynamoProperty.getEmail(), dynamoTourStopOriginal.getId());

        Assert.assertEquals(dynamoTourStopOriginal.getId(),dynamoTourStopToTest.getId());

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
