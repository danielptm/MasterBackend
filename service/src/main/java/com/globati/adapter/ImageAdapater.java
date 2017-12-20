package com.globati.adapter;

import com.globati.dbmodel.Event;
import com.globati.dbmodel.EventImage;
import com.globati.dbmodel.Recommendation;
import com.globati.dbmodel.RecommendationImage;
import com.globati.deserialization_beans.response.employee.ResponseImage;
import com.globati.service.DealService;
import com.globati.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageAdapater {

    private static final Logger log = LogManager.getLogger(ImageAdapater.class);


    public List<RecommendationImage> translateImagesToRecommendationImages(Recommendation recommendation, List<String> images){
        List<RecommendationImage> recommendationImages = new ArrayList<>();
        for(String image: images){
            RecommendationImage recImage = new RecommendationImage(recommendation, image);
            recommendationImages.add(recImage);
        }
        return recommendationImages;
    }

    public List<EventImage> translateEventImages(Event event, List<String> images){
        List<EventImage> eventImages =  new ArrayList<>();

        for(String image: images){
            EventImage eventImage = new EventImage(event, image);
            eventImages.add(eventImage);
        }
        return eventImages;
    }


    public List<ResponseImage> translateRecommendationImages(List<RecommendationImage> items) {
        List<ResponseImage> recommendationImages = new ArrayList<>();
        for (RecommendationImage item : items) {
            ResponseImage responseImage = new ResponseImage(item.getId(), item.getPath());
            recommendationImages.add(responseImage);
        }
        return recommendationImages;
    }

    public List<ResponseImage> translateEventImages(List<EventImage> items) {
        List<ResponseImage> eventImages = new ArrayList<>();
        for (EventImage item : items) {
            ResponseImage image = new ResponseImage(item.getId(), item.getPath());
            eventImages.add(image);
        }
        return eventImages;
    }


    /**
     * No test written for this yet.
     * @param rawImages
     * @return
     * @throws ServiceException
     */
    public List<RecommendationImage> translateToRecommendationImages(Recommendation recommendation, List<String> rawImages) throws ServiceException {
        try{
            List<RecommendationImage> recommendationImages = new ArrayList<>();
            for(String image: rawImages){
                RecommendationImage newImage = new RecommendationImage(recommendation, image);
                recommendationImages.add(newImage);
            }
            return recommendationImages;
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: translateRecommendationImages()");
            throw new ServiceException("Could not translate raw images to RecommendationImage", e);
        }
    }



}
