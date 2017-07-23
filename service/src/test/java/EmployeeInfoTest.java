import com.globati.dbmodel.Employee;
import com.globati.dbmodel.EmployeeInfo;
import com.globati.service.EmployeeInfoService;
import com.globati.service.EmployeeService;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    @Test
    public void testCreateEmployeeInfo() throws FileNotFoundException, ServiceException, UserDoesNotExistException {
        String uid = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        Employee employee = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

    }


    @Test
    public void testGetEmployeeInfoByAuthToken() throws FileNotFoundException, ServiceException, UserDoesNotExistException {

        String uid = UUID.randomUUID().toString();
        String uid2 = UUID.randomUUID().toString();
        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
        InputStream fis = new FileInputStream(file);
        Employee employee = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

        EmployeeInfo employeeInfo = employeeInfoService.createEmployeeInfo(employee.get_id(), "hi");

        employeeInfo.set_authToken(uid2);

        employeeInfoService.updateEmployeeInfo(employeeInfo);

        EmployeeInfo employeeInfo2 = employeeInfoService.getEmployeeInfoByToken(uid2);

        Assert.assertEquals(employeeInfo.get_id(), employeeInfo2.get_id());

    }
}
