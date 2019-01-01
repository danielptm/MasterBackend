import com.globati.dbmodel.*;
import com.globati.enums.Verified;
import com.globati.service.*;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import com.globati.service_beans.guest.PropertyAndItems;
import com.globati.utildb.GlobatiUtilException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TestPropertyService extends SuperTest{

	@InjectMocks
	@Autowired
	private PropertyService propertyService;

	@Autowired
	PropertyInfoService propertyInfoService;

	@Autowired
	private RecommendationService recommendationService;

	private static final Logger log = LogManager.getLogger(TestPropertyService.class);

	String randomString;

	@Mock
	JwtService jwtService;

	Property commonProperty;


	@Before
	public void prep() throws UserNameIsNotUniqueException {
		String randomString = UUID.randomUUID().toString();
		MockitoAnnotations.initMocks(this);
		this.commonProperty = propertyService.createProperty(getUniquePropertyInstance());
	}


	@Test
	public void createPropertyAndGetPropertyById() throws ServiceException, FileNotFoundException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
		String randomString = UUID.randomUUID().toString();
		Property employee4 = propertyService.createProperty(getUniquePropertyInstance());
		Property employee5 = this.propertyService.getPropertyById(employee4.getId());
		Assert.assertEquals(employee4.getId(), employee5.getId());
	}

	@Ignore
	public void createPropertyAndNotSucceedBecauseOfReservedWordForUsername() throws ServiceException, UserDoesNotExistException, FileNotFoundException, UserNameIsNotUniqueException, IllegalUserNameException {
		String randomString = UUID.randomUUID().toString();
		Property employee4 = propertyService.createProperty(getUniquePropertyInstance());

	}

	@Test
	public void updateProperty() throws ServiceException, IOException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
		String randomString = UUID.randomUUID().toString();
		Property e = propertyService.createProperty(getUniquePropertyInstance());
		Property e2 = propertyService.getPropertyById(e.getId());
		e2.setFirstName("zebra");
		Property e3 = propertyService.updateProperty(e2);
		Assert.assertEquals("zebra", propertyService.getPropertyById(e3.getId()).getFirstName());
	}



	/**
	 * This tests to make sure that we owners of globati can update our profiles to reserved word.
	 *
	 * This will fail. If Not ignored.
	 *
	 * @throws FileNotFoundException
	 * @throws ServiceException
	 * @throws UserDoesNotExistException
	 */
	@Ignore
	public void allowAuserWithReservedWordToUpdateTheirProfile() throws IOException, ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
		String randomString = UUID.randomUUID().toString();
		Property e = propertyService.createProperty(getUniquePropertyInstance());
		e.setGlobatiUsername("London");
		propertyService.updateProperty(e);
		Assert.assertEquals("London", e.getGlobatiUsername());
	}

	@Test
	public void getPropertybyUserName() throws ServiceException, FileNotFoundException, GlobatiUtilException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
		String randomString = UUID.randomUUID().toString();
		List<String> images = new ArrayList<>();
		images.add("image1/url");
		images.add("image2/url");
		images.add("image3/url");

		when(jwtService.buildJwt(Mockito.anyString())).thenReturn("mockApiToken");

		Property e1 = this.propertyService.createProperty(getUniquePropertyInstance());
		PropertyAndItems items = propertyService.getItemsForProperty(e1.getGlobatiUsername());
		Assert.assertNotNull(items.getProperty());

	}

	@Test
	public void inrementCounter() throws ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {
		String uid = UUID.randomUUID().toString();
		Property e1 = this.propertyService.createProperty(getUniquePropertyInstance());
		Property e2 = propertyService.incrementCounter(e1);
		Assert.assertEquals(1, e2.getVisitCounter().intValue());
		Property e3 = propertyService.incrementCounter(e2);
		Assert.assertEquals(2, e3.getVisitCounter().intValue());
	}


	@Test
	public void userNameIsAReservedWord() throws ServiceException, IOException {
		String desiredName="London";
		String desieredName2="zebraFace";
		String desieredName3="Vienna";

		Assert.assertTrue(propertyService.userNameIsAReservedWord(desiredName));
		Assert.assertFalse(propertyService.userNameIsAReservedWord(desieredName2));
		Assert.assertTrue(propertyService.userNameIsAReservedWord(desieredName3));
	}



	@Test(expected = UserNameIsNotUniqueException.class)
	public void attempttoCreatePropertyButFailBecauseUsernameAlreadyExists() throws UserDoesNotExistException, UserNameIsNotUniqueException, ServiceException, IllegalUserNameException {
		Property e1 = this.propertyService.createProperty(getSingletonPropertyInstance());
		Property e2 = this.propertyService.createProperty(getSingletonPropertyInstance());
	}

	@Test
	public void getPropertyItemsFromFacebookLogin(){


	}

}