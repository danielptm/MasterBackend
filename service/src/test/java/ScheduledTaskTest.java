import com.globati.service.ScheduledTaskService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class ScheduledTaskTest {

    @Autowired
    ScheduledTaskService scheduledTaskService;

    @Ignore
    public void testSendWelcomeToGlobatiMail() throws Exception {
        scheduledTaskService.sendMarketingMail();
    }

}
