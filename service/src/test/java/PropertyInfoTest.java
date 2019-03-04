import com.globati.mysql.dbmodel.Property;
import com.globati.mysql.dbmodel.PropertyInfo;
import com.globati.service.mysql.PropertyInfoService;
import com.globati.service.mysql.PropertyService;
import com.globati.service.mysql.exceptions.IllegalUserNameException;
import com.globati.service.mysql.exceptions.ServiceException;
import com.globati.service.mysql.exceptions.UserDoesNotExistException;
import com.globati.service.mysql.exceptions.UserNameIsNotUniqueException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Created by daniel on 1/17/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class PropertyInfoTest extends SuperTest{

    @Autowired
    PropertyService propertyService;

    @Autowired
    PropertyInfoService propertyInfoService;

    @Test
    public void testCreatePropertyInfo() throws ServiceException, UserNameIsNotUniqueException {
        Property employee = propertyService.createProperty(getUniquePropertyInstance());

        PropertyInfo e2 = propertyInfoService.getPropertyInfoByPropertyId(employee.getId());

        Assert.assertEquals(employee.getId(), e2.getPropertyId());

    }



    @Test
    public void testGetPropertyInfoByAuthToken() throws ServiceException, UserDoesNotExistException, UserNameIsNotUniqueException, IllegalUserNameException {

        String uid = UUID.randomUUID().toString();
        String uid2 = UUID.randomUUID().toString();
        Property employee = this.propertyService.createProperty(getUniquePropertyInstance());

        PropertyInfo propertyInfo = propertyInfoService.createPropertyInfo(employee.getId(), "hi");

        propertyInfo.setAuthToken(uid2);

        propertyInfoService.updatePropertyInfo(propertyInfo);

        PropertyInfo propertyInfo2 = propertyInfoService.getPropertyInfoByToken(uid2);

        Assert.assertEquals(propertyInfo.getId(), propertyInfo2.getId());

    }

}
