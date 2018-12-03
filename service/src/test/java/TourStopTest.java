import com.globati.dbmodel.TourStop;
import com.globati.service.TourStopService;
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
public class TourStopTest {

    @Autowired
    TourStopService tourStopService;

    @Test
    public void testMappingTourStops(){
        List<com.globati.request.tour.TourStop> tourStops = new ArrayList<>();



//        List<TourStop> tourStops = tourStopService.mapRequestTourStopsToDbModelTourStops();
    }
}
