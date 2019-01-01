import com.globati.dbmodel.Property;
import com.globati.request.CreateProperty;
import com.globati.request.tour.TourImageRequest;
import com.globati.request.tour.TourRequest;
import com.globati.request.tour.TourStopImageRequest;
import com.globati.request.tour.TourStopRequest;

import java.util.UUID;

public class SuperTest {

    private static CreateProperty property;

    protected SuperTest() {};

    public static String getRandomString() {
        return UUID.randomUUID().toString();
    }


    /**
     * Create a Property instance. Use synchronized to allow creation of only 1 instance even when using multiple threads.
     */
    private static void createSingletonInstanceOfProperty() {
        if (property == null) {
           property = new CreateProperty();
           property.setFirstName("SINGLETON_PROPERTY_NAME");
           property.setEmail("SINGLETON_PROPERTY_EMAIL");
           property.setImage("SINGLETON_PROPERTY_IMAGE");
           property.setCity("SINGLETON_PROPERTY_CITY");
           property.setCity("SINGLETON_PROPERTY_STREET");
           property.setCity("SINGLETON_PROPERTY_COUNTRY");
           property.setUsername("SINGLETON_PROPERTY_USERNAME");
            property.setPassword("SINGLETON_PROPERTY_PASSWORD");
            property.setTargetLat(11.11);
           property.setTargetLong(111.11);
        }
    }

    public static CreateProperty getUniquePropertyInstance() {
        String uniqueMarker = getRandomString();
        CreateProperty property = new CreateProperty();
        property.setFirstName("UNIQUE_PROPERTY_NAME_" + uniqueMarker);
        property.setEmail("UNIQUE_PROPERTY_EMAIL_" + uniqueMarker);
        property.setCity("UNIQUE_PROPERTY_CITY");
        property.setUsername("UNIQUE_PROPERTY_USERNAME_" + uniqueMarker);
        property.setPassword("UNIQUE_PROPERTY_PASSWORD");
        property.setTargetLat(11.11);
        property.setTargetLong(111.11);
        return property;
    }

    public static CreateProperty getSingletonPropertyInstance() {
        if(property == null) {
            createSingletonInstanceOfProperty();
        }
        return property;
    }

    /**
     * Get a tour request with a given property id.
     * @param id
     * @return TourRequest
     */
    public static TourRequest getATourRequestWithGivenPropertyId(Long id) {
        TourRequest tourRequest = new TourRequest();
        tourRequest.setPropertyId(id);
        tourRequest.setCity("city");
        tourRequest.setCountry("country");
        tourRequest.setTitle("title");
        tourRequest.setTargetLat(11.11);
        tourRequest.setTargetLong(11.11);
        return tourRequest;
    }

    /**
     * Get a unique TourImageRequest object.
     * @return TourImageRequest
     */
    public static TourImageRequest getUniqueTourImageRequest() {
        TourImageRequest tourImageRequest = new TourImageRequest("PATH_" + getRandomString());
        return tourImageRequest;
    }

    public static TourStopImageRequest getUniqueTourStopImageRequest() {
        TourStopImageRequest tourStopImageRequest = new TourStopImageRequest(1L, "TOUR_STOP_IMAGE_REQUEST_PATH");
        return tourStopImageRequest;
    }

    /**
     * Get a unique TourStopRequest object.
     * @return TourStopRequest
     */
    public static TourStopRequest getUniqueTourStopRequest() {
        TourStopRequest tourStopRequest = new TourStopRequest();

        tourStopRequest.setCity("tourStopCity");
        tourStopRequest.setCountry("tourStopCountry");
        tourStopRequest.setStreet("tourStreet");
        tourStopRequest.setDescription("tourStopDescription");
        tourStopRequest.setId(1L);
        tourStopRequest.setTargetLat(11.11);
        tourStopRequest.setTargetLong(11.11);
        tourStopRequest.setTitle("tourStopTitle");

        return tourStopRequest;
    }

}
