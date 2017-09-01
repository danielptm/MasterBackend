package com.globati.service;


import com.globati.config.Paths;
import com.globati.dbmodel.*;
import com.globati.repository.EmployeeRepository;
import com.globati.service.exceptions.ServiceException;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.service_beans.guest.EmployeeAndItems;
import com.globati.utildb.FacebookUserId;
import com.globati.HelpObjects.ApiKey;
import com.globati.utildb.ImageHandler;
import com.globati.utildb.PBKDF2;
import com.globati.utildb.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel on 12/21/16.
 */

@Service
public class EmployeeService {

    private static final Logger log = LogManager.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    DealService dealService;

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    EventService eventService;

    @Autowired
    EmployeeInfoService employeeInfoService;

    EmployeeService(){}

    public Employee getEmployeeById(Long id) throws ServiceException {
        try {
            Employee employee = this.employeeRepository.getEmployeeByid(id);
            employee.setDeals(null);
            employee.setEvents(null);
            employee.setRecommendations(null);
            return employee;
        }catch(Exception e){
            throw new ServiceException("Was not able to retrieve employee by id: "+id, e);
        }
    }

    /**
     * This creates profile credentials from facebook info, although it takes a paramater facebookId, this value is actually being stored in
     * EmployeeInfo
     * @param facebookId
     * @param name
     * @param email
     * @param image
     * @return
     * @throws ServiceException
     */
    public Employee createProfileFromFacebookInfo(String facebookId, String name, String email, String image ) throws ServiceException {
        try{
            String username=FacebookUserId.generateFacebookUserId(name);
            Employee employee = new Employee(name, email, username, image);
            Employee employeeToReturn = employeeRepository.save(employee);
            createEmployeeInfoFromFacebook(employeeToReturn.getId(), facebookId);
            return employeeToReturn;

        }catch(Exception e){
            throw new ServiceException("Could not create a profile from facebook info for person with facebook id: "+facebookId, e);
        }
    }

    public EmployeeInfo createEmployeeInfoFromFacebook(Long id, String facebookid) throws ServiceException {
        try{
            return employeeInfoService.createEmployeeInfoForFacebookLogin(id, facebookid);
        }catch(Exception e){
            throw new ServiceException("Could not create Employee info for facebook login with employee id: "+id, e);
        }
    }

    public Employee getEmployeeByFacebookId(String id) throws ServiceException {
        try{
            EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfoByFacebookId(id);
            if(employeeInfo==null){
                return null;
            }
            else {
                return employeeRepository.findOne(employeeInfo.getEmployeeId());
            }
        }catch(Exception e){
            throw new ServiceException("Could not get employee by facebookid with id: "+id, e);
        }
    }

    /**
     *
     * Tries to get an employee based on the facebookId. If It cant find that employee, then null is returned.
     * If this is the case, then a new facebook profile is created. An API key is then generated, allocated to the
     * users info, and then the data is returned. If the user already has a facebook profile registered with globati
     * The same procedure is done minus the creation of the profile.
     *
     * @param facebookId
     * @param name
     * @param email
     * @param image
     * @return
     * @throws ServiceException
     */
    public EmployeeAndItems createAccountOrLoginWithFacebook(String facebookId, String name, String email, String image) throws ServiceException {
        try{
            EmployeeInfo employeeInfo=null;
            Employee employee = getEmployeeByFacebookId(facebookId);
            if(employee==null){
                Employee createdEmployee = createProfileFromFacebookInfo(facebookId, name, email, image);
                employeeInfo = employeeInfoService.getEmployeeInfoByFacebookId(facebookId);
                ApiKey apiKey = new ApiKey();
                employeeInfo.setAuthToken(apiKey.getApiKey());
                employeeInfo.setTokenExpiration(apiKey.getTime());
                employeeInfoService.updateEmployeeInfo(employeeInfo);
                EmployeeAndItems employeeAndItems = getItemsForEmployee(createdEmployee.getGlobatiUsername());
                employeeAndItems.setApiKey(apiKey);
                return employeeAndItems;
            }
            else{
                employeeInfo = employeeInfoService.getEmployeeInfoByFacebookId(facebookId);
                ApiKey apiKey = new ApiKey();
                employeeInfo.setAuthToken(apiKey.getApiKey());
                employeeInfo.setTokenExpiration(apiKey.getTime());
                employeeInfoService.updateEmployeeInfo(employeeInfo);
                EmployeeAndItems items = getItemsForEmployee(employee.getGlobatiUsername());
                items.setApiKey(apiKey);
                return items;
            }
        }catch(Exception e){
            throw new ServiceException("Could not create an account and/or login with facebookid: "+facebookId,e);
        }

    }


