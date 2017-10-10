import com.globati.dbmodel.*;
import com.globati.enums.Verified;
import com.globati.service.*;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service_beans.guest.EmployeeAndItems;
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

import java.io.*;
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

		Employee employee5 = this.employeeService.getEmployeeById(employee4.getId());


		Assert.assertEquals(employee4.getId(), employee5.getId());
	}

	@Test(expected = ServiceException.class)
	public void createEmployeeAndNotSucceedBecauseOfReservedWordForUsername() throws ServiceException, UserDoesNotExistException, FileNotFoundException {

		String uid = UUID.randomUUID().toString();

		File file = new File(getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile());
		InputStream fis = new FileInputStream(file);

		Employee employee4 = employeeService.createEmployee("check this2", uid+"@me.com", "London", "secret password", 59.336038, 18.055268, "image", "2308 n 44 st", "seattle", "usa");



	}

	@Test
	public void updateEmployee() throws ServiceException, FileNotFoundException, UserDoesNotExistException {
		String uid = UUID.randomUUID().toString();
		File file = new File(getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile());
		InputStream fis = new FileInputStream(file);
		Employee e = employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");


		Employee e2 = employeeService.getEmployeeById(e.getId());
		e2.setFirstName("zebra");
		Employee e3 = employeeService.updateEmployee(e2);
		Assert.assertEquals("zebra", employeeService.getEmployeeById(e3.getId()).getFirstName());
	}

	@Test(expected = ServiceException.class)
	public void attemptToUpdateEmployeeWithReservedWordAsUsername() throws FileNotFoundException, ServiceException, UserDoesNotExistException {

		String uid = UUID.randomUUID().toString();
		File file = new File(getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile());
		InputStream fis = new FileInputStream(file);

		//Do this just to create the employee
		Employee e = employeeService.createEmployee("Daniel", "er@gmail.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

		//Trick the method that this users
		e.setGlobatiUsername("London");
		employeeService.updateEmployee(e);
	}


	/**
	 * This tests to make sure that we owners of globati can update our profiles to reserved word.
	 * @throws FileNotFoundException
	 * @throws ServiceException
	 * @throws UserDoesNotExistException
	 */
	@Test
	public void allowAuserWithReservedWordToUpdateTheirProfile() throws FileNotFoundException, ServiceException, UserDoesNotExistException {

		String uid = UUID.randomUUID().toString();
		File file = new File(getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile());
		InputStream fis = new FileInputStream(file);
		Employee e = employeeService.createEmployee("Daniel", "owardbodie@gmail.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");
		e.setGlobatiUsername("London");
		e.setId(1L);
		employeeService.updateEmployee(e);
		Assert.assertEquals("London", e.getGlobatiUsername());

	}

	@Test
	public void getEmployeebyUserName() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException {
		File file = new File(getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile());

		String image1 = "image1 file";
		String image2 = "iamge2 file";
		String image3 = "image3 file";


		InputStream fis = new FileInputStream(file);
		InputStream fis2 = new FileInputStream(file);
		InputStream fis3 = new FileInputStream(file);
		InputStream fis4 = new FileInputStream(file);

		String uid = UUID.randomUUID().toString();

		Employee e1 = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");


		String uid2 = UUID.randomUUID().toString();
		Deal d = dealService.createDeal(image1, image2, image3, "qqqqqqqqqq", "A deal description", "Name of business", 23.23, 23.23, e1.getId(), "USA", "2308", "Seattle", "TOURISM", "globati.com", "daniel@me.com", "DAYS_30", 30, "234", "billing","billing","billing","billing");

		Date date = new Date();

		String uid3 = UUID.randomUUID().toString();
		Event e = eventService.createEvent(e1, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", "A description", "imageNam2", "imageName3");

		String uid4 = UUID.randomUUID().toString();
		Recommendation rec = recommendationService.createRecommendation(e1.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", "image1", "image2", "image3");


		//
		EmployeeAndItems items = employeeService.getItemsForEmployee(e1.getGlobatiUsername());


//		Assert.assertNotNull(items.getApiKey());
		Assert.assertNotNull(items.getEmployee());
		Assert.assertNotNull(items.getNearByDeals());


	}

	@Test
	public void inrementCounter() throws ServiceException, UserDoesNotExistException {

		String uid = UUID.randomUUID().toString();

		Employee e1 = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");

		Employee e2 = employeeService.incrementCounter(e1);

		Assert.assertEquals(1, e2.getVisitCounter().intValue());

		Employee e3 = employeeService.incrementCounter(e2);
		Assert.assertEquals(2, e3.getVisitCounter().intValue());

	}

	/**
	 *There is not unit test for getEmployeesByCountry()... But if this passes, you can be pretty sure the other will pass.
	 * @throws ServiceException
	 */
	@Test
	public void getEmployeesByCity() throws ServiceException, UserDoesNotExistException {
		String uid = UUID.randomUUID().toString();

		Employee e1 = this.employeeService.createEmployee("Daniel", uid+"@me.com", uid+"a", "secret password", 23.234, 23.23, "image", "2308 n 44 st", "xxx432", "usa");
		EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfoByEmployeeId(e1.getId());
		employeeInfo.set_verified(Verified.STANDARD);
		employeeInfoService.updateEmployeeInfo(employeeInfo);

		String uid2 = UUID.randomUUID().toString();

		Employee e2 = this.employeeService.createEmployee("Daniel", uid2+"@me.com", uid2+"b", "secret password", 23.234, 23.23, "image", "2308 n 44 st", "xxx432", "usa");
		EmployeeInfo employeeInfo1 = employeeInfoService.getEmployeeInfoByEmployeeId(e2.getId());
		employeeInfo1.set_verified(Verified.STANDARD);
		employeeInfoService.updateEmployeeInfo(employeeInfo1);

		String uid3 = UUID.randomUUID().toString();

		Employee e3 = this.employeeService.createEmployee("Daniel", uid3+"@me.com", uid3+"c", "secret password", 23.234, 23.23, "image", "2308 n 44 st", "yy3324", "usa");


		String uid4 = UUID.randomUUID().toString();

		Employee e4 = this.employeeService.createEmployee("Daniel", uid4+"@me.com", uid4+"a", "secret password", 23.234, 23.23, "image", "2308 n 44 st", "xxx432", "usa");
		EmployeeInfo employeeInfo4 = employeeInfoService.getEmployeeInfoByEmployeeId(e1.getId());
		employeeInfo.set_verified(null);
		employeeInfoService.updateEmployeeInfo(employeeInfo4);


		List<Employee> employees = employeeService.getEmployeesByCity("xxx432");

		List<Employee> employees2 = employeeService.getEmployeesByCity("yy3324");


		Assert.assertEquals(2, employees.size());

		Assert.assertEquals(0, employees2.size());


	}


	@Test
	public void getEmployeeByCountry(){

	}

	@Test
	public void createEmployeeWithFacebookInfo() throws ServiceException {

		String uid = UUID.randomUUID().toString();

		Employee employee = employeeService.createProfileFromFacebookInfo("FacebookEmployee", "Daniel", "emailaddress1@me.com","image/path");

		Assert.assertNotNull(employee);
		Assert.assertEquals(employee.getFirstName(), "Daniel");

	}

	@Test
	public void getEmployeeByFacebookId() throws FileNotFoundException, ServiceException, UserDoesNotExistException {
		String uid = UUID.randomUUID().toString();

		Employee employee = employeeService.createProfileFromFacebookInfo("FacebookEmployee1", "Daniel", "emailaddress2@me.com","image/path");

		Assert.assertNotNull(employee);

		EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfoByEmployeeId(employee.getId());

		Assert.assertEquals(employeeInfo.getEmployeeId(), employee.getId());

		Employee employee2 = employeeService.getEmployeeByFacebookId(employeeInfo.getFacebookId());


		Assert.assertNotNull(employee2);
		Assert.assertEquals(employee2.getFirstName(), "Daniel");

		Employee byFacebook = employeeService.getEmployeeByFacebookId(employeeInfo.getFacebookId());

		Assert.assertNotNull(byFacebook);
		Assert.assertEquals(byFacebook.getFirstName(), "Daniel");

	}

	@Test
	public void getEmployeeByFacebookIdAndFail() throws ServiceException {

		Employee employee = employeeService.getEmployeeByFacebookId("asdfasdf");


		Assert.assertNull(employee);


	}

	@Test
	public void loginWithFacebook() throws ServiceException, FileNotFoundException, UserDoesNotExistException, GlobatiUtilException {

		String image1 = "image1 file";
		String image2 = "iamge2 file";
		String image3 = "image3 file";

		String uid = UUID.randomUUID().toString();

		Employee employee = employeeService.createProfileFromFacebookInfo("FacebookEmployee2", "Daniel", "emailaddress3@me.com","image/path");


		File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );

		InputStream fis = new FileInputStream(file);

		Recommendation rec = recommendationService.createRecommendation(employee.getId(),  "title", "Description", 23.23, 23.23, "persikogatan", "stockholm", "Sweden", "image1", "image2", "image3");

		File file2 = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );

		InputStream fis2 = new FileInputStream(file2);

		Date date = new Date();

		Event e = eventService.createEvent(employee, date, 33.33, 33.33, "Regiringsgatan", "stockholm","sweden", "title", "description", "A description", "imageNam2", "imageName3");

		InputStream fis1 = new FileInputStream(file2);

		Deal d = dealService.createDeal(image1, image2, image3, "qqqqqqqqqq", "A deal description", "Name of business", 23.23,23.23, employee.getId(), "USA", "2308", "Seattle", "TOURISM", "globati.com", "daniel@me.com","DAYS_30", 30, "234", "billing","billing","billing","billing");

	}


	@Test
	public void userNameIsAReservedWord() throws ServiceException, IOException {
		String desiredName="London";
		String desieredName2="zebraface";
		String desieredName3="Vienna";


		Assert.assertTrue(employeeService.userNameIsAReservedWord(desiredName));
		Assert.assertFalse(employeeService.userNameIsAReservedWord(desieredName2));
		Assert.assertTrue(employeeService.userNameIsAReservedWord(desieredName3));
	}



}