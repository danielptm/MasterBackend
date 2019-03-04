import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.DynamoRecommendation;
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

import java.util.ArrayList;
import java.util.List;

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
    public void testing() throws JsonProcessingException {

        List<DynamoRecommendation> recommendationList = new ArrayList<>();

        DynamoRecommendation dr = new DynamoRecommendation();

        dr.setCity("Seattle");

        recommendationList.add(dr);

        DynamoProperty db = new DynamoProperty();

        db.setEmail("danielptm@me.com");
        db.setName("CBP");

        db.setDynamoRecommendations(recommendationList);

        dynamoPropertyService.createDynamoProperty(db);

        System.out.println(dynamoPropertyService.getDynamoPropertyById("danielptm@me.com").toString());


    }

}
