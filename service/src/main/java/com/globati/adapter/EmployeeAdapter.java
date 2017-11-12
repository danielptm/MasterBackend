package com.globati.adapter;

import com.globati.dbmodel.*;
import com.globati.deserialization_beans.response.employee.ResponseEmployee;
import com.globati.deserialization_beans.response.employee.ResponseEvent;
import com.globati.deserialization_beans.response.employee.ResponseRecommendation;
import com.globati.service.EmployeeService;
import com.globati.service.exceptions.AdapaterException;
import com.globati.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class does not have tests written for it.
 */
@Service
public class EmployeeAdapter {

    private static final Logger log = LogManager.getLogger(EmployeeAdapter.class);


    @Autowired
    EmployeeService employeeService;

    /**
     * No tests are written for this class, because there is no logic, just object mapping and getts and setters.
     *
     * @param city
     * @return
     * @throws ServiceException
     */
    public List<ResponseEmployee> translateEmployeeAndItems(String city) throws AdapaterException {
        List<ResponseEmployee> responseEmployees;
        try {
            responseEmployees = new ArrayList<>();

            List<Employee> employeeList = employeeService.getEmployeesByCityAndTheirRecommendations(city);

            for (Employee employee : employeeList) {
                ResponseEmployee responseEmployee = new ResponseEmployee(
                        employee.getEmail(), employee.getPaypalEmail(), employee.getAbout(),
                        employee.getWelcomeMail(), employee.getRecruitmentMail(), employee.getInstagramUser(),
                        employee.getInstagramUserId(), employee.getInstagramUserToken(), employee.getPropLat(),
                        employee.getPropLong(), employee.getStreet(), employee.getCity(), employee.getCountry(),
                        employee.getDisplay(), employee.getGlobatiUsername(), translateResponseRecommendations(employee),
                        translateResponseEvents(employee), employee.getFirstName()
                );

                responseEmployees.add(responseEmployee);
            }
        }catch(Exception e){
            log.warn("An adapater exception occurred");
            e.printStackTrace();
            throw new AdapaterException("An adpater exception occurred");
        }
        return responseEmployees;

    }

    public List<ResponseRecommendation> translateResponseRecommendations(Employee employee) {
        List<ResponseRecommendation> responseRecomendations = new ArrayList<>();

        List<Recommendation> items = null;

        if(employee.getRecommendations() !=null || ! employee.getRecommendations().isEmpty()){
            items = employee.getRecommendations();
        }
        else{
            return null;
        }

        for (Recommendation recommendation : items) {
            ResponseRecommendation responseRecommendation = new ResponseRecommendation(
                    recommendation.getTargetLat(), recommendation.getTargetLong(),
                    recommendation.getStreet(), recommendation.getCity(), recommendation.getCountry(),
                    recommendation.getDescription(), recommendation.getLocation(), recommendation.getTitle(),
                    translateRecommendationImages(recommendation.getRecommendationimages())
            );
            responseRecomendations.add(responseRecommendation);
        }
        System.out.println("***: "+responseRecomendations.size());
        return responseRecomendations;
    }

    public List<ResponseEvent> translateResponseEvents(Employee employee) throws Exception {
        List<ResponseEvent> responses = new ArrayList<>();

        List<Event> items = null;
        if(employee.getRecommendations() != null || ! employee.getRecommendations().isEmpty()){
            items = employee.getEvents();
        }
        else{
            return null;
        }


        for (Event item : items) {
            ResponseEvent event = new ResponseEvent(
                    item.getTargetLat(), item.getTargetLong(), item.getStreet(),
                    item.getCity(), item.getCountry(), item.getDescription(),
                    item.getTitle(), item.getDate(), translateEventImages(item.getEventimages())
            );

            responses.add(event);
        }

        return responses;
    }

    public List<String> translateRecommendationImages(List<RecommendationImage> items) {
        List<String> recommendationImages = new ArrayList<>();
        for (RecommendationImage item : items) {
            recommendationImages.add(item.getPath());
        }
        return recommendationImages;
    }

    public List<String> translateEventImages(List<EventImage> items) {
        List<String> eventImages = new ArrayList<>();
        for (EventImage item : items) {
            eventImages.add(item.getPath());
        }
        return eventImages;
    }

}
