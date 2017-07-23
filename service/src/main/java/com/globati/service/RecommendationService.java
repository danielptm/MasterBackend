package com.globati.service;


import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Recommendation;
import com.globati.repository.EmployeeRepository;
import com.globati.repository.RecommendationRepository;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.CheckProximity;
import com.globati.utildb.ImageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * Created by daniel on 12/21/16.
 */
@Service
public class RecommendationService{

    private static final Logger log = LogManager.getLogger(DealService.class);


    @Autowired
    RecommendationRepository recommendationRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    RecommendationService(){}


    public Recommendation createRecommendation(Long employeeId, String title, String description, double targetLat, double targetLong, String street, String city, String country, String image1, String image2, String image3) throws ServiceException {
        Employee employee=null;
        Recommendation rec=null;
        String imagepath=null;
        try {
//            imagepath = ImageHandler.createNewImage(is);
            employee = employeeRepository.getEmployeeBy_id(employeeId);
            rec = new Recommendation(employee, title, description, targetLat, targetLong, street, city, country, image1, image2, image3);
            Recommendation rec2 = CheckProximity.getRecommendationProximity(rec, employee);
            return recommendationRepository.save(rec2);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not create recommendation at this time: "+rec.toString(), e);
        }
    }

    public Recommendation getRecommendationById(Long id){
        return recommendationRepository.findOne(id);
    }



    public Recommendation inactivateRecommendation(Long id) throws ServiceException {
        try{
            Recommendation rec = recommendationRepository.findOne(id);
            rec.set_active(false);
            return updateRecommendation(rec);
        }catch(Exception e){
            log.error("Could not inactivate recommendation with id: "+id);
            throw new ServiceException("Could not inactivate recommendation", e);
        }
    }

    public Recommendation updateRecommendation(Recommendation recommendation) throws ServiceException {
        try {
            return recommendationRepository.save(recommendation);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not update recommendation: "+recommendation.toString(), e);
        }
    }

    public void deleteRecommendation(Recommendation recommendation) throws ServiceException {
        try{
            recommendationRepository.delete(recommendation);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not delete reocommendation "+recommendation.toString(), e);
        }
    }


    public List<Recommendation> getRecommendationByEmployeeId(Long id) throws ServiceException {
        try{
            return recommendationRepository.getAllRecommendationsBy_employee_idAndActive(id, true);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException( "Could not get recommendations by id: "+id, e );
        }
    }
}
