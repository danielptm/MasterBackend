import com.globati.dbmodel.Property;
import com.globati.request.tour.Tour;
import com.globati.request.tour.TourStop;
import com.globati.service.PropertyService;
import com.globati.service.TourService;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TourTest {

    @Autowired
    TourService tourService;

    @Autowired
    PropertyService propertyService;


    @Test
    public void testcreateTour() throws ServiceException, UserNameIsNotUniqueException {
        Property property = propertyService.createProperty(
                "propertyName",
                        "email",
                "username",
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

        List<String> images = new ArrayList<>();
        images.add("image1");
        images.add("image2");

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

        Assert.notNull(createdTour);
    }

    @Test
    public void testGetToursByPropertyId() throws UserNameIsNotUniqueException, ServiceException {
        Property property = propertyService.createProperty(
                "propertyName",
                "email",
                "username",
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

        List<String> images = new ArrayList<>();
        images.add("image1");
        images.add("image2");

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
        Assert.isTrue(retrievedTours.size() == 1);
        //Size of TourStops is 1
        Assert.isTrue(retrievedTours.get(0).getTourStops().size() == 1);
    }
}
