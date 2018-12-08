import com.globati.dbmodel.Property;
import com.globati.dbmodel.TourStop;
import com.globati.request.tour.Tour;
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
        images.add("image");
        tour.setImages(images);

        tour.setTourStops(Arrays.asList(new com.globati.request.tour.TourStop()));

        tourService.createTour(tour);

        com.globati.dbmodel.Tour createdTour = tourService.getTourById(tour.getId());

        Assert.notNull(createdTour);
        Assert.notNull(createdTour.getTourImages());
        Assert.notNull(createdTour.getTourStops());
        Assert.notNull(createdTour.getCity());
        Assert.notNull(createdTour.getCountry());
        Assert.notNull(createdTour.getStreet());
        Assert.notNull(createdTour.getTitle());
        Assert.notNull(createdTour.getTargetLat());
        Assert.notNull(createdTour.getTargetLong());
        Assert.notNull(createdTour.getImage());
        Assert.notNull(createdTour.getDescription());


    }
}
