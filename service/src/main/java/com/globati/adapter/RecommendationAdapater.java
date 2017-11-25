package com.globati.adapter;

import com.globati.dbmodel.Recommendation;
import com.globati.dbmodel.RecommendationImage;
import com.globati.deserialization_beans.response.employee.ResponseImage;
import com.globati.deserialization_beans.response.employee.ResponseRecommendation;
import com.globati.service.exceptions.AdapaterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationAdapater {

    private static final Logger log = LogManager.getLogger(RecommendationAdapater.class);

    public ResponseRecommendation translateRecommendationForResponse(Recommendation recommendation) throws AdapaterException {
        try{
            ResponseRecommendation responseRecommendation = new ResponseRecommendation(
                    recommendation.getId(), recommendation.getCity(), recommendation.getCountry(),
                    recommendation.getDescription(), recommendation.getLocation(), recommendation.getStreet(),
                    recommendation.getTargetLat(), recommendation.getTargetLong(), recommendation.getTitle(),
                    recommendation.isActive(), translateImagesToResponseImages(recommendation)
            );
            return responseRecommendation;
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: deleteRecommendation(): recommendationId: "+recommendation.getId());
            e.printStackTrace();
            throw new AdapaterException("Could not translate recommendation",e);
        }
    }


    private List<ResponseImage> translateImagesToResponseImages(Recommendation recommendation) {
        List<ResponseImage> responseImages = new ArrayList<>();

        for (RecommendationImage rec : recommendation.getRecommendationimages()) {
            ResponseImage responseImage = new ResponseImage(rec.getId(), rec.getPath());
            responseImages.add(responseImage);
        }

        return responseImages;
    }
}
