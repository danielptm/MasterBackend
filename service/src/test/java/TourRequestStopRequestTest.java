import com.globati.dbmodel.Property;
import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourStop;
import com.globati.dbmodel.TourStopImage;
import com.globati.request.tour.TourImageRequest;
import com.globati.request.tour.TourRequest;
import com.globati.request.tour.TourStopImageRequest;
import com.globati.request.tour.TourStopRequest;
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
public class TourRequestStopRequestTest extends SuperTest{

    @Autowired
    TourStopService tourStopService;

    @Autowired
    PropertyService propertyService;

    @Autowired
    TourService tourService;


    @Test
    public void getTourStopsByTourId() throws UserNameIsNotUniqueException, ServiceException {
        List<TourStopImageRequest> tourStopImageRequests = new ArrayList<>();
        List<TourImageRequest> images = new ArrayList<>();
        List<TourStopRequest> tourStopRequests = new ArrayList<>();

        Property property = propertyService.createProperty(getUniquePropertyInstance());
        TourRequest tourRequest = getATourRequestWithGivenPropertyId(property.getId());
        TourRequest tourRequest2 = getATourRequestWithGivenPropertyId(property.getId());

        TourImageRequest tourImageRequest = getUniqueTourImageRequest();
        TourImageRequest tourImageRequest2 = getUniqueTourImageRequest();

        TourStopImageRequest tourStopImageRequest = getUniqueTourStopImageRequest();
        TourStopImageRequest tourStopImageRequest2 = getUniqueTourStopImageRequest();

        images.add(tourImageRequest);
        images.add(tourImageRequest2);

        tourRequest.setTourImages(images);
        tourRequest2.setTourImages(images);

        tourStopImageRequests.add(tourStopImageRequest);
        tourStopImageRequests.add(tourStopImageRequest2);

        TourStopRequest tourStopRequest = getUniqueTourStopRequest();

        tourStopRequest.setTourStopImages(tourStopImageRequests);
        tourStopRequests.add(tourStopRequest);


        tourRequest.setTourStopRequests(tourStopRequests);
        tourRequest.setTourImages(images);

        com.globati.dbmodel.Tour createdTour = tourService.createTour(tourRequest);

        List<TourStop> mappedTourStops = tourStopService.mapRequestTourStopsToDbModelTourStops(createdTour, tourStopRequests);

        List<TourStop> retrievedTourStops = tourStopService.getTourStopsByTourId(createdTour.getId());
        
        retrievedTourStops.forEach((ts) -> {
            // Test that the a list of TourStopRequest is returned by the tourId.
            Assert.assertTrue(tourStopService.getTourStopsByTourId(ts.getTour().getId()).size() > 0);
        });
    }

    @Test
    public void testDeleteTourStop() throws ServiceException, UserNameIsNotUniqueException {
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

        com.globati.dbmodel.Tour createdTour = tourService.createTour(tourRequest);

        tourStopService.setTourStopToInactive(createdTour.getTourStops().get(0).getId());

        Tour updatedTour = tourService.getTourByTourId(createdTour.getId());


        Assert.assertNotNull(createdTour);
        Assert.assertEquals(0, updatedTour.getTourStops());
//        Assert.assertNotNull(createdTour.getTourStops().get(0).getTourStopImages().get(0).getImagePath());
    }

}
