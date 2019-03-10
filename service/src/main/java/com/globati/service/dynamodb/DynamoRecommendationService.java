package com.globati.service.dynamodb;

import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import com.oracle.jrockit.jfr.DynamicEventToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DynamoRecommendationService {

    @Autowired
    DynamoPropertyRepository dynamoPropertyRepository;

    public DynamoRecommendation createRecommendation(com.globati.request.Recommendation recommendation) {


        return null;
    }

    /**
     * Deletes a recommenation from a property
     * @param email of the property
     * @param recommendationId of the recommendation to delete.
     * @returnå
     */
    public DynamoProperty deleteRecommendation(String email, String recommendationId) {
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(email);
        DynamoRecommendation recommendationToRemove = dynamoProperty.getDynamoRecommendations().stream()
                .filter((dynamoRecommendation -> dynamoRecommendation.getId().equals(recommendationId))).findFirst().get();

        dynamoProperty.getDynamoRecommendations().remove(recommendationToRemove);

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoProperty;
    }

    public DynamoProperty updateRecommendation(com.globati.request.Recommendation recommendation){

        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(recommendation.getPropertyEmail());

        DynamoRecommendation dynamoRecommendation = dynamoProperty.getDynamoRecommendations()
                .stream().filter((dr -> dr.getId() == recommendation.getId())).findFirst().get();

        int indexToUpdate = dynamoProperty.getDynamoRecommendations().indexOf(dynamoRecommendation);

        if( dynamoRecommendation != null) {
            Optional.ofNullable(recommendation.getTitle())
                    .ifPresent((title) -> dynamoRecommendation.setTitle(title));

            Optional.ofNullable(recommendation.getDescription())
                    .ifPresent((des) -> dynamoRecommendation.setDescription(des));

            Optional.ofNullable(recommendation.getCity())
                    .ifPresent((city) -> dynamoRecommendation.setCity(city));

            Optional.ofNullable(recommendation.getCountry())
                    .ifPresent((country) -> dynamoRecommendation.setCountry(country));

            Optional.ofNullable(recommendation.getTargetLat())
                    .ifPresent((lat) -> dynamoRecommendation.setLatitude(lat));

            Optional.ofNullable(recommendation.getTargetLong())
                    .ifPresent((longitude) -> dynamoRecommendation.setLongitude(longitude));
        }

        dynamoProperty.getDynamoRecommendations().set(indexToUpdate, dynamoRecommendation);

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoProperty;
    }

    public DynamoRecommendation getRecommendationById(String id) {
        return null;
    }

    public List<DynamoRecommendation> getRecommendationsByEmployeeName(String name) {
        return null;
    }
}
