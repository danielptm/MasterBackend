package com.globati.service.dynamodb;

import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import com.globati.request.tour.TourRequest;
import com.globati.request.tour.TourStopImageRequest;
import com.globati.utildb.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DynamoTourService {

    @Autowired
    DynamoPropertyRepository dynamoPropertyRepository;


    public DynamoProperty createTour(TourRequest tourRequest) {
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(tourRequest.getPropertyEmail());

        DynamoTour dynamoTour = new DynamoTour();

        Optional.ofNullable(tourRequest.getTitle())
                .ifPresent((title) -> dynamoTour.setTitle(title));

        Optional.ofNullable(tourRequest.getDescription())
                .ifPresent((description) -> dynamoTour.setDescription(description));

        Optional.ofNullable(tourRequest.getCity())
                .ifPresent((city) -> dynamoTour.setCity(city));

        Optional.ofNullable(tourRequest.getCountry())
                .ifPresent((country) -> dynamoTour.setCountry(country));

        Optional.ofNullable(tourRequest.getStreet())
                .ifPresent((street) -> dynamoTour.setStreet(street));

        Optional.ofNullable(tourRequest.getTargetLat())
                .ifPresent(lat -> dynamoTour.setLatitude(lat));

        Optional.ofNullable(tourRequest.getTargetLong())
                .ifPresent(longitude -> dynamoTour.setLongitude(longitude));

        dynamoTour.setImages(new ArrayList<>());

        Optional.ofNullable(tourRequest.getTourImages())
                .ifPresent(images -> {
                    images.forEach(image -> {
                        DynamoImage dynamoImage = new DynamoImage(image.getImagePath());
                        dynamoTour.getImages().add(dynamoImage);
                    });
                });

        dynamoTour.setTourStops(new ArrayList());

        tourRequest.getTourStops()
                .forEach((ts) -> {
                        dynamoTour.getTourStops().add(Mapper.mapTourStop(ts));
                    });

        dynamoProperty.getDynamoTours().add(dynamoTour);

        dynamoPropertyRepository.save(dynamoProperty);


        return dynamoProperty;
    }

    public DynamoProperty updateTour(TourRequest tourRequest) {
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(tourRequest.getPropertyEmail());
        DynamoTour dynamoTour = dynamoProperty.getDynamoTours()
                .stream().filter((tr -> tr.getId() ==  tourRequest.getId())).findFirst().get();

        int indexToUpdate = dynamoProperty.getDynamoTours().indexOf(dynamoTour);

        if(tourRequest != null) {
            Optional.ofNullable(tourRequest.getTitle())
                    .ifPresent(title -> dynamoTour.setTitle(title));

            Optional.ofNullable(tourRequest.getDescription())
                    .ifPresent(des -> dynamoTour.setDescription(des));

            Optional.ofNullable(tourRequest.getCity())
                    .ifPresent(city -> dynamoTour.setCity(city));

            Optional.ofNullable(tourRequest.getCountry())
                    .ifPresent(country -> dynamoTour.setCountry(country));

            Optional.ofNullable(tourRequest.getTargetLat())
                    .ifPresent(lat -> dynamoTour.setLatitude(lat));

            Optional.ofNullable(tourRequest.getTargetLong())
                    .ifPresent(longitutde -> dynamoTour.setLongitude(longitutde));
        }

        dynamoProperty.getDynamoTours().set(indexToUpdate, dynamoTour);

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoProperty;
    }

    public Object deleteTour(String id) {
        return null;
    }

    public Object getTourById(String id) {
        return null;
    }

    public Object getTourStopById(String id) {
        return null;
    }

    public Object createTourStop(TourStopImageRequest tourStopImageRequest) {
        return null;
    }

    public DynamoProperty deleteTourStop(String id) {
        return null;
    }

    public Object updateTourStop(String id) {
        return null;
    }
}
