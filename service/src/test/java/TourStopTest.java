import com.globati.dbmodel.BusinessImage;
import com.globati.dbmodel.Property;
import com.globati.dbmodel.TourStop;
import com.globati.enums.ImageType;
import com.globati.request.tour.Tour;
import com.globati.service.PropertyService;
import com.globati.service.TourService;
import com.globati.service.TourStopService;
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
public class TourStopTest {

    @Autowired
    TourStopService tourStopService;

    @Autowired
    PropertyService propertyService;

    @Autowired
    TourService tourService;

    public String getRandomString() {
        return UUID.randomUUID().toString();
    }

    @Test
    public void getTourStopsByTourId() throws UserNameIsNotUniqueException, ServiceException {
        Property property = propertyService.createProperty(
                "propertyName",
                getRandomString() + "@me.com",
                getRandomString() + "@me.com",
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

        Tour tour2 = new Tour();

        tour2.setPropertyId(property.getId());
        tour2.setCity("city");
        tour2.setCountry("country");
        tour2.setTitle("title");
        tour2.setTargetLat(11.11);
        tour2.setTargetLong(11.11);

        List<com.globati.request.tour.BusinessImage> images = new ArrayList<>();

        com.globati.request.tour.BusinessImage businessImage = new com.globati.request.tour.BusinessImage("path", "TOUR", 1);
        com.globati.request.tour.BusinessImage businessImage2 = new com.globati.request.tour.BusinessImage("path", "TOUR", 2);

        images.add(businessImage);
        images.add(businessImage2);

        tour.setImages(images);
        tour2.setImages(images);


        List<com.globati.request.tour.TourStop> tourStops = new ArrayList<>();

        com.globati.request.tour.TourStop tourStop = new com.globati.request.tour.TourStop();

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
        tour.setImages(images);

        com.globati.dbmodel.Tour createdTour = tourService.createTour(tour);

        List<TourStop> mappedTourStops = tourStopService.mapRequestTourStopsToDbModelTourStops(createdTour, tourStops);

        List<TourStop> createdStops = new ArrayList<>();

        for(TourStop ts: mappedTourStops) {
            TourStop savedTs = tourStopService.createTourStop(ts);
            createdStops.add(savedTs);
        }

        createdStops.forEach((ts) -> {
            // Test that the a list of TourStop is returned by the tourId.
            Assert.assertTrue(tourStopService.getTourStopsByTourId(ts.getTour().getId()).size() > 0);
        });
    }
}
