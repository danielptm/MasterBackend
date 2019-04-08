package dynamodb;


import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.dynamodb.tour.DynamoTourStop;
import com.globati.api.tour.TourStopRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-context.xml"})
@ActiveProfiles("test")
public class TestDynamoTourService extends SuperTest {


    @Test
    public void testCreateTour() {
        DynamoTour dynamoTour = dynamoTourService.createTour(tourRequest);
        Assert.assertEquals("TourTitle", dynamoTour.getTitle());
    }

    @Test
    public void testUpdateTour() {
        DynamoTour dynamoTour = dynamoTourService.createTour(tourRequest);
        tourRequest.setId(dynamoProperty.getDynamoTours().get(0).getId());
        tourRequest.setCountry("Argentina");
        DynamoTour dynamoTour1 = dynamoTourService.updateTour(tourRequest);
        Assert.assertEquals("Argentina", dynamoTour1.getCountry());
    }

    @Test
    public void testDeleteTour() {
        DynamoTour dynamoTour = dynamoTourService.createTour(tourRequest);
//
//        DynamoProperty

        DynamoProperty dynamoProperty1 = dynamoTourService.deleteTour(dynamoProperty.getEmail(), dynamoTour.getId());

        Assert.assertEquals(1, dynamoProperty.getDynamoTours().size());

    }

    @Test
    public void testGetTourById() {
        DynamoTour dynamoTour = dynamoTourService.createTour(tourRequest);
        DynamoTour dynamoTour2 = dynamoTourService.getTourById(dynamoTour.getId());

        Assert.assertEquals(dynamoTour.getId(), dynamoTour2.getId());
    }

    @Test
    public void getTourStopById(){
        DynamoTour dynamoTour = dynamoTourService.createTour(tourRequest);
        DynamoTourStop dynamoTourStopOriginal = dynamoTour.getTourStops().get(0);
        DynamoTourStop dynamoTourStopToTest = dynamoTourService.getTourStopById(dynamoProperty.getEmail(), dynamoTourStopOriginal.getId());

        Assert.assertEquals(dynamoTourStopOriginal.getId(),dynamoTourStopToTest.getId());

    }

    @Test
    public void testCreateTourStop(){
        DynamoTourStop dynamoTourStop = dynamoTourService.createTourStop(tourStopRequest);

        Assert.assertNotNull(dynamoTourStop);
    }

    @Test
    public void testDeleteTourStop() {

        DynamoProperty returnedProperty = dynamoTourService
                .deleteTourStop(
                        dynamoProperty.getEmail(),
                        dynamoProperty.getDynamoTours().get(0).getId(),
                        dynamoProperty.getDynamoTours().get(0).getTourStops().get(0).getId());

        Assert.assertEquals(0, dynamoProperty.getDynamoTours().get(0).getTourStops().size());
    }

    @Test
    public void testUpdateTourStop(){
        TourStopRequest tourStopRequest = new TourStopRequest();
        tourStopRequest.setTourId(tourRequest.getId());
        tourStopRequest.setId(dynamoProperty.getDynamoTours().get(0).getTourStops().get(0).getId());
        tourStopRequest.setTitle("updatedTitle");
        DynamoTourStop updatedTourStop = dynamoTourService
                .updateTourStop(tourStopRequest);

        Assert.assertEquals("updatedTitle", updatedTourStop.getTitle());
    }
}
