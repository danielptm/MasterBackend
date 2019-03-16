package dynamodb;


import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import com.globati.request.Recommendation;
import com.globati.request.tour.TourRequest;
import com.globati.request.tour.TourStopRequest;
import com.globati.service.dynamodb.DynamoRecommendationService;
import com.globati.service.dynamodb.DynamoTourService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-context.xml"})
@ActiveProfiles("test")
public class SuperTest {

    @Mock
    DynamoPropertyRepository dynamoPropertyRepository;

    @InjectMocks
    DynamoRecommendationService dynamoRecommendationService;

    @InjectMocks
    DynamoTourService dynamoTourService;

    DynamoProperty dynamoProperty;

    com.globati.request.Recommendation recommendation;
    com.globati.request.tour.TourRequest tourRequest;
    com.globati.request.tour.TourStopRequest tourStopRequest;

    String updatedCity = "Seattle";
    String updatedDescription = "updatedDescription";
    String email = "daniel@cbp.com";

    @Before
    public void setup() {
        DynamoProperty dynamoProperty = new DynamoProperty();
        dynamoProperty.setEmail("daniel@cbp.com");
        dynamoProperty.setName("CBP");
        dynamoProperty.setDescription("AboutCbp");
        dynamoProperty.setCity("Stockholm");

        DynamoRecommendation dynamoRecommendation = new DynamoRecommendation();
        DynamoRecommendation dynamoRecommendation2 = new DynamoRecommendation();

        dynamoRecommendation.setTitle("title");
        dynamoRecommendation.setDescription("description");


        List<DynamoRecommendation> dynamoRecommendations = new ArrayList<>();
        dynamoRecommendations.add(dynamoRecommendation);
        dynamoRecommendations.add(dynamoRecommendation2);

        dynamoProperty.setDynamoTours(new ArrayList<>());
        dynamoProperty.setDynamoRecommendations(dynamoRecommendations);

        tourRequest = new TourRequest();
        tourStopRequest = new TourStopRequest();

        tourStopRequest.setTitle("TourStopTitle");
        tourStopRequest.setDescription("TourStopDescription");

        tourRequest.setTourStopRequests(new ArrayList<>());
        tourRequest.getTourStopRequests().add(tourStopRequest);

        tourRequest.setTitle("TourTitle");
        tourRequest.setDescription("TourDescription");

        this.dynamoProperty = dynamoProperty;

        this.recommendation = new Recommendation();

        //Set the id like this after creation because for the sake of testing these are the same recommendation
        //just being updated.
        this.recommendation.setId(dynamoRecommendation.getId());
        this.recommendation.setPropertyEmail("daniel@cbp.com");
        this.recommendation.setCity(updatedCity);
        this.recommendation.setDescription(updatedDescription);
        this.recommendation.setPropertyEmail(this.email);

        this.recommendation.setImages(new ArrayList<>());
        this.recommendation.getImages().add("image1");


        Mockito.when(dynamoPropertyRepository.findOne(Mockito.anyString()))
                .thenReturn(this.dynamoProperty);

        Mockito.when(dynamoPropertyRepository.save((DynamoProperty) Mockito.anyObject()))
                .thenReturn(null);


    }

}
