import com.globati.dbmodel.Property;
import com.globati.dbmodel.TourStop;
import com.globati.request.tour.TourImageRequest;
import com.globati.request.tour.TourRequest;
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
        Property property = propertyService.createProperty(getUniquePropertyInstance());
        TourRequest tourRequest = new TourRequest();

        tourRequest.setPropertyId(property.getId());
        tourRequest.setCity("city");
        tourRequest.setCountry("country");
        tourRequest.setTitle("title");
        tourRequest.setTargetLat(11.11);
        tourRequest.setTargetLong(11.11);

        TourRequest tourRequest2 = new TourRequest();

        tourRequest2.setPropertyId(property.getId());
        tourRequest2.setCity("city");
        tourRequest2.setCountry("country");
        tourRequest2.setTitle("title");
        tourRequest2.setTargetLat(11.11);
        tourRequest2.setTargetLong(11.11);

        List<TourImageRequest> images = new ArrayList<>();

        TourImageRequest tourImageRequest = new TourImageRequest("path");
        TourImageRequest tourImageRequest2 = new TourImageRequest("path");

        images.add(tourImageRequest);
        images.add(tourImageRequest2);

        tourRequest.setImages(images);
        tourRequest2.setImages(images);


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
        tourRequest.setImages(images);

        com.globati.dbmodel.Tour createdTour = tourService.createTour(tourRequest);

        List<TourStop> mappedTourStops = tourStopService.mapRequestTourStopsToDbModelTourStops(createdTour, tourStopRequests);

        List<TourStop> createdStops = new ArrayList<>();

        for(TourStop ts: mappedTourStops) {
            TourStop savedTs = tourStopService.createTourStop(ts);
            createdStops.add(savedTs);
        }

        createdStops.forEach((ts) -> {
            // Test that the a list of TourStopRequest is returned by the tourId.
            Assert.assertTrue(tourStopService.getTourStopsByTourId(ts.getTour().getId()).size() > 0);
        });
    }
}
