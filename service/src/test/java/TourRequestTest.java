import com.globati.mysql.dbmodel.Property;
import com.globati.mysql.dbmodel.Tour;
import com.globati.request.tour.TourImageRequest;
import com.globati.request.tour.TourRequest;
import com.globati.request.tour.TourStopImageRequest;
import com.globati.request.tour.TourStopRequest;
import com.globati.service.PropertyService;
import com.globati.service.TourService;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import org.junit.Assert;
import org.junit.Before;
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
public class TourRequestTest extends SuperTest{

    @Autowired
    TourService tourService;

    @Autowired
    PropertyService propertyService;

    @Before
    public void setup() {

    }


    @Test
    public void testcreateTour() throws ServiceException, UserNameIsNotUniqueException {
        Property property = propertyService.createProperty(getUniquePropertyInstance());
        TourRequest tourRequest = getATourRequestWithGivenPropertyId(property.getId());

        List<TourImageRequest> images = new ArrayList<>();
        List<TourStopImageRequest> tourStopImageRequests = new ArrayList<>();

        tourStopImageRequests.add(getUniqueTourStopImageRequest());
        tourStopImageRequests.add(getUniqueTourStopImageRequest());

        List<TourStopRequest> tourStopRequests = new ArrayList<>();

        images.add(getUniqueTourImageRequest());
        images.add(getUniqueTourImageRequest());
        tourRequest.setTourImages(images);

        TourStopRequest tourStopRequest = getUniqueTourStopRequest();
        tourStopRequest.setTourStopImages(tourStopImageRequests);

        tourStopRequests.add(tourStopRequest);
        tourRequest.setTourStopRequests(tourStopRequests);

        com.globati.mysql.dbmodel.Tour createdTour = tourService.createTour(tourRequest);

        Assert.assertNotNull(createdTour);
        Assert.assertNotNull(createdTour.getTourStops());
        Assert.assertNotNull(createdTour.getTourStops().get(0).getTourStopImages().get(0).getImagePath());
    }

    @Test
    public void deleteTour() throws UserNameIsNotUniqueException {
        Property property = propertyService.createProperty(getUniquePropertyInstance());
        TourRequest tourRequest = getATourRequestWithGivenPropertyId(property.getId());

        List<TourImageRequest> images = new ArrayList<>();
        List<TourStopImageRequest> tourStopImageRequests = new ArrayList<>();

        tourStopImageRequests.add(getUniqueTourStopImageRequest());
        tourStopImageRequests.add(getUniqueTourStopImageRequest());

        List<TourStopRequest> tourStopRequests = new ArrayList<>();

        images.add(getUniqueTourImageRequest());
        images.add(getUniqueTourImageRequest());
        tourRequest.setTourImages(images);

        TourStopRequest tourStopRequest = getUniqueTourStopRequest();
        tourStopRequest.setTourStopImages(tourStopImageRequests);

        tourStopRequests.add(tourStopRequest);
        tourRequest.setTourStopRequests(tourStopRequests);

        com.globati.mysql.dbmodel.Tour createdTour = tourService.createTour(tourRequest);

        Tour deletedTour = tourService.setTourToInactive(createdTour.getId());

        Assert.assertNotNull(deletedTour);
        Assert.assertEquals(false, deletedTour.isActive());

    }


    @Test
    public void testGetToursByPropertyId() throws UserNameIsNotUniqueException, ServiceException {
        Property property = propertyService.createProperty(getUniquePropertyInstance());
        TourRequest tourRequest = getATourRequestWithGivenPropertyId(property.getId());
        List<TourImageRequest> images = new ArrayList<>();
        List<TourStopRequest> tourStopRequests = new ArrayList<>();
        List<TourStopImageRequest> tourStopImageRequests = new ArrayList<>();

        images.add(getUniqueTourImageRequest());
        images.add(getUniqueTourImageRequest());
        tourRequest.setTourImages(images);

        TourStopRequest tourStopRequest = getUniqueTourStopRequest();

        tourStopImageRequests.add(getUniqueTourStopImageRequest());

        tourStopRequest.setTourStopImages(tourStopImageRequests);

        tourStopRequests.add(tourStopRequest);
        tourRequest.setTourStopRequests(tourStopRequests);

        com.globati.mysql.dbmodel.Tour createdTour = tourService.createTour(tourRequest);

        List<com.globati.mysql.dbmodel.Tour> retrievedTours = tourService.getToursByPropertyId(property.getId());

        //Size of tours is 1
        Assert.assertEquals(1, retrievedTours.size());

        //Size of TourStops is 1
        Assert.assertEquals(1, retrievedTours.get(0).getTourStops().size());

        //Test that images are bigger than 1
        Assert.assertTrue(retrievedTours.get(0).getTourImages().size() > 0);
    }

    @Test
    public void testUpdateTour() throws UserNameIsNotUniqueException {
        Property property = propertyService.createProperty(getUniquePropertyInstance());
        TourRequest tourRequest = getATourRequestWithGivenPropertyId(property.getId());
        TourStopRequest tourStopRequest = getUniqueTourStopRequest();
        List<TourImageRequest> images = new ArrayList<>();
        List<TourStopRequest> tourStopRequests = new ArrayList<>();
        List<TourStopImageRequest> tourStopImageRequests = new ArrayList<>();

        //Add the data to the lists
        tourStopImageRequests.add(getUniqueTourStopImageRequest());
        images.add(getUniqueTourImageRequest());
        images.add(getUniqueTourImageRequest());
        tourStopRequests.add(tourStopRequest);

        //Set the data toe TourRequest
        tourRequest.setTourImages(images);
        tourStopRequest.setTourStopImages(tourStopImageRequests);
        tourRequest.setTourStopRequests(tourStopRequests);

        //Create the tour fromt the TourRequest
        com.globati.mysql.dbmodel.Tour createdTour = tourService.createTour(tourRequest);

        //Reuse the same tourRequest  but set data to the createdTour.
        tourRequest.setId(createdTour.getId());
        tourRequest.getTourImages().get(0).setImagePath("UPDATED");
        tourRequest.setTitle("UPDATED_TOUR_TITLE");
        tourRequest.getTourStopRequests().get(0).setTitle("UPDATED_TITLE");

        com.globati.mysql.dbmodel.Tour secondCreatedTour = tourService.updateTour(tourRequest);

        Assert.assertEquals("UPDATED", secondCreatedTour.getTourImages().get(0).getImagePath());
        Assert.assertEquals("UPDATED_TITLE", secondCreatedTour.getTourStops().get(0).getTitle());
        Assert.assertEquals("UPDATED_TOUR_TITLE", secondCreatedTour.getTitle());

        tourRequest.getTourStopRequests().get(0).getTourStopImages().set(0, new TourStopImageRequest(createdTour.getTourStops().get(0).getId(), "UPDATED_TOUR_STOP_IMAGE"));

        com.globati.mysql.dbmodel.Tour thirdCreatedTour = null;

        thirdCreatedTour = tourService.updateTour(tourRequest);

        String updatedPath = thirdCreatedTour.getTourStops().get(0).getTourStopImages().get(0).getImagePath();

        Assert.assertEquals("UPDATED_TOUR_STOP_IMAGE", updatedPath );

    }
}
