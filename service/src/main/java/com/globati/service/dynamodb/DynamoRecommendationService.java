package com.globati.service.dynamodb;

import com.globati.api.ImageRequest;
import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.enums.Category;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DynamoRecommendationService {

    @Autowired
    DynamoPropertyRepository dynamoPropertyRepository;

    public DynamoRecommendation createRecommendation(com.globati.api.Recommendation recommendation) {
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(recommendation.getPropertyEmail());

        DynamoRecommendation dynamoRecommendation = new DynamoRecommendation();

        Optional.ofNullable(recommendation.getTitle())
                .ifPresent((title) -> dynamoRecommendation.setTitle(title));

        Optional.ofNullable(recommendation.getDescription())
                .ifPresent((des) -> dynamoRecommendation.setDescription(des));

        Optional.ofNullable(recommendation.getCity())
                .ifPresent((city) -> dynamoRecommendation.setCity(city));

        Optional.ofNullable(recommendation.getCountry())
                .ifPresent((country) -> dynamoRecommendation.setCountry(country));

        Optional.ofNullable(recommendation.getLatitude())
                .ifPresent((lat) -> dynamoRecommendation.setLatitude(lat));

        Optional.ofNullable(recommendation.getLongitude())
                .ifPresent((longitude) -> dynamoRecommendation.setLongitude(longitude));

        Optional.ofNullable(recommendation.getCategory())
                .ifPresent(cat -> dynamoRecommendation.setCategory(Category.valueOf(cat)));

        dynamoRecommendation.setImages(new ArrayList<>());

        if(recommendation.getImages() != null && recommendation.getImages().size() == 0) {
            recommendation.getImages().add(new ImageRequest("https://globatiimages.s3.eu-central-1.amazonaws.com/other/empty.png"));
        }

        Optional.ofNullable(recommendation.getImages())
                .ifPresent((images) -> {
                    images.forEach((image) -> {
                       DynamoImage dynamoImage = new DynamoImage(image.getPath());
                       dynamoRecommendation.getImages().add(dynamoImage);
                   });
                });

        dynamoProperty.getDynamoRecommendations().add(dynamoRecommendation);

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoRecommendation;
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
                .filter((dynamoRecommendation -> dynamoRecommendation.getId().equals(recommendationId)))
                .findFirst().get();

        dynamoProperty.getDynamoRecommendations().remove(recommendationToRemove);

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoProperty;
    }

    public DynamoRecommendation updateRecommendation(com.globati.api.Recommendation recommendation){

        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(recommendation.getPropertyEmail());

        DynamoRecommendation dynamoRecommendation = dynamoProperty.getDynamoRecommendations()
                .stream().filter((dr -> dr.getId().equals(recommendation.getId()))).findFirst().get();

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

            Optional.ofNullable(recommendation.getLatitude())
                    .ifPresent((lat) -> dynamoRecommendation.setLatitude(lat));

            Optional.ofNullable(recommendation.getLongitude())
                    .ifPresent((longitude) -> dynamoRecommendation.setLongitude(longitude));

            Optional.ofNullable(recommendation.getCategory())
                    .ifPresent((cat) -> dynamoRecommendation.setCategory(Category.valueOf(cat)));

            if(recommendation.getImages() != null && recommendation.getImages().size() == 0) {
                recommendation.getImages().add(new ImageRequest("https://globatiimages.s3.eu-central-1.amazonaws.com/other/empty.png"));
            }

            Optional.ofNullable(recommendation.getImages())
                    .ifPresent((images) -> {
                        dynamoRecommendation.setImages(new ArrayList<>());
                        images.forEach(image -> {
                            dynamoRecommendation.getImages().add(new DynamoImage(image.getPath()));
                        });
                    });
        }

        dynamoProperty.getDynamoRecommendations().set(indexToUpdate, dynamoRecommendation);

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoRecommendation;
    }

    //TODO: Add the email of the property as the first argument of the method, because otherwise it wont be able to get the right property
    public DynamoRecommendation getRecommendationById(String id) {
        DynamoRecommendation dynamoRecommendation = null;
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(id);
        for (DynamoRecommendation recommendation: dynamoProperty.getDynamoRecommendations()) {
            if(recommendation.getId().equals(id)) {
                dynamoRecommendation = recommendation;
            }
        }
        return dynamoRecommendation;
    }

    public List<DynamoRecommendation> getRecommendationsByEmployeeEmail(String email) {
        return dynamoPropertyRepository.findOne(email).getDynamoRecommendations();
    }


}
