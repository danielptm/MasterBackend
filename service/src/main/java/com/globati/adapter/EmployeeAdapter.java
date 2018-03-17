package com.globati.adapter;

import com.globati.dbmodel.*;
import com.globati.deserialization_beans.response.employee.*;
import com.globati.service.EmployeeService;
import com.globati.service.exceptions.AdapaterException;
import com.globati.service.exceptions.ServiceException;
import com.globati.service_beans.guest.EmployeeAndItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeAdapter {

    private static final Logger log = LogManager.getLogger(EmployeeAdapter.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ImageAdapater imageAdapater;

    @Autowired
    FlightAdapater flightAdapater;

    @Autowired
    HotelAdapater hotelAdapater;

    @Autowired
    BlogAdapater blogAdapater;

    /**
     * No tests are written for this class, because there is no logic, just object mapping and getts and setters.
     *
     * @return
     * @throws ServiceException
     */
    //This is bad, I have to call getItemsForEmployee() becase createAccountOrLoginWithFacebookId, does not return an apikey
    public ResponseEmployee translateFacebookLogin(String id, String name, String email, String image) throws AdapaterException {
        try {
            EmployeeAndItems profileItems = employeeService.createAccountOrLoginWithFacebook(id, name, email, image);
            EmployeeAndItems employeeAndItems = employeeService.getItemsForEmployee(profileItems.getEmployee().getGlobatiUsername());
            employeeAndItems.setApiKey(profileItems.getApiKey());
            return translateOneEmployee(employeeAndItems);
        } catch (Exception e) {
            log.warn("An adapater exception occurred");
            e.printStackTrace();
            throw new AdapaterException("An adpater exception occurred");
        }
    }

    public ResponseEmployee getAndTranslateEmployeeByUserName(String username) throws AdapaterException {
        try {
            return translateOneEmployee(employeeService.getItemsForEmployee(username));
        } catch (Exception e) {
            log.warn("** GLOBATI ADAPATER EXCEPTION **");
            e.printStackTrace();
            throw new AdapaterException("Could not get employee by username: getEmployeeByUsername()");
        }
    }

    public ResponseEmployee getTranslateEmployeeAndIncrement(String username) throws AdapaterException {
        try{
            return translateOneEmployee(employeeService.getItemsForEmployeeAndIncrement(username));
        }catch(Exception e){
            log.warn("** GLOBATI ADAPATER EXCEPTION **");
            e.printStackTrace();
            throw new AdapaterException("Could not get employee by username: getEmployeeByUsername()");
        }
    }

    public ResponseEmployee translateOneEmployee(EmployeeAndItems employeeAndItems) throws AdapaterException {
        try {

            Employee employee = employeeAndItems.getEmployee();

            ResponseEmployee responseEmployee = new ResponseEmployee(
                    employee.getId(), employee.getFirstName(), employee.getImage(),
                    employee.getImage2(), employee.getImage3(), employee.getEmail(),
                    employee.getPaypalEmail(), employee.getAbout(), employee.getWelcomeMail(),
                    employee.getRecruitmentMail(), employee.getFirstName(), employee.getInstagramUserId(),
                    employee.getInstagramUserToken(), employee.getPropLat(), employee.getPropLong(),
                    employee.getStreet(), employee.getCity(), employee.getCountry(), employee.getDisplay(),
                    employee.getGlobatiUsername(), employee.isFacebookProfile(), translateResponseRecommendations(employee),
                    translateResponseEvents(employee), employeeAndItems.getApiKey(), translateResponseFlights(employee),
                    translateResponseHotels(employee), employee.getVisitCounter(), translateResponseBlogs(employee), getAndTranslateResponsetips(employee)
            );
            return responseEmployee;
        } catch (Exception e) {
            log.warn("An adapater exception occurred");
            e.printStackTrace();
            throw new AdapaterException("An adpater exception occurred");
        }

    }


    public List<ResponseEmployee> getAndTranslateEmployeesByCitySearch(String city) throws AdapaterException {
        List<ResponseEmployee> responseEmployees;
        try {
            responseEmployees = new ArrayList<>();

            List<Employee> employeeList = employeeService.getEmployeesByCityAndTheirRecommendations(city);

            for (Employee employee : employeeList) {
                ResponseEmployee responseEmployee = new ResponseEmployee(
                        employee.getId(), employee.getFirstName(), employee.getImage(),
                        employee.getImage2(), employee.getImage3(), employee.getEmail(),
                        employee.getPaypalEmail(), employee.getAbout(), employee.getWelcomeMail(),
                        employee.getRecruitmentMail(), employee.getFirstName(), employee.getInstagramUserId(),
                        employee.getInstagramUserToken(), employee.getPropLat(), employee.getPropLong(),
                        employee.getStreet(), employee.getCity(), employee.getCountry(), employee.getDisplay(),
                        employee.getGlobatiUsername(), employee.isFacebookProfile(), translateResponseRecommendations(employee),
                        translateResponseEvents(employee),null, null, employee.getVisitCounter(), null, null
                );
                responseEmployees.add(responseEmployee);
                System.out.println(responseEmployees.size());
            }
        } catch (Exception e) {
            log.warn("An adapater exception occurred");
            e.printStackTrace();
            throw new AdapaterException("An adpater exception occurred");
        }
        return responseEmployees;

    }

    private List<ResponseTip> getAndTranslateResponsetips(Employee employee){
        List<ResponseTip> responseTips = new ArrayList<>();

        for(Tip tip: employee.getTips()){
            ResponseTip rt = new ResponseTip(tip.getDate(), tip.getId(), tip.getTipAmount(), tip.getTransactionId(), tip.getEmail());
            responseTips.add(rt);
        }

        return responseTips;
    }

    //This should be moved to RecommendationAdapter
    private List<ResponseRecommendation> translateResponseRecommendations(Employee employee) {

        List<ResponseRecommendation> responseRecomendations = new ArrayList<>();

        List<Recommendation> items = null;

        if (employee.getRecommendations() != null || !employee.getRecommendations().isEmpty()) {
            items = employee.getRecommendations();
        }

        for (Recommendation recommendation : items) {
            ResponseRecommendation responseRecommendation = new ResponseRecommendation(
                    recommendation.getId(), recommendation.getCity(), recommendation.getCountry(),
                    recommendation.getDescription(), recommendation.getLocation(),
                    recommendation.getStreet(), recommendation.getTargetLat(), recommendation.getTargetLong(),
                    recommendation.getTitle(), recommendation.isActive(), imageAdapater.translateRecommendationImages(recommendation.getRecommendationimages())
            );
            responseRecomendations.add(responseRecommendation);
        }

        return responseRecomendations;

    }

    //This should be moved to EventAdapater
    private List<ResponseEvent> translateResponseEvents(Employee employee) throws Exception {
        List<ResponseEvent> responses = new ArrayList<>();

        List<Event> items = null;
        if ( employee.getRecommendations() != null || ! employee.getRecommendations().isEmpty() ) {
            items = employee.getEvents();
        }
        for ( Event event : items ) {
            ResponseEvent responseEvent = new ResponseEvent(
                    event.getId(), event.getCity(), event.getCountry(),
                    event.getRepeat(), event.getDescription(), event.getLocation(),
                    event.getStreet(), event.getTargetLat(), event.getTargetLong(),
                    event.getTitle(), imageAdapater.translateEventImages(event.getEventimages()),
                    event.getDate()
            );
            responses.add(responseEvent);
        }
        return responses;
    }

    public List<ResponseFlight> translateResponseFlights(Employee employee){
        List<ResponseFlight> responseFlights = new ArrayList<>();

        for(FlightBooking flight: employee.getFlights()){
            ResponseFlight responseFlight = flightAdapater.getAndTranslateAflight(flight);
            responseFlights.add(responseFlight);
        }

        return responseFlights;

    }

    public List<ResponseHotel> translateResponseHotels(Employee employee){
        List<ResponseHotel> responseHotels = new ArrayList<>();

        for(HotelBooking hotelBooking: employee.getHotels()){
            ResponseHotel responseHotel = hotelAdapater.getAndTranslateAHotel(hotelBooking);
            responseHotels.add(responseHotel);
        }

        return responseHotels;
    }

    public List<ResponseBlog> translateResponseBlogs(Employee employee){
        List<ResponseBlog> responseBlogs = new ArrayList<>();

        for(Blog blog: employee.getBlogs()){
            ResponseBlog responseBlog = blogAdapater.getResponseBlog(blog.getId(), blog.getTitle(), blog.getCityAbout(), blog.getDescription(), blog.getBlogLink(), blog.getImageLink() );
            responseBlogs.add(responseBlog);
            System.out.println(responseBlog);
        }
        return responseBlogs;
    }

}
