package com.globati.service;


import com.globati.HelpObjects.ApiKey;
import com.globati.mysql.dbmodel.*;
import com.globati.mysql.enums.Verified;
import com.globati.repository.PropertyRepository;
import com.globati.request.CreateProperty;
import com.globati.service.exceptions.IllegalUserNameException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import com.globati.service_beans.guest.PropertyAndItems;
import com.globati.utildb.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel on 12/21/16.
 */

@Service
public class PropertyService {

    private static final Logger log = LogManager.getLogger(PropertyService.class);


    @Autowired
    PropertiesService propertiesService;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    RecommendationService recommendationService;


    @Autowired
    PropertyInfoService propertyInfoService;

    @Autowired
    JwtService jwtService;

    @Autowired
    TourService tourService;


    PropertyService() {
    }


    public Property getPropertyById(Long id) throws ServiceException {
        log.info("getPropertyById(): " + id);
        try {
            Property employee = this.propertyRepository.getPropertyByid(id);
            employee.setRecommendations(null);
            employee.setTours(null);
            return employee;
        } catch (Exception e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getPropertyById(Long id)");
            e.printStackTrace();
            throw new ServiceException("Was not able to retrieve employee by id: " + id, e);
        }
    }

    public Property getPropertyByUserName(String username) throws ServiceException {
        log.info("getPropertyByUserName(): username: " + username);
        try {
            Property employee = propertyRepository.getPropertyByGlobatiUsername(username);
            employee.setRecommendations(new ArrayList<>());
            employee.setTours(new ArrayList<>());
            return employee;
        } catch (Exception e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getPropertyByUserName()");
            e.printStackTrace();
            throw new ServiceException("Could not retrieve employee by username", e);
        }
    }


    /**
     * Gets an employee and all of the items, recommendations, deals, events associated with that employee;
     * <p>
     * The arraylist returned is the size of 2. The first element is the above mentioend employee, the second item in the array is the nearby deals.
     *
     * @param username
     * @return
     * @throws ServiceException
     */
    public PropertyAndItems getItemsForProperty(String username) throws ServiceException {
        log.info("getItemsForProperty(): username: " + username);
        ArrayList<Object> items = new ArrayList<>();
        try {
            Property employee = propertyRepository.getPropertyByGlobatiUsername(username);
            PropertyInfo propertyInfo = propertyInfoService.getPropertyInfoByPropertyId(employee.getId());

            if (employee == null) {
                throw new Exception("this username could not be found");
            }

            List<Recommendation> recommendations = recommendationService.getRecommendationByPropertyId(employee.getId());
            List<Tour> tours = tourService.getToursByPropertyId(employee.getId());
            employee.setRecommendations(recommendations);
            employee.setTours(tours);
            PropertyAndItems employeeAndItems = new PropertyAndItems(employee);
            employeeAndItems.setApiKey(jwtService.buildJwt(propertyInfo.getAuthToken()));

            return employeeAndItems;
        } catch (Exception e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getItemsForProperty()");
            e.printStackTrace();
            throw new ServiceException("Could not retrieve employee by user name and pasword", e);
        }
    }

    public Property incrementCounter(Property employee) {
        log.info("incrementConter(): employeeId: " + employee.getId());
        if (employee.getVisitCounter() == null) {
            employee.setVisitCounter(1);
        } else {
            Integer newValue = employee.getVisitCounter();
            newValue++;
            employee.setVisitCounter(newValue);
        }
        return employee;
    }

    /**
     * Creates a new employee
     * @throws ServiceException org.hibernate.exception.ConstraintViolationException
     */


