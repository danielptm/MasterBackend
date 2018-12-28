import com.globati.dbmodel.Property;
import com.globati.dbmodel.TourStopImage;
import com.globati.request.tour.TourImageRequest;
import com.globati.request.tour.TourRequest;
import com.globati.request.tour.TourStopImageRequest;
import com.globati.request.tour.TourStopRequest;
import com.globati.service.PropertyService;
import com.globati.service.TourService;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TourRequestTest {

    @Autowired
    TourService tourService;

    @Autowired
    PropertyService propertyService;

    public String getRandomString() {
        return UUID.randomUUID().toString();
    }


    @Test
    public void testcreateTour() throws ServiceException, UserNameIsNotUniqueException {
        Property property = propertyService.createProperty(
                getRandomString(),
                        "email",
                getRandomString(),
                "password",
                11.11,
                11.11,
                "image",
                "street",
                "city",
                "country"
                );
        TourRequest tourRequest = new TourRequest();

        tourRequest.setPropertyId(property.getId());
        tourRequest.setCity("city");
        tourRequest.setCountry("country");
        tourRequest.setTitle("title");
        tourRequest.setTargetLat(11.11);
        tourRequest.setTargetLong(11.11);

        List<TourImageRequest> images = new ArrayList<>();

        TourImageRequest tourImageRequest = new TourImageRequest("path", "TOUR");
        TourImageRequest tourImageRequest2 = new TourImageRequest("path", "TOUR");

        images.add(tourImageRequest);
        images.add(tourImageRequest2);

        tourRequest.setImages(images);

        List<TourStopRequest> tourStopRequests = new ArrayList<>();

        TourStopRequest tourStopRequest = new TourStopRequest();

        tourStopRequest.setCity("tourStopCity");
        tourStopRequest.setCountry("tourStopCountry");
        tourStopRequest.setStreet("tourStreet");
        tourStopRequest.setDescription("tourStopDescription");
        tourStopRequest.setId(1L);
        tourStopRequest.setTargetLat(11.11);
        tourStopRequest.setTargetLong(11.11);
        tourStopRequest.setTitle("tourStopTitle");

        tourStopRequests.add(tourStopRequest);
        tourRequest.setTourStopRequests(tourStopRequests);


        com.globati.dbmodel.Tour createdTour = tourService.createTour(tourRequest);

        Assert.assertNotNull(createdTour);
    }

    @Test
    public void testGetToursByPropertyId() throws UserNameIsNotUniqueException, ServiceException {
        Property property = propertyService.createProperty(
                getRandomString(),
                "email",
                getRandomString(),
                "password",
                11.11,
                11.11,
                "image",
                "street",
                "city",
                "country"
        );
        TourRequest tourRequest = new TourRequest();

        tourRequest.setPropertyId(property.getId());
        tourRequest.setCity("city");
        tourRequest.setCountry("country");
        tourRequest.setTitle("title");
        tourRequest.setTargetLat(11.11);
        tourRequest.setTargetLong(11.11);

        List<TourImageRequest> images = new ArrayList<>();

        TourImageRequest tourImageRequest = new TourImageRequest("path", "TOUR");
        TourImageRequest tourImageRequest2 = new TourImageRequest("path", "TOUR");

        images.add(tourImageRequest);
        images.add(tourImageRequest2);

        tourRequest.setImages(images);


        List<TourStopRequest> tourStopRequests = new ArrayList<>();

        TourStopRequest tourStopRequest = new TourStopRequest();

        tourStopRequest.setCity("tourStopCity");
        tourStopRequest.setCountry("tourStopCountry");
        tourStopRequest.setStreet("tourStreet");
        tourStopRequest.setDescription("tourStopDescription");
        tourStopRequest.setId(1L);
        tourStopRequest.setTargetLat(11.11);
        tourStopRequest.setTargetLong(11.11);
        tourStopRequest.setTitle("tourStopTitle");

        tourStopRequests.add(tourStopRequest);
        tourRequest.setTourStopRequests(tourStopRequests);


        com.globati.dbmodel.Tour createdTour = tourService.createTour(tourRequest);

        List<com.globati.dbmodel.Tour> retrievedTours = tourService.getToursByPropertyId(property.getId());

        //Size of tours is 1
        Assert.assertTrue(retrievedTours.size() == 1);
        //Size of TourStops is 1
        Assert.assertTrue(retrievedTours.get(0).getTourStops().size() == 1);

        //Test that images are bigger than 1
        Assert.assertTrue(retrievedTours.get(0).getTourImages().size() > 0);
    }

    @Test
    public void testUpdateTour() throws UserNameIsNotUniqueException {
        Property property = propertyService.createProperty(
                getRandomString(),
                "email",
                getRandomString(),
                "password",
                11.11,
                11.11,
                "image",
                "street",
                "city",
                "country"
        );
        TourRequest tourRequest = new TourRequest();

        tourRequest.setPropertyId(property.getId());
        tourRequest.setCity("city");
        tourRequest.setCountry("country");
        tourRequest.setTitle("title");
        tourRequest.setTargetLat(11.11);
        tourRequest.setTargetLong(11.11);

        List<TourImageRequest> images = new ArrayList<>();

        TourImageRequest tourImageRequest = new TourImageRequest("path", "TOUR");
        TourImageRequest tourImageRequest2 = new TourImageRequest("path", "TOUR");

        images.add(tourImageRequest);
        images.add(tourImageRequest2);

        tourRequest.setImages(images);

        List<TourStopRequest> tourStopRequests = new ArrayList<>();

        TourStopRequest tourStopRequest = new TourStopRequest();

        TourStopImageRequest tourStopImageRequest = new TourStopImageRequest(1L, "tourStopImagePath");
        TourStopImageRequest tourStopImageRequest2 = new TourStopImageRequest(2L, "tourStopImagePath");

        List<TourStopImageRequest> tourStopImageRequests = new ArrayList<>();
        tourStopImageRequests.add(tourStopImageRequest);
        tourStopImageRequests.add(tourStopImageRequest2);

        tourStopRequest.setImages(tourStopImageRequests);

        tourStopRequest.setCity("tourStopCity");
        tourStopRequest.setCountry("tourStopCountry");
        tourStopRequest.setStreet("tourStreet");
        tourStopRequest.setDescription("tourStopDescription");
        tourStopRequest.setId(1L);
        tourStopRequest.setTargetLat(11.11);
        tourStopRequest.setTargetLong(11.11);
        tourStopRequest.setTitle("tourStopTitle");

        tourStopRequests.add(tourStopRequest);
        tourRequest.setTourStopRequests(tourStopRequests);

        com.globati.dbmodel.Tour createdTour = tourService.createTour(tourRequest);

        tourRequest.setId(createdTour.getId());
        tourRequest.getImages().get(0).setImagePath("UPDATED");
        tourRequest.setTitle("UPDATED_TOUR_TITLE");
        tourRequest.getTourStopRequests().get(0).setTitle("UPDATED_TITLE");



        com.globati.dbmodel.Tour secondCreatedTour = tourService.updateTour(tourRequest);

        Assert.assertEquals("UPDATED", secondCreatedTour.getTourImages().get(0).getPath());
        Assert.assertEquals("UPDATED_TITLE", secondCreatedTour.getTourStops().get(0).getTitle());
        Assert.assertEquals("UPDATED_TOUR_TITLE", secondCreatedTour.getTitle());

        tourRequest.getTourStopRequests().get(0).getImages().set(0, new TourStopImageRequest(createdTour.getTourStops().get(0).getId(), "UPDATED_TOUR_STOP_IMAGE"));
        com.globati.dbmodel.Tour thirdCreatedTour = tourService.updateTour(tourRequest);

        Assert.assertEquals("UPDATED_TOUR_STOP_IMAGE", thirdCreatedTour.getTourStops().get(0).getTourStopImages().get(0).getPath());

    }
}
