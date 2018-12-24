import com.globati.dbmodel.Property;
import com.globati.request.tour.BusinessImage;
import com.globati.request.tour.Tour;
import com.globati.request.tour.TourStop;
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
public class TourTest {

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
        Tour tour = new Tour();

        tour.setPropertyId(property.getId());
        tour.setCity("city");
        tour.setCountry("country");
        tour.setTitle("title");
        tour.setTargetLat(11.11);
        tour.setTargetLong(11.11);

        List<BusinessImage> images = new ArrayList<>();

        com.globati.request.tour.BusinessImage businessImage = new com.globati.request.tour.BusinessImage("path", "TOUR", 1);
        com.globati.request.tour.BusinessImage businessImage2 = new com.globati.request.tour.BusinessImage("path", "TOUR", 2);

        images.add(businessImage);
        images.add(businessImage2);

        tour.setImages(images);

        List<TourStop> tourStops = new ArrayList<>();

        TourStop tourStop = new TourStop();

        tourStop.setCity("tourStopCity");
        tourStop.setCountry("tourStopCountry");
        tourStop.setStreet("tourStreet");
        tourStop.setDescription("tourStopDescription");
        tourStop.setId(1L);
        tourStop.setTargetLat(11.11);
        tourStop.setTargetLong(11.11);
        tourStop.setTitle("tourStopTitle");

        tourStops.add(tourStop);
        tour.setTourStops(tourStops);


        com.globati.dbmodel.Tour createdTour = tourService.createTour(tour);

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
        Tour tour = new Tour();

        tour.setPropertyId(property.getId());
        tour.setCity("city");
        tour.setCountry("country");
        tour.setTitle("title");
        tour.setTargetLat(11.11);
        tour.setTargetLong(11.11);

        List<BusinessImage> images = new ArrayList<>();

        com.globati.request.tour.BusinessImage businessImage = new com.globati.request.tour.BusinessImage("path", "TOUR", 1);
        com.globati.request.tour.BusinessImage businessImage2 = new com.globati.request.tour.BusinessImage("path", "TOUR", 2);

        images.add(businessImage);
        images.add(businessImage2);

        tour.setImages(images);


        List<TourStop> tourStops = new ArrayList<>();

        TourStop tourStop = new TourStop();

        tourStop.setCity("tourStopCity");
        tourStop.setCountry("tourStopCountry");
        tourStop.setStreet("tourStreet");
        tourStop.setDescription("tourStopDescription");
        tourStop.setId(1L);
        tourStop.setTargetLat(11.11);
        tourStop.setTargetLong(11.11);
        tourStop.setTitle("tourStopTitle");

        tourStops.add(tourStop);
        tour.setTourStops(tourStops);


        com.globati.dbmodel.Tour createdTour = tourService.createTour(tour);

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
        Tour tour = new Tour();

        tour.setPropertyId(property.getId());
        tour.setCity("city");
        tour.setCountry("country");
        tour.setTitle("title");
        tour.setTargetLat(11.11);
        tour.setTargetLong(11.11);

        List<BusinessImage> images = new ArrayList<>();

        com.globati.request.tour.BusinessImage businessImage = new com.globati.request.tour.BusinessImage("path", "TOUR", 1);
        com.globati.request.tour.BusinessImage businessImage2 = new com.globati.request.tour.BusinessImage("path", "TOUR", 2);

        images.add(businessImage);
        images.add(businessImage2);

        tour.setImages(images);

        List<TourStop> tourStops = new ArrayList<>();

        TourStop tourStop = new TourStop();

        tourStop.setCity("tourStopCity");
        tourStop.setCountry("tourStopCountry");
        tourStop.setStreet("tourStreet");
        tourStop.setDescription("tourStopDescription");
        tourStop.setId(1L);
        tourStop.setTargetLat(11.11);
        tourStop.setTargetLong(11.11);
        tourStop.setTitle("tourStopTitle");

        tourStops.add(tourStop);
        tour.setTourStops(tourStops);

        com.globati.dbmodel.Tour createdTour = tourService.createTour(tour);

        tour.setId(createdTour.getId());
        tour.getImages().get(0).setImagePath("UPDATED");
        tour.setTitle("UPDATED_TOUR_TITLE");
        tour.getTourStops().get(0).setTitle("UPDATED_TITLE");

        com.globati.dbmodel.Tour secondCreatedTour = tourService.updateTour(tour);

        Assert.assertEquals("UPDATED", secondCreatedTour.getTourImages().get(0).getPath());
        Assert.assertEquals("UPDATED_TITLE", secondCreatedTour.getTourStops().get(0).getTitle());
        Assert.assertEquals("UPDATED_TOUR_TITLE", secondCreatedTour.getTitle());

    }
}
