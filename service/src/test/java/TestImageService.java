import com.globati.dbmodel.Property;
import com.globati.request.tour.BusinessImage;
import com.globati.request.tour.Tour;
import com.globati.request.tour.TourStop;
import com.globati.service.ImageService;
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
public class TestImageService {

    @Autowired
    ImageService imageService;

    @Autowired
    PropertyService propertyService;

    @Autowired
    TourService tourService;

    public String getRandomString() {
        return UUID.randomUUID().toString();
    }

    @Test
    public void testGetImagesByTourId() throws UserNameIsNotUniqueException, ServiceException {
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

        List<com.globati.dbmodel.BusinessImage> businessImages = imageService.getImagesByTourId(createdTour.getId());

        System.out.println("Business Images");
        System.out.println(businessImages.size());

        Assert.assertTrue(businessImages.size() > 0);

    }
}
