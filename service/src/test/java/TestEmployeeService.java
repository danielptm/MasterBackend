import com.globati.dbmodel.*;
import com.globati.service.*;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.utildb.GlobatiUtilException;
import com.globati.utildb.ImageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TestEmployeeService {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	EmployeeInfoService employeeInfoService;

	@Autowired
	private RecommendationService recommendationService;

	@Autowired
	DealService dealService;

	@Autowired
	EventService eventService;

	private static final Logger log = LogManager.getLogger(TestEmployeeService.class);



	@Test
	public void createEmployeeAndGetEmployeeById() throws ServiceException, FileNotFoundException, UserDoesNotExistException {
		String uid = UUID.randomUUID().toString();

		File file = new File(getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile());
		InputStream fis = new FileInputStream(file);

		Employee employee4 = employeeService.createEmployee("check this2", uid+"@me.com", uid, "secret password", 59.336038, 18.055268, "image", "2308 n 44 st", "seattle", "usa");

		Employee employee5 = this.employeeService.getEmployeeById(employee4.get_id());


		Assert.assertEquals(employee4.get_id(), employee5.get_id());
	}

	@Test
	public void updateEmployee() throws ServiceException, FileNotFoundException, UserDoesNotExistException {
		String uid = UUID.randomUUID().toString();
		File file = new File(getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile());
		InputStream fis = new FileInputStream(file);
		Employee e = employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");


		Employee e2 = employeeService.getEmployeeById(e.get_id());
		e2.set_firstName("zebra");
		Employee e3 = employeeService.updateEmployee(e2);
		Assert.assertEquals("zebra", employeeService.getEmployeeById(e3.get_id()).get_firstName());
	}

	@Test
	public void getEmployeebyUserName() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException {
		File file = new File(getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile());


		InputStream fis = new FileInputStream(file);
		InputStream fis2 = new FileInputStream(file);
		InputStream fis3 = new FileInputStream(file);
		InputStream fis4 = new FileInputStream(file);

		String uid = UUID.randomUUID().toString();

		Employee e1 = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");


		String uid2 = UUID.randomUUID().toString();
		Deal d = dealService.createDeal(fis2, "qqqqqqqqqq", "A deal description", "Name of business", 23.23, 23.23, e1.get_id(), "USA", "2308", "Seattle", "deal type", "globati.com", "daniel@me.com", "30 day", 30, "234", "billing","billing","billing","billing");

		Date date = new Date();

		String uid3 = UUID.randomUUID().toString();
		Event e = eventService.createEvent(e1, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", "A description", "imageNam2", "imageName3");

		String uid4 = UUID.randomUUID().toString();
		Recommendation rec = recommendationService.createRecommendation(e1.get_id(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", "image1", "image2", "image3");
//
//		List<Object> items = employeeService.getItemsForEmployee(e1.get_globatiUsername());

//		for (int i = 0; i < items.size(); i++) {
//			log.debug(items.get(i));
//		}


	}

	@Test
	public void getEmployeesByCity() throws ServiceException {
		List<Employee> employees = employeeService.getEmployeesByCity("seattle");

		List<Employee> employeelist = employeeService.getEmployeesByCountry("usa");

		log.debug(employees.size());

		log.debug(employeelist.size());

	}

	@Test
	public void createEmployeeWithFacebookInfo() throws ServiceException {

		String uid = UUID.randomUUID().toString();

		Employee employee = employeeService.createProfileFromFacebookInfo("FacebookEmployee", "Daniel", "emailaddress1@me.com","image/path");

		Assert.assertNotNull(employee);
		Assert.assertEquals(employee.get_firstName(), "Daniel");

	}

	@Test
	public void getEmployeeByFacebookId() throws FileNotFoundException, ServiceException, UserDoesNotExistException {
		String uid = UUID.randomUUID().toString();

		Employee employee = employeeService.createProfileFromFacebookInfo("FacebookEmployee1", "Daniel", "emailaddress2@me.com","image/path");

		Assert.assertNotNull(employee);

		EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfoByEmployeeId(employee.get_id());

		Assert.assertEquals(employeeInfo.get_employeeId(), employee.get_id());

		Employee employee2 = employeeService.getEmployeeByFacebookId(employeeInfo.get_facebookId());


		Assert.assertNotNull(employee2);
		Assert.assertEquals(employee2.get_firstName(), "Daniel");

		Employee byFacebook = employeeService.getEmployeeByFacebookId(employeeInfo.get_facebookId());

		Assert.assertNotNull(byFacebook);
		Assert.assertEquals(byFacebook.get_firstName(), "Daniel");

	}

	@Test
	public void getEmployeeByFacebookIdAndFail() throws ServiceException {

		Employee employee = employeeService.getEmployeeByFacebookId("asdfasdf");


		Assert.assertNull(employee);


	}

	@Test
	public void loginWithFacebook() throws ServiceException, FileNotFoundException, UserDoesNotExistException, GlobatiUtilException {

		String uid = UUID.randomUUID().toString();

		Employee employee = employeeService.createProfileFromFacebookInfo("FacebookEmployee2", "Daniel", "emailaddress3@me.com","image/path");


		File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );

		InputStream fis = new FileInputStream(file);

		Recommendation rec = recommendationService.createRecommendation(employee.get_id(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", "image1", "image2", "image3");

		File file2 = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );

		InputStream fis2 = new FileInputStream(file2);

		Date date = new Date();

		Event e = eventService.createEvent(employee, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", "A description", "imageNam2", "imageName3");

		InputStream fis1 = new FileInputStream(file2);

		Deal d = dealService.createDeal(fis1, "qqqqqqqqqq", "A deal description", "Name of business", 23.23,23.23, employee.get_id(), "USA", "2308", "Seattle", "deal type", "globati.com", "daniel@me.com","30day", 30, "234", "billing","billing","billing","billing");

//		List<Object> employeeAfterEditions =   employeeService.getItemsForEmployee(employee.get_globatiUsername());

//		Employee employee1 = (Employee) employeeAfterEditions.get(0);

//		Assert.assertEquals( employee1.get_deals().size() , 1);
//
//		Assert.assertEquals(employee1.get_recommendations().size(), 1);
//
//		Assert.assertEquals(employee1.get_events().size(), 1);


	}

}