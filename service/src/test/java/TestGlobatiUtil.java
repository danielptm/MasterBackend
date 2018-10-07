
import com.globati.dbmodel.*;

import com.globati.mail.beans.GlobatiReminder;
import com.globati.service.*;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import com.globati.utildb.*;
import com.globati.HelpObjects.HashedPassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.globati.HelpObjects.Email;
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
    PropertyService propertyService;

    @Autowired
    PropertiesService propertiesService;


    @Autowired
    PropertyInfoService propertyInfoService;




    @Ignore
    public void testRecommendationCheckPromixmity() throws FileNotFoundException, ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        Property employee = propertyService.createProperty("Daniel",  uid+"@me.com", uid, "secret password", 59.336019, 18.055262, "image", "2308 n 44 st", "seattle", "usa");


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
    public void testCheckPassword() throws FileNotFoundException, ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();

        Property employee = propertyService.createProperty("check this2", uid+"@me.com", uid, "secret password", 59.336038, 18.055268, "image", "2308 n 44 st", "seattle", "usa");

        Property employee1 = propertyService.getPropertyById(employee.getId());

        PropertyInfo ei = new PropertyInfo(employee1.getId());

        PropertyInfo ei2 = PBKDF2.hashPropertyPassword(ei, "secret password");

        Assert.assertTrue(PBKDF2.checkPassword(ei2, "secret password"));


    }

    @Test
    public void testCheckPasswordAndFail() throws FileNotFoundException, ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {

        String uid = UUID.randomUUID().toString();

        Property employee = propertyService.createProperty("check this2", uid+"@me.com", uid, "secret password", 59.336038, 18.055268, "image", "2308 n 44 st", "seattle", "usa");

        PropertyInfo ei = new PropertyInfo(employee.getId());

        PropertyInfo ei2 = PBKDF2.hashPropertyPassword(ei, "secret password");


        Assert.assertFalse(PBKDF2.checkPassword(ei2, "secret passwor"));

    }


    @Ignore
    public void testWriteImageToS3() throws IOException, GlobatiUtilException {

        File file = new File(getClass().getClassLoader().getResource("test_resources/faduma.jpg").getFile());

        InputStream fis = new FileInputStream(file);

        ImageHandler.createNewImage(fis);

    }

    @Ignore
    public void testDeleteImageFromS3(){

        String keyName = "daniel.jpg";

        ImageHandler.deleteFileFromS3(keyName);

    }



    @Test
    public void testStaticBlock(){
        propertiesService.getImageBucket();
        propertiesService.getActiveCreateAddLink();
    }

    @Ignore
    public void testReplaceImage() throws IOException, GlobatiUtilException, ServiceException {
        File file = new File(getClass().getClassLoader().getResource("test_resources/faduma.jpg").getFile());
        InputStream fis = new FileInputStream(file);
        ImageHandler.createNewImage(fis);

        Assert.assertTrue(propertyService.replaceImage(1L, fis));

    }

    @Ignore
    public void testFacebookUserId(){
        String hi = "daniel";
        String fakeusername = FacebookUserId.generateFacebookUserId(hi);
        System.out.println(fakeusername);
        Assert.assertEquals(fakeusername.length(), 10);
    }



    @Test
    public void isValidDateFormat(){
        String validDate = "9999-99-99";
        String invalidDate = "22-22-22";

        Assert.assertTrue(DateTools.isValidDateFormat(validDate));
        Assert.assertFalse(DateTools.isValidDateFormat(invalidDate));

    }



    /**
     *
     * Tests getting a file from S3. If the file flightbookings.csv does not exist in the S3 bucket, then this test will
     * fail. So Ignore this test when building on server. Only use it for manually debugging.
     *
     * @throws Exception
     */
    @Ignore
    public void getBookingFromS3() throws Exception {
        File file = ImageHandler.getFlightBookingsFromS3();
        Assert.assertNotNull(file);
    }



    @Ignore
    public void getHotelBookingsFromS3() throws Exception {
        File file = ImageHandler.getHotelBookingsFromS3();
        Assert.assertNotNull(file);
    }

    @Ignore
    public void restReminder() throws Exception {
        String user = "CityBackpackers";
        String email = "danielptm@me.com";
        GlobatiReminder gm = new GlobatiReminder();
        SendMail.sendGlobatiReminder(user, email, gm.getMessage(user, "https://s3.eu-central-1.amazonaws.com/globatiimages/splash/posters/citybackpackers.jpg"));


    }
}