    public Property createProperty(CreateProperty createProperty) throws UserNameIsNotUniqueException{
        Property employee = null;
        Property savedProperty = null;
        try {
            employee = new Property(
                    createProperty.getFirstName(),
                    createProperty.getEmail(),
                    createProperty.getUsername(),
                    createProperty.getTargetLat(),
                    createProperty.getTargetLong(),
                    createProperty.getImage(),
                    createProperty.getStreet(),
                    createProperty.getCity(),
                    createProperty.getCountry(),
                    Verified.STANDARD);
            employee.setRecommendations(new ArrayList<>());
            savedProperty = propertyRepository.save(employee);
            propertyInfoService.createPropertyInfo(savedProperty.getId(), createProperty.getPassword());
            return savedProperty;
        } catch (DataIntegrityViolationException e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: createProperty()");
            e.printStackTrace();
            throw new UserNameIsNotUniqueException("A username that is already used was attempted to be created a profile with: " + createProperty.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return savedProperty;
    }

    /**
     * Updates an employee, but does a check on globati username, if the username is a reserved word
     * or exists already in the database, an exception is thrown.
     * <p>
     * If uncomment this comment, this will
     *
     * @param employee
     * @return
     * @throws ServiceException
     * @throws UserDoesNotExistException
     */

    public Property updateProperty(Property employee) throws ServiceException, UserDoesNotExistException, IOException, IllegalUserNameException, UserNameIsNotUniqueException {
        try {
            return this.propertyRepository.save(employee);
        } catch (DataIntegrityViolationException e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: createProperty()");
            e.printStackTrace();
            throw new UserNameIsNotUniqueException("A username that is already used was attempted to be created a profile with: " + employee.getGlobatiUsername());
        } catch (Exception e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: updateProperty()");
            e.printStackTrace();
            throw e;
        }
    }

    public boolean replaceImage(Long id, InputStream is) throws ServiceException {
        log.info("replaceImage(): id: " + id);
        Property employee = null;
        try {
            employee = getPropertyById(id);
            ImageHandler.deleteFileFromS3(employee.getImage());
        } catch (Exception e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: replaceImage()");
            e.printStackTrace();
            throw new ServiceException("Could not retrieve employee with id: " + employee.getId(), e);
        } finally {
            //Creates
            String path = propertiesService.getS3Root() + propertiesService.getImageBucket();
            try {
                path += ImageHandler.createNewImage(is);
                employee.setImage(path);
                updateProperty(employee);
            } catch (Exception e) {
                log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: replaceImage()");
                e.printStackTrace();
                throw new ServiceException("Could not create new image for employee: " + employee.toString(), e);
            }
            return true;
        }
    }


    /**
     * This method is the same as getItemsForProperty() but it does not get the JWT. This is necessary for listing
     * other employees in the admin page.
     * @param username
     * @return
     * @throws ServiceException
     */

    public PropertyAndItems getItemsForPropertyButNoWebToken(String username) throws ServiceException {

        ArrayList<Object> items = new ArrayList<>();
        try {
            Property employee = propertyRepository.getPropertyByGlobatiUsername(username);

            if (employee == null) {
                throw new Exception("this username could not be found");
            }

            List<Recommendation> recommendations = recommendationService.getRecommendationByPropertyId(employee.getId());

            employee.setRecommendations(recommendations);
            employee.setTours(new ArrayList<>());

            PropertyAndItems employeeAndItems = new PropertyAndItems(employee);

            return employeeAndItems;
        } catch (Exception e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getItemsForProperty()");
            e.printStackTrace();
            throw new ServiceException("Could not retrieve employee by user name and pasword", e);
        }


    }


    /**
     * Returns true if the password is successfully changed.
     *
     * @param employeeId
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws ServiceException
     */
    public boolean changePassword(Long employeeId, String oldPassword, String newPassword) throws ServiceException {
        log.info("chnagePassword(): employeeId: " + employeeId);
        try {
            PropertyInfo propertyInfo = propertyInfoService.getPropertyInfoByPropertyId(employeeId);
            if (PBKDF2.checkPassword(propertyInfo, oldPassword)) {
                PropertyInfo info = PBKDF2.hashPropertyPassword(propertyInfo, newPassword);
                propertyInfoService.updatePropertyInfo(info);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: changePassword()");
            e.printStackTrace();
            throw new ServiceException("Could not change password at this time " + employeeId, e);
        }
    }

    public boolean changePasswordWithToken(String token, String password) throws ServiceException {
        log.info("changePasswordWithToken(): token: " + token);
        try {
            PropertyInfo propertyInfo = propertyInfoService.getPropertyInfoByToken(token);
            if (Long.parseLong(propertyInfo.getTokenExpiration()) > System.currentTimeMillis()) {
                PropertyInfo info = PBKDF2.hashPropertyPassword(propertyInfo, password);
                propertyInfoService.updatePropertyInfo(info);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: changePasswordWithToken()");
            e.printStackTrace();
            throw new ServiceException("Could not change password at this time ", e);
        }
    }

    /**
     * This does not use the email supplied in the form, but uses the email
     * to find the employee by email. This is problematic because email is not unique in the database,
     * so if a sombody creates multiple accounts with the same email and tries to reset their password
     * this function will fail, and the user will not receive an email.
     * <p>
     * This seems like kind of an unlikely problem, but if it appears
     *
     * @param email
     * @return
     * @throws ServiceException
     */
    public boolean sendEmailToChangePassword(String email) throws Exception {
        log.info("sendEmailToChangePassword(): email: " + email);
        try {
            log.debug("service sendEmailToChangePassword() " + email);
            ApiKey api = new ApiKey();
            Property employee = propertyRepository.getPropertyByEmail(email);
            PropertyInfo propertyInfo = propertyInfoService.getPropertyInfoByPropertyId(employee.getId());
            propertyInfo.setAuthToken(api.getApiKey());
            propertyInfo.setTokenExpiration(api.getTime());
            propertyInfoService.updatePropertyInfo(propertyInfo);
            return SendMail.sendForgottenPasswordEmail(employee.getEmail(), employee.getGlobatiUsername(), propertyInfo.getAuthToken());
        } catch (Exception e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: sendEmailToChangePassword()");
            e.printStackTrace();
            throw new ServiceException("Could not send employee info", e);
        }
    }

    /**
     * This function was moved from the Web third_party_api level to the service class.
     * <p>
     * There are no tests for this function, and was copied exactly from the Web third_party_api level, except for it returns now List<ApiKey>
     * In the web third_party_api level it returned Response.ok(item).build();
     * <p>
     * This function needs a test!
     *
     * @param userName
     * @param password
     * @return
     * @throws ServiceException
     */
    public PropertyAndItems authenticateReceptionist(String userName, String password) throws ServiceException {
        log.info("authenticateRecptionist(): username: " + userName);
        try {
            String username = userName;
            String passwordAttempt = password;

            Property employee = getPropertyByUserName(username);

            employee.setRecommendations(new ArrayList<>());
            employee.setTours(new ArrayList<>());

            PropertyAndItems employeeAndItems = new PropertyAndItems(employee);

            PropertyInfo propertyInfo = propertyInfoService.getPropertyInfoByPropertyId(employee.getId());

            if (PBKDF2.checkPassword(propertyInfo, passwordAttempt)) {
                propertyInfo.setLastLogin(new Date());
                ApiKey apiKey = new ApiKey();
                propertyInfo.setAuthToken(apiKey.getApiKey());
                propertyInfo.setTokenExpiration(apiKey.getTime());
                String jwt = jwtService.buildJwt(apiKey.getApiKey());
                employeeAndItems.setApiKey(jwt);
                propertyInfoService.updatePropertyInfo(propertyInfo);
                return employeeAndItems;
            } else {
                throw new ServiceException("Could not verify user: " + username);
            }
        } catch (ServiceException e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: authenticateReceptionist()");
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * No unit test written for this. This is only called by getItemsForPropertyAndIncrement().
     * Maybe just put it there instead. It is only used for updating an employee when not making the
     * isValidusername check. Which is used when
     */
    private Property updatePropertyWithoutCheckingUsername(Property employee) {
        return this.propertyRepository.save(employee);
    }

    public static boolean userNameIsAReservedWord(String desiredUserName) throws ServiceException, IOException {
        boolean isAReservedKeyWord = false;
        BufferedReader br = null;
        String lowerCasedDesiredName = desiredUserName.toLowerCase();
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource("reserved_words/ListOfReservedWords.txt").getFile());
            br = new BufferedReader(new FileReader(file));
            String reservedWordFromList = "";
            while (reservedWordFromList != null) {
                reservedWordFromList = br.readLine();
                if (reservedWordFromList != null && reservedWordFromList.equals(lowerCasedDesiredName)) {
                    isAReservedKeyWord = true;
                    break;
                }
            }

        } catch (FileNotFoundException e1) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: userNameIsAReservedWord()");
            e1.printStackTrace();
            throw new ServiceException("Could not calculate if the username is a reserved word for word:" + desiredUserName, e1);
        } catch (IOException e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: userNameIsAReservedWord()");
            e.printStackTrace();
            throw new ServiceException("Could not calculate if the username is a reserved word for word:" + desiredUserName, e);
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return isAReservedKeyWord;
    }

    public Property incrementWebsiteCounter(Long id) throws ServiceException {
        try {
            Property employee = propertyRepository.getPropertyByid(id);
            employee.setRecommendations(null);
            Integer newValue = employee.getVisitCounter();
            newValue++;
            employee.setVisitCounter(newValue);
            return propertyRepository.save(employee);
        }catch(Exception e) {
            throw new ServiceException("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: incrementWebsiteCounter()");
        }
    }

    public Property incrementMobileCounter(Long id) throws ServiceException {
        try {
            Property employee = propertyRepository.getPropertyByid(id);
            employee.setRecommendations(null);
            Integer newValue = employee.getMobileVisitCounter();
            newValue++;
            employee.setMobileVisitCounter(newValue);
            return propertyRepository.save(employee);
        } catch (Exception e) {
            throw new ServiceException("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: incrementMobileCounter()" );
        }
    }

    public Property getPropertyByIdWithRecommdations(String username) throws ServiceException {
        try {
            Property employee = getPropertyByUserName(username);
            List<Recommendation> recommendations = recommendationService.getRecommendationByPropertyId(employee.getId());
            employee.setRecommendations(recommendations);
            employee.setTours(new ArrayList<>());
            return employee;
        } catch (Exception e) {
            throw new ServiceException("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getPropertyByIdWithRecommendations");
        }
    }


}