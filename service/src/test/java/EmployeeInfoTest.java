import com.globati.dbmodel.Employee;
import com.globati.dbmodel.EmployeeInfo;
import com.globati.enums.Verified;
import com.globati.service.EmployeeInfoService;
import com.globati.service.EmployeeService;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import com.globati.service_beans.guest.EmployeeAndItems;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 1/17/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class EmployeeInfoTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeInfoService employeeInfoService;

    @Autowired
    PayService payService;

    @Test
    public void testGetEmployeeInfoByVerified() throws FileNotFoundException, ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();
        String uid2 = UUID.randomUUID().toString();

        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        Employee employee = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");
        EmployeeInfo e2 = employeeInfoService.getEmployeeInfoByEmployeeId(employee.getId());

        Employee employee2 = this.employeeService.createEmployee("Daniel", uid2+"@me.com", uid2, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");


        List<EmployeeInfo> e3 = employeeInfoService.getAllEmployeesByVerified(Verified.NOT);

        //Tests that 2 employee infos are returned
        Assert.assertTrue(e3.size()>1);


        //And tests that all are = to verified.NOT
        for(EmployeeInfo ei: e3){
            Assert.assertEquals(ei.get_verified(), Verified.NOT);
        }

    }

    @Test
    public void testCreateEmployeeInfo() throws FileNotFoundException, ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        Employee employee = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");
        EmployeeInfo e2 = employeeInfoService.getEmployeeInfoByEmployeeId(employee.getId());

        Assert.assertEquals(employee.getId(), e2.getEmployeeId());

    }

    @Test
    public void testVerified() throws FileNotFoundException, ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        Employee employee = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        EmployeeInfo e2 = employeeInfoService.getEmployeeInfoByEmployeeId(employee.getId());

        e2.set_verified(Verified.NOT);

        EmployeeInfo e3 = employeeInfoService.updateEmployeeInfo(e2);

        Assert.assertEquals(e3.get_verified(), Verified.NOT);

        e3.set_verified(Verified.STANDARD);

        EmployeeInfo e4= employeeInfoService.updateEmployeeInfo(e3);

        Assert.assertEquals(e4.get_verified(), Verified.STANDARD);


    }


    @Test
    public void testGetEmployeeInfoByAuthToken() throws FileNotFoundException, ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {

        String uid = UUID.randomUUID().toString();
        String uid2 = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        Employee employee = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        EmployeeInfo employeeInfo = employeeInfoService.createEmployeeInfo(employee.getId(), "hi");

        employeeInfo.setAuthToken(uid2);

        employeeInfoService.updateEmployeeInfo(employeeInfo);

        EmployeeInfo employeeInfo2 = employeeInfoService.getEmployeeInfoByToken(uid2);

        Assert.assertEquals(employeeInfo.getId(), employeeInfo2.getId());

    }


    /**
     *
     *
     * This test requires an internet connection and is ignored so that I can develop on the airplane. This test
     * and other tests which upload or take stuff form S3 should be put into their own test file. So that these types
     * of tests can easily be turned on and off.
     *
     * @throws ServiceException
     * @throws IOException
     * @throws UserDoesNotExistException
     * @throws UserNameIsNotUniqueException
     * @throws IllegalUserNameException
     */
    @Ignore
    public void testSendVerifiedUsers() throws ServiceException, IOException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
        String uid = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        Employee employee = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        employee.setPaypalEmail("UnitTestEmail");

        employeeService.updateEmployee(employee);
        EmployeeInfo e2 = employeeInfoService.getEmployeeInfoByEmployeeId(employee.getId());

        e2.set_verified(Verified.STANDARD);

        employeeInfoService.updateEmployeeInfo(e2);

//        ImageHandler.uploadVerifiedUsersToS3(payService.createCSVFileOfVerifiedUsersForBookings());

    }

    /**
     * This is not the greatest written test, because it should check that the apiktoken value is not null
     * not that the ApikToken object is not null.
     * @throws ServiceException
     * @throws UserDoesNotExistException
     */

    @Test
    public void createAccountOrLoginWithFacebook() throws ServiceException, UserDoesNotExistException, IOException, UserNameIsNotUniqueException, IllegalUserNameException {
        String facebookid="123";
        String name = "daniels";
        String email="hello@me.com";
        String image="234234234";

        String globatiusername ="zebsdfra";

        EmployeeInfo employeeInfo = this.employeeInfoService.getEmployeeInfoByFacebookId(facebookid);

        //EmplyoeeInfo has not been created
        Assert.assertNull(employeeInfo);

        employeeService.createAccountOrLoginWithFacebook(facebookid, name, email, image);

        EmployeeInfo employeeInfo1 = this.employeeInfoService.getEmployeeInfoByFacebookId(facebookid);

        Assert.assertNotNull(employeeInfo1);

        Employee employee = this.employeeService.getEmployeeByFacebookId(facebookid);

        employee.setGlobatiUsername(globatiusername);


        employeeService.updateEmployee(employee);

        EmployeeAndItems items = employeeService.createAccountOrLoginWithFacebook(facebookid, name, email, image);

//        Assert.assertNotNull(items.getNearByDeals());

        System.out.println(items.getEmployee().getFirstName());

        Assert.assertNotNull( items.getEmployee());











    }

}
