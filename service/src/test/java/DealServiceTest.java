import com.globati.dbmodel.Deal;
import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Event;
import com.globati.service.DealService;
import com.globati.service.EmployeeService;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.utildb.GlobatiUtilException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by daniel on 12/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class DealServiceTest {

    private static final Logger log = LogManager.getLogger(DealServiceTest.class);


    @Autowired
    DealService dealService;

    @Autowired
    EmployeeService employeeService;

    /**
     * With the new ImageBuffer, it must do some thread stuff on the inputstreams because you cannot use the same inputstream, therefore i have created many.
     * @throws ServiceException
     * @throws FileNotFoundException
     * @throws GlobatiUtilException
     */

    @Test
    public void createDeal() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException {
        String uid = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/faduma.jpg").getFile() );
        InputStream fis = new FileInputStream(file);

        String image1 = "image1 file";
        String image2 = "iamge2 file";
        String image3 = "image3 file";

        File file1 = new File( getClass().getClassLoader().getResource("test_resources/sweden.jpg").getFile() );
        InputStream fis1 = new FileInputStream(file1);

        Employee employee = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        log.debug(employee.toString());

        Deal d = dealService.createDeal(image1, image3, image3, "qqqqqqqqqq", "A deal description", "Name of business", 23.23,23.23, employee.getId(), "USA", "2308", "Seattle", "deal type", "globati.com", "daniel@me.com","30day", 30, "234", "billing","billing","billing","billing");

        Assert.assertNotNull(d);
    }

    @Test
    public void updateDeal() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException {
        String uid = UUID.randomUUID().toString();
        String imageToReplace = "35/d0/cc-5b6e-4941-ab82-3b1881fc94d0image.png"; //Make sure this file exists, otherwise the test will fail!!!!

        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);

        File file1 = new File( getClass().getClassLoader().getResource("test_resources/sweden.jpg").getFile() );
        InputStream fis1 = new FileInputStream(file1);

        String image1 = "image1 file";
        String image2 = "iamge2 file";
        String image3 = "image3 file";

        Employee employee = this.employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");
        Deal d = dealService.createDeal(image1, image2, image3, "A title goes here", "A deal description", "Name of business", 23.23,23.23, employee.getId(), "USA", "2308", "Seattle", "deal type", "globati.com", "daniel@me.com", "30day", 30, "234", "billing","billing","billing","billing");
        Deal d2 = dealService.updateDeal(d);
        Assert.assertEquals(true, d2.isActive());

    }

    @Test
    public void getNearByDeals() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException {

        String image1 = "image1 file";
        String image2 = "iamge2 file";
        String image3 = "image3 file";

        String uid = UUID.randomUUID().toString();

        String uid2 = UUID.randomUUID().toString();

        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);

        File file4 = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis4 = new FileInputStream(file);

        File file1 = new File( getClass().getClassLoader().getResource("test_resources/sweden.jpg").getFile() );
        InputStream fis1 = new FileInputStream(file1);

        File file2 = new File( getClass().getClassLoader().getResource("test_resources/sweden.jpg").getFile() );
        InputStream fis2 = new FileInputStream(file1);

        File file3 = new File( getClass().getClassLoader().getResource("test_resources/sweden.jpg").getFile() );
        InputStream fis3 = new FileInputStream(file1);

        Employee employee = employeeService.createEmployee("Daniel",  uid+"@me.com", uid, "secret password", 59.336626, 18.055053, "image", "2308 n 44 st", "seattle", "usa");

        Employee employee2 = employeeService.createEmployee("Daniel",  uid2+"@me.com", uid2, "secret password", 59.336626, 18.055053, "image", "2308 n 44 st", "seattle", "usa");


        Deal d = dealService.createDeal(image1, image2, image3, "qqqqqqqqqq", "A deal description", "Name of business", 59.271283,18.102924, employee.getId(), "q", "2308", "Seattle", "deal type", "globati.com", "daniel@me.com","30day", 30,"234", "billing","billing","billing","billing");

        Deal d2 = dealService.createDeal(image1, image2, image3, "qqqqqqqqqq", "A deal description", "Name of business", 59.232557,18.131736, employee.getId(), "q", "2308", "Seattle", "deal type", "globati.com", "daniel@me.com","30day", 30,"234", "billing","billing","billing","billing");

        Deal d3 = dealService.createDeal(image1, image2, image3, "qqqqqqqqqq", "A deal description", "Name of business", 58.902919,17.948099, employee2.getId(), "q", "2308", "Seattle", "deal type", "globati.com", "daniel@me.com","30 day", 30,"234", "billing","billing","billing","billing");

        List<Deal> testarray = dealService.getNearbyActiveDeals("q", employee.getId());

        for(Deal deal:testarray){
            Assert.assertTrue( 7.75730390360477== deal.getNonRecruiterDistance() | 12.364386246574913== deal.getNonRecruiterDistance() );
        }
    }

    @Test
    public void getActiveDealsBCountry() throws ServiceException {

        List<Deal> deals = this.dealService.getActiveDealsByCountry("sweden");

        log.debug("size: "+deals.size());

        for(Deal deal: deals){
            log.debug(deal.toString());
            Assert.assertTrue(deal.isActive());
            Assert.assertTrue(deal.getCountry().equals("sweden"));
        }
    }


    @Test
    public void getActiveDealsByCountryAndEmployee() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException {

        String image1 = "image1 file";
        String image2 = "iamge2 file";
        String image3 = "image3 file";

        String uid = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/faduma.jpg").getFile() );
        InputStream fis = new FileInputStream(file);

        File file1 = new File( getClass().getClassLoader().getResource("test_resources/sweden.jpg").getFile() );
        InputStream fis1 = new FileInputStream(file1);

        Employee employee = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        log.debug(employee.toString());

        Deal d = dealService.createDeal(image1, image2, image3, "qqqqqqqqqq", "A deal description", "Name of business", 23.23,23.23, employee.getId(), "USA", "2308", "Seattle", "deal type", "globati.com", "daniel@me.com","30 day", 30, "234", "billing","billing","billing","billing" );


        List<Deal> deals = dealService.getActiveDealsByEmployee(employee.getId());

        for(Deal deal:deals){
            Assert.assertTrue(deal.isActive());
            Assert.assertTrue(deals.size()==1);
        }
    }

    /**
     *
     * As of now this test relies on data being in the database already, I think this test might be unnecessary anyway
     *
     * @throws ServiceException
     */
    @Ignore
    public void getActiveDealsByMonthCreated() throws ServiceException, FileNotFoundException, UserDoesNotExistException, GlobatiUtilException {


        String image1 = "image1 file";
        String image2 = "iamge2 file";
        String image3 = "image3 file";


        String uid = UUID.randomUUID().toString();

        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);


        Employee employee = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        File file1 = new File( getClass().getClassLoader().getResource("test_resources/sweden.jpg").getFile() );
        InputStream fis1 = new FileInputStream(file1);

        Deal d = dealService.createDeal(image1, image2, image3, "qqqqqqqqqq", "A deal description", "Name of business", 23.23,23.23, employee.getId(), "USA", "2308", "Seattle", "deal type", "globati.com", "daniel@me.com","30 day", 30, "234", "billing","billing","billing","billing" );

        List<Deal> deals = dealService.getDealsCreatedByMonth(1, 2017, 1L);
        log.debug(deals.size());
        for(Deal deal:deals){
            Assert.assertTrue(deal.getDatemade().getYear()+1900==2017);
            Assert.assertTrue(deal.getDatemade().getMonth()==0);
        }
    }
}

