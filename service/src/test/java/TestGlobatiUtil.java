
import com.globati.config.Paths;
import com.globati.dbmodel.*;
import com.globati.service.DealService;
import com.globati.service.EmployeeService;
import com.globati.service.EventService;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.utildb.*;
import com.globati.utildb.HelpObjects.HashedPassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.globati.utildb.HelpObjects.Email;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.*;
import java.util.List;

/**
 * Created by daniel on 12/22/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TestGlobatiUtil {

    private static final Logger log = LogManager.getLogger(TestGlobatiUtil.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EventService eventService;

    @Autowired
    DealService dealService;

    @Test
    public void testSendSesMail() throws Exception {

        File file1 = new File( getClass().getClassLoader().getResource("test_resources/sweden.jpg").getFile() );
        InputStream fis1 = new FileInputStream(file1);

        String uid = UUID.randomUUID().toString();

        File file = new File(getClass().getClassLoader().getResource("test_resources/cbp.jpg").getFile());
        InputStream fis = new FileInputStream(file);
        Employee employee = employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 59.336019, 18.055262, "image", "2308 n 44 st", "seattle", "usa");

        List<String> li = new ArrayList<>();
        li.add("danielptm@me.com");
        li.add("daniel@globati.com");

        Email email = new Email(employee, li);

        Deal d = dealService.createDeal(fis1, "qqqqqqqqqq", "A deal description", "Name of business", 59.271283,18.102924, employee.get_id(), "q", "2308", "Seattle", "deal type", "globati.com", "daniel@globati.com","30 day", 30,"234", "billing","billing","billing","billing");

        Assert.assertTrue(SendMail.sendGuestMail(email));
        Assert.assertTrue(SendMail.sendReceipt(d));
        Assert.assertTrue(SendMail.sendRecruitmentMail(employee, "daniel@globati.com", "La neta"));
        Assert.assertTrue(SendMail.sendForgottenPasswordEmail("daniel@globati.com", "danie", "token" ));

    }



    @Test
    public void testRecommendationCheckPromixmity() throws FileNotFoundException, ServiceException, UserDoesNotExistException {
        String uid = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 59.336019, 18.055262, "image", "2308 n 44 st", "seattle", "usa");

        //true
        Recommendation recommendation = new Recommendation(employee,  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", "image1", "image2", "image3");

        //true
        Recommendation recommendation2 = new Recommendation(employee,  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", "image1", "image2", "image3");

        //false
        Recommendation recommendation3 = new Recommendation(employee,  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", "image1", "image2", "image3");


    }



    @Test
    public void sendMail() throws Exception {

        String uid = UUID.randomUUID().toString();

        File file = new File(getClass().getClassLoader().getResource("test_resources/cbp.jpg").getFile());
        InputStream fis = new FileInputStream(file);
        Employee employee = employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 59.336019, 18.055262, "image", "2308 n 44 st", "seattle", "usa");

        List<String> li = new ArrayList<>();
        li.add("danielptm@me.com");
        li.add("tuttleptm@gmail.com");

        Email email = new Email(employee, li);
        Assert.assertTrue(SendMail.sendGuestMail(email));
    }



    @Test
    public void date() throws ParseException {
        Date d = DateTools.getDate("2016-12-14");
        Assert.assertEquals("2016-12-14", d.toString());
    }


    @Test
    public void testPBKDF2() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String  originalPassword = "password";
        char [] charpass = originalPassword.toCharArray();
        byte[] salt = PBKDF2.getsalt();
        int iterations = 50000;

        HashedPassword hp = new HashedPassword(charpass, salt, iterations, 256);

        Assert.assertTrue( PBKDF2.hashPassword(charpass, salt, iterations, 256).length()>10 );

    }


    @Test
    public void testCheckPassword() throws FileNotFoundException, ServiceException, UserDoesNotExistException {
        String uid = UUID.randomUUID().toString();

        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        Employee employee = employeeService.createEmployee("check this2", uid+"@me.com", uid, "secret password", 59.336038, 18.055268, "image", "2308 n 44 st", "seattle", "usa");

        Employee employee1 =employeeService.getEmployeeById(employee.get_id());

        EmployeeInfo ei = new EmployeeInfo(employee1.get_id());

        EmployeeInfo ei2 = PBKDF2.hashEmployeePassword(ei, "secret password");

        Assert.assertTrue(PBKDF2.checkPassword(ei2, "secret password"));


    }

    @Test
    public void testCheckPasswordAndFail() throws FileNotFoundException, ServiceException, UserDoesNotExistException {

        String uid = UUID.randomUUID().toString();

        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        Employee employee = employeeService.createEmployee("check this2", uid+"@me.com", uid, "secret password", 59.336038, 18.055268, "image", "2308 n 44 st", "seattle", "usa");

        EmployeeInfo ei = new EmployeeInfo(employee.get_id());

        EmployeeInfo ei2 = PBKDF2.hashEmployeePassword(ei, "secret password");


        Assert.assertFalse(PBKDF2.checkPassword(ei2, "secret passwor"));

    }


    @Test
    public void testWriteImageToS3() throws IOException, GlobatiUtilException {

        File file = new File(getClass().getClassLoader().getResource("test_resources/faduma.jpg").getFile());

        InputStream fis = new FileInputStream(file);

        ImageHandler.createNewImage(fis);

    }

    @Test
    public void testDeleteImageFromS3(){

        String keyName = "daniel.jpg";

        ImageHandler.deleteFileFromS3(keyName);

    }

    @Test
    public void testPropertiesFile() throws IOException {
        InputStream is = new FileInputStream(new File(TestGlobatiUtil.class.getClassLoader().getResource("environment/development.properties").getFile()));
        Properties properties = new Properties();
        properties.load(is);

    }

    @Test
    public void testStaticBlock(){
        Paths.getActiveImageLink();
        Paths.getActiveCreateAddLink();
    }

    @Test
    public void testReplaceImage() throws IOException, GlobatiUtilException, ServiceException {
        File file = new File(getClass().getClassLoader().getResource("test_resources/faduma.jpg").getFile());
        InputStream fis = new FileInputStream(file);
        ImageHandler.createNewImage(fis);

        Assert.assertTrue(employeeService.replaceImage(1L, fis));

    }

    @Test
    public void testFacebookUserId(){
        String hi = "daniel";
        String fakeusername = FacebookUserId.generateFacebookUserId(hi);
        System.out.println(fakeusername);
        Assert.assertEquals(fakeusername.length(), 10);
    }
}
