package com.globati.service;

import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Recommendation;
import com.globati.dbmodel.RecommendationImage;
import com.globati.enums.Category;
import com.globati.repository.EmployeeRepository;
import com.globati.repository.RecommendationRepository;
import com.globati.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 12/21/16.
 */
@Service
public class RecommendationService{

    private static final Logger log = LogManager.getLogger(RecommendationService.class);



    @Autowired
    RecommendationRepository recommendationRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    RecommendationImageService recommendationImageService;

    RecommendationService(){}


    public Recommendation createRecommendation(Long employeeId, String title, String description, double targetLat,
                                               double targetLong, String street, String city, String country,
                                               List<String> rawImages, String category) throws ServiceException {

        log.info("createRecommendation(): employeeId: "+employeeId+" recommendationTitle: "+title);
        Employee employee=null;
        List<RecommendationImage> recommendationImages = new ArrayList<>();
        try {
            Employee e2 = employeeRepository.getEmployeeByid(employeeId);
            Recommendation rec = new Recommendation(e2, title, description, targetLat, targetLong, street, city, country, Category.valueOf(category));
            rawImages.forEach((image) -> {
                RecommendationImage recommendationImage = new RecommendationImage(rec, image);
                recommendationImages.add(recommendationImage);
            });
            rec.setRecommendationimages(recommendationImages);
            return recommendationRepository.save(rec);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: createRecommendation(): employeeId: "+employeeId);
            e.printStackTrace();
            throw new ServiceException("Could not create recommendation at this time: ", e);
        }
    }

    public Recommendation getRecommendationById(Long id){
        return recommendationRepository.findOne(id);
    }

    public Recommendation inactivateRecommendation(Long id) throws ServiceException {
        try{
            Recommendation rec = recommendationRepository.findOne(id);
            rec.setActive(false);
            return recommendationRepository.save(rec);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: inactivateRecommendation(): id: "+id);
            e.printStackTrace();
            throw new ServiceException("Could not inactivate recommendation", e);
        }
    }

    public Recommendation updateRecommendation(Recommendation recommendation) throws ServiceException {
        try{
            return recommendationRepository.save(recommendation);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: updateRecommendation(): recommendationId: ");
            e.printStackTrace();
            throw new ServiceException("Could not update recommendation with id: ", e);
        }
    }


    public Recommendation updateRecommendation(Long id, String title, String description, List<String> images) throws ServiceException {
        try {

            Recommendation returnRecommendation = getRecommendationById(id);

            for(RecommendationImage image : returnRecommendation.getRecommendationimages()){
                recommendationImageService.deleteRecommendationImage(image.getId());
            }

            List<RecommendationImage> translatedImages = new ArrayList<>();
            images.forEach((image) -> translatedImages.add(recommendationImageService.createRecommendationImage(returnRecommendation, image)));

            returnRecommendation.setRecommendationimages(translatedImages);
            returnRecommendation.setDescription(description);
            returnRecommendation.setTitle(title);


            Recommendation updatedRecommendation = recommendationRepository.save(returnRecommendation);

            return updatedRecommendation;
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: updateRecommendation(): recommendationId: ");
            e.printStackTrace();
            throw new ServiceException("Could not update recommendation with id: ", e);
        }
    }

    public void deleteRecommendation(Recommendation recommendation) throws ServiceException {
        try{
            recommendationRepository.delete(recommendation);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: deleteRecommendation(): recommendationId: "+recommendation.getId());
            e.printStackTrace();
            throw new ServiceException("Could not delete reocommendation with id: "+recommendation.getId(), e);
        }
    }

    public List<Recommendation> getRecommendationByEmployeeId(Long id) throws ServiceException {
        try{
            return recommendationRepository.getAllRecommendationsByEmployeeIdAndActive(id, true);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getRecommendationByEmployeeId(): employeeId: "+id);
            e.printStackTrace();
            throw new ServiceException( "Could not get recommendations by employeeId: "+id, e );
        }
    }

}
