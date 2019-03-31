package dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.request.RequestProperty;
import com.globati.service.dynamodb.DynamoPropertyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class TestDynamoPropertyService extends SuperTest {

    private static final Logger log = LogManager.getLogger(TestDynamoPropertyService.class);

    //TODO: Create a test that tests creating a profile without an email or invalid email
    //TODO: Create a test that throws an exception if password is not there.
    @Test
    public void createProperty() {
        RequestProperty requestProperty2 = new RequestProperty();
        requestProperty2.setUserName("daniel");
        requestProperty2.setEmail("daniel@me.com");
        requestProperty2.setPassword("password");
        DynamoProperty dynamoProperty = dynamoPropertyService.createDynamoProperty(requestProperty2);
        Assert.assertEquals("daniel", dynamoProperty.getUserName());
    }

    @Test
    public void deleteProperty() {
        String email = dynamoPropertyService.deleteDynamoProperty("email@me.com");
        Assert.assertEquals("email@me.com", email);
    }

    @Test
    public void updateProperty() {
        RequestProperty requestProperty = new RequestProperty();
        requestProperty.setEmail("updatedEmail");
        DynamoProperty dynamoProperty = dynamoPropertyService.updateDynamoProperty(requestProperty);

        Assert.assertEquals("updatedEmail", dynamoProperty.getEmail());
    }

    @Test
    public void testAuthenticate() {
        RequestProperty requestProperty = new RequestProperty();
        requestProperty.setUserName("danielptm");
        requestProperty.setPassword("hello");

        DynamoProperty dynamoProperty = dynamoPropertyService.createDynamoProperty(requestProperty);

        Assert.assertTrue(dynamoProperty.getHashedPassword().length() > 10);
        Assert.assertEquals("danielptm", dynamoProperty.getUserName());

    }

    @Test
    public void testChangePassword() {
       System.out.println("testChangePassword() not implemented");
    }

    @Test
    public void testChangePasswordWithToken() {
        System.out.println("testChangePasswordWithToken() not implemented");
    }


}