    public Employee getEmployeeByUserName(String username) throws ServiceException {
        try{
            return employeeRepository.getEmployeeByGlobatiUsername(username);
        }catch(Exception e){
            throw new ServiceException("Could not retrieve employee by username", e);
        }
    }


    /**
     *
     * Gets an employee and all of the items, recommendations, deals, events associated with that employee;
     *
     * The arraylist returned is the size of 2. The first element is the above mentioend employee, the second item in the array is the nearby deals.
     *
     *
     *
     *
     * @param username
     * @return
     * @throws ServiceException
     */
    public EmployeeAndItems getItemsForEmployee(String username) throws ServiceException {
        ArrayList<Object> items = new ArrayList<>();
        try{
            Employee employee = employeeRepository.getEmployeeByGlobatiUsername(username);

            if(employee==null){
                throw new Exception("this username could not be found");
            }

            List<Deal> deals = dealService.getActiveDealsByEmployee(employee.getId());
            for(Deal deal: deals){
                deal.setTransactionId(null);
            }

            List<Deal> nearbydeals = this.dealService.getNearbyActiveDeals(employee.getCountry(), employee.getId());
            for(Deal deal: nearbydeals){
                deal.setTransactionId(null);
            }

            List<Recommendation> recommendations = recommendationService.getRecommendationByEmployeeId(employee.getId());
            List<Event> events = eventService.getEventsByEmployeeId(employee.getId());
            employee.setRecommendations(recommendations);
            employee.setEvents(events);
            employee.setDeals(deals);

            EmployeeAndItems employeeAndItems = new EmployeeAndItems(employee, deals);

//            items.add(employee);
//            items.add(nearbydeals);

            return employeeAndItems;
            } catch(Exception e){
                throw new ServiceException("Could not retrieve employee by user name and pasword", e);
        }
    }

    public Employee incrementCounter(Employee employee){
        if(employee.getVisitCounter()==null){
            employee.setVisitCounter(1);
        }
        else{
            Integer newValue = employee.getVisitCounter();
            newValue++;
             employee.setVisitCounter(newValue);
        }
        return employee;
    }

    /**
     * Creates a new employee
     * @param name
     * @param email
     * @param username
     * @param password
     * @param latvalue
     * @param longvalue
     * @param street
     * @param city
     * @param country
     * @return
     * @throws ServiceException org.hibernate.exception.ConstraintViolationException
     */

    public Employee createEmployee(String name, String email, String username, String password, double latvalue, double longvalue, String image, String street, String city, String country) throws ServiceException, UserDoesNotExistException {
        Employee employee=null;
        try {
            employee = new Employee(name, email, username, latvalue, longvalue, image, street, city, country);
            Employee savedEmployee = employeeRepository.save(employee);
            employeeInfoService.createEmployeeInfo(savedEmployee.getId(), password);
            return savedEmployee;
        }
        catch(Exception e){
            if(e.getClass().toString().equals("class org.springframework.dao.DataIntegrityViolationException")){
                throw new UserDoesNotExistException("This username already exists");
            }
            else{
                throw new ServiceException("Could not create employee: "+employee.toString(), e);
            }
        }
    }

    public Employee updateEmployee(Employee employee) throws ServiceException, UserDoesNotExistException {
        try {
            return this.employeeRepository.save(employee);
        }catch(Exception e){
            if(e.getClass().toString().equals("class org.springframework.dao.DataIntegrityViolationException")){
                throw new UserDoesNotExistException("This username already exists");
            }
            else{
                throw new ServiceException("Could not update employee: "+employee.toString(), e);
            }
        }
    }

    public boolean replaceImage(Long id, InputStream is) throws ServiceException{
        Employee employee = null;
        try {
            employee = getEmployeeById(id);
            ImageHandler.deleteFileFromS3(employee.getImage());
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not retrieve employee with id: "+employee.getId(), e);
        }finally {
            //Creates
            String path = Paths.getS3Root()+Paths.getActiveImageLink();
            try {
                path += ImageHandler.createNewImage(is);
                employee.setImage(path);
                updateEmployee(employee);
            } catch (Exception e) {
                log.error(e.toString());
                throw new ServiceException("Could not create new image for employee: " + employee.toString(), e);
            }
            return true;
        }
    }

    public Iterable<Employee> getAllEmployees() throws ServiceException {
        try{
            return employeeRepository.findAll();
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not get All employees", e);
        }
    }

