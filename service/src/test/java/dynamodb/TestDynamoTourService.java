package dynamodb;


import com.globati.dynamodb.DynamoProperty;
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

    }

    @Test
    public void testDeleteTour() {

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
