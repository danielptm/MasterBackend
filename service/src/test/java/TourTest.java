import com.globati.request.tour.Tour;
import com.globati.service.TourService;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TourTest {

    @Autowired
    TourService tourService;

    @Test
    public void testcreateTour(){
        System.out.println("hi");
//        Tour tour = new Tour();
//        com.globati.dbmodel.Tour createdTour = tourService.createTour(tour);
//
//        Assert.notNull(createdTour);
    }
}