    public List<Employee> getEmployeesByCountry(String country) throws ServiceException {
        try{
            List<Employee> employees= this.employeeRepository.getEmployeeByCountry(country);
            for(Employee employee: employees){
                employee.setDeals(null);
                employee.setRecommendations(null);
                employee.setEvents(null);
            }
            return employees;
        }catch(Exception e){
            throw new ServiceException("Could not get employees by place: "+country, e);
        }
    }

    public List<Employee> getEmployeesByCity(String city) throws ServiceException {
        try{
            List<Employee> employees = this.employeeRepository.getEmployeeByCity(city);
            for(Employee employee: employees){
                employee.setDeals(null);
                employee.setRecommendations(null);
                employee.setEvents(null);
            }
            return employees;
        }catch(Exception e){
            throw new ServiceException("Could not get employees by city: "+city, e);
        }
    }

    /**
     * Returns true if the password is successfully changed.
     * @param employeeId
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws ServiceException
     */
    public boolean changePassword(Long employeeId, String oldPassword, String newPassword) throws ServiceException {
        try{
            EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfoByEmployeeId(employeeId);
            if(PBKDF2.checkPassword(employeeInfo, oldPassword)){
                EmployeeInfo info = PBKDF2.hashEmployeePassword(employeeInfo, newPassword);
                employeeInfoService.updateEmployeeInfo(info);
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){
            throw new ServiceException("Could not change password at this time "+employeeId,e);
        }
    }

    public boolean changePasswordWithToken(String token, String password) throws ServiceException {
        try{
            EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfoByToken(token);
            if(Long.parseLong(employeeInfo.getTokenExpiration())>System.currentTimeMillis()) {
                EmployeeInfo info = PBKDF2.hashEmployeePassword(employeeInfo, password);
                employeeInfoService.updateEmployeeInfo(info);
                return true;
            }
            else{
               throw new Exception();
            }
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not change password at this time ",e);
        }
    }

    /**
     * This does not use the email supplied in the form, but uses the email
     * to find the employee by email. This is problematic because email is not unique in the database,
     * so if a sombody creates multiple accounts with the same email and tries to reset their password
     * this function will fail, and the user will not receive an email.
     *
     * This seems like kind of an unlikely problem, but if it appears
     *
     * @param email
     * @return
     * @throws ServiceException
     */
    public boolean sendEmailToChangePassword(String email) throws ServiceException {
        try {
            log.debug("service sendEmailToChangePassword() "+email);
            ApiKey api = new ApiKey();
            Employee employee = employeeRepository.getEmployeeByEmail(email);
            EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfoByEmployeeId(employee.getId());
            employeeInfo.setAuthToken(api.getApiKey());
            employeeInfo.setTokenExpiration(api.getTime());
            employeeInfoService.updateEmployeeInfo(employeeInfo);
            return SendMail.sendForgottenPasswordEmail(employee.getEmail(), employee.getGlobatiUsername(),employeeInfo.getAuthToken());
        }catch(Exception e){
            throw new ServiceException("Could not send employee info", e);
        }
    }

    /**
     * This function was moved from the Web third_party_api level to the service class.
     *
     * There are no tests for this function, and was copied exactly from the Web third_party_api level, except for it returns now List<ApiKey>
     * In the web third_party_api level it returned Response.ok(item).build();
     *
     * This function needs a test!
     *
     * @param userName
     * @param password
     * @return
     * @throws ServiceException
     */
    public List<ApiKey> authenticateReceptionist(String userName, String password) throws ServiceException {
        try {
            List<ApiKey> item = new ArrayList<>();
            String username = userName;
            String passwordAttempt = password;

            Employee employee = getEmployeeByUserName(username);

            EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfoByEmployeeId(employee.getId());

            if(PBKDF2.checkPassword(employeeInfo, passwordAttempt)){
                employeeInfo.setLastLogin( new Date() );
                ApiKey apiKey = new ApiKey();
                employeeInfo.setAuthToken(apiKey.getApiKey());
                employeeInfo.setTokenExpiration(apiKey.getTime());
                item.add(apiKey);
                employeeInfoService.updateEmployeeInfo(employeeInfo);
                return item;
            }
            else{
                throw new Exception("Could not verify user: "+username);
            }
        }catch(Exception e){
            throw new ServiceException("Password or username did not match", e);
        }
    }

    /**
     * Called when a guest visits a globati profile page.
     * @param id
     * @return
     * @throws Exception
     */

    public EmployeeAndItems getItemsForEmployeeAndIncrement(String id) throws Exception {
        try {
            Employee employee = getEmployeeByUserName(id);
            incrementCounter(employee);
            updateEmployee(employee);
            return getItemsForEmployee(id);
        } catch (ServiceException e) {
            throw new Exception("Could not get items for employee by user name, and or could not increment visit counter with username: "+id);
        }
    }
}
