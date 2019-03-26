package com.globati.service.dynamodb;

import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import com.globati.request.ImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamoImageService {

    @Autowired
    DynamoPropertyRepository dynamoPropertyRepository;

    //Property
    public DynamoImage createDynamoImageForProperty(String propertyEmail, ImageRequest imageRequest) {
        return null;
    }

    public DynamoImage getDynamoImageForProperty(String propertyEmail, String imageId) {
        return null;
    }

    public DynamoImage updateDynamoImageForProperty (String propertyEmail, String imageId, ImageRequest imageRequest) {
        return null;
    }
    public DynamoProperty deleteDynamoImageForProperty(String propertyEmail, String imageId) {
        return null;
    }

    //Recommendation
    public DynamoImage createDynamoImageForRecommendation(String propertyEmail, ImageRequest imageRequest) {
        return null;
    }

    public DynamoImage getDynamoImageForRecommendation(String propertyEmail, String imageId) {
        return null;
    }

    public DynamoImage updateDynamoImageForRecommendation (String propertyEmail, String imageId, ImageRequest imageRequest) {
        return null;
    }
    public DynamoProperty deleteDynamoImageForRecommendation(String propertyEmail, String image) {
        return null;
    }

    //Tour
    public DynamoImage createDynamoImageForTour(String propertyEmail, ImageRequest imageRequest) {
        return null;
    }

    public DynamoImage getDynamoImageForTour() {
        return null;
    }

    public DynamoImage updateDynamoImageForTour () {
        return null;
    }
    public DynamoProperty deleteDynamoImageForTour() {
        return null;
    }

    //TourStop
    public DynamoImage createDynamoImageForTourStop(ImageRequest imageRequest) {
        return null;
    }

    public DynamoImage getDynamoImageForTourStop() {
        return null;
    }

    public DynamoImage updateDynamoImageForTourStop () {
        return null;
    }
    public DynamoProperty deleteDynamoImageForTourStop() {
        return null;
    }

}
