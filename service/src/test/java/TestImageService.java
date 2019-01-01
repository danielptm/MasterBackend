import com.globati.dbmodel.Property;
import com.globati.request.tour.TourImageRequest;
import com.globati.request.tour.TourRequest;
import com.globati.request.tour.TourStopRequest;
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
        TourRequest tourRequest = new TourRequest();

        tourRequest.setPropertyId(property.getId());
        tourRequest.setCity("city");
        tourRequest.setCountry("country");
        tourRequest.setTitle("title");
        tourRequest.setTargetLat(11.11);
        tourRequest.setTargetLong(11.11);

        List<TourImageRequest> images = new ArrayList<>();

        TourImageRequest tourImageRequest = new TourImageRequest("path");
        TourImageRequest tourImageRequest2 = new TourImageRequest("path");

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

        List<com.globati.dbmodel.TourImage> businessImages = imageService.getImagesByTourId(createdTour.getId());

        System.out.println("Business Images");
        System.out.println(businessImages.size());

        Assert.assertTrue(businessImages.size() > 0);

    }
}
