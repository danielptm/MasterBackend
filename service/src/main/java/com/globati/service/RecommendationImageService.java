package com.globati.service;

import com.globati.dbmodel.RecommendationImage;
import com.globati.repository.RecommendationImageRepository;
import com.globati.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationImageService {

    private static final Logger log = LogManager.getLogger(RecommendationImageService.class);

    @Autowired
    RecommendationImageRepository recommendationImageRepository;

    public void deleteRecommendationImage(Long id) throws ServiceException {
        try{
            RecommendationImage recommendationImage = recommendationImageRepository.findOne(id);
            recommendationImageRepository.delete(recommendationImage);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** deleteRecommendationImage(): recommendationId: "+id);
            e.printStackTrace();
            throw new ServiceException("Could not delete recommendationImage");
        }
    }

    public void deleteRecommendationImages(Long id) throws ServiceException {
        try{
            recommendationImageRepository.deleteAllRecommendationImagesWithRecommendationId(id);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** deleteRecommendationImages() recommendationin: "+id);
            e.printStackTrace();
            throw new ServiceException("Could not delete recommendation images");
        }
    }
}
