package dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.request.RequestProperty;
import com.globati.service.dynamodb.DynamoPropertyService;
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
@ContextConfiguration(locations = {"classpath:/spring/test-context.xml"})
@ActiveProfiles("test")
public class TestDynamoPropertyService {

    @Autowired
    DynamoPropertyService dynamoPropertyService;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    private static final Logger log = LogManager.getLogger(TestDynamoPropertyService.class);

    @Test
    public void createProperty() throws JsonProcessingException {
    }

    @Test
    public void deleteProperty() {

    }

    @Test
    public void updateProperty() {

    }
}
