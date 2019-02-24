import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.globati.dynamodb.DynamoProperty;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import com.globati.service.DynamoPropertyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TestDynamoDbService {

    @Autowired
    DynamoPropertyService dynamoPropertyService;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    private static final Logger log = LogManager.getLogger(TestDynamoDbService.class);



    @Test
    public void testing() {

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);



        DynamoProperty db = new DynamoProperty();
        db.setEmail("danielptm@me.com");
        db.setName("CBP");


        dynamoPropertyService.createDynamoProperty(db);


    }

}
