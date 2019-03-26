package dynamodb;


import com.globati.service.dynamodb.DynamoPropertyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-context.xml"})
@ActiveProfiles("test")
public class TestDynamoImageService extends SuperTest{

    @Autowired
    DynamoPropertyService dynamoPropertyService;

    @Test
    public void testCreateDynamoImage() {

    }

    @Test
    public void testGetDynamoImageById() {

    }

    @Test
    public void testUpdateDynamoImage() {

    }

    @Test
    public void testDeleteDynamoIamge() {

    }

}
