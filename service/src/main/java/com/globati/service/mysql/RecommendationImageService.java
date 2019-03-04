package com.globati.service.mysql;

import com.globati.mysql.dbmodel.Recommendation;
import com.globati.mysql.dbmodel.RecommendationImage;
import com.globati.repository.mysql.RecommendationImageRepository;
import com.globati.service.mysql.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationImageService {

    private static final Logger log = LogManager.getLogger(RecommendationImageService.class);

    @Autowired
    RecommendationImageRepository recommendationImageRepository;

    public RecommendationImage createRecommendationImage(Recommendation recommendation, String image){
        RecommendationImage recommendationImage = null;
        try {
            recommendationImage = new RecommendationImage(recommendation, image);
            recommendationImage = recommendationImageRepository.save(recommendationImage);
        } catch (Exception e) {
            log.warn("** GLOBATI SERVICE EXCEPTION ** Cannot create image recommendation.");
        }
        return recommendationImage;
    }

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

}
