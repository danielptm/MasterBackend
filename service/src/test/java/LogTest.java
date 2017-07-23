import com.globati.service.DealService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by daniel on 1/17/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
@Ignore
public class LogTest {

    private static final Logger log = LogManager.getLogger(LogTest.class);

//    @Ignore
//    public void testLogger(){
//        log.debug("********** say hasdfas;ldkjasd;fkjads");
//
//    }
}
