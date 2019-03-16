package com.globati.utildb;

import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.tour.DynamoTourStop;
import com.globati.request.tour.TourStopRequest;

import java.util.ArrayList;
import java.util.Optional;

public class Mapper {

    private Mapper(){}

    public static DynamoTourStop mapTourStop(TourStopRequest tourStopRequest) {
        DynamoTourStop dynamoTourStop = new DynamoTourStop();

        Optional.ofNullable(tourStopRequest.getTitle())
                .ifPresent(title -> dynamoTourStop.setTitle(title));

        Optional.ofNullable(tourStopRequest.getDescription())
                .ifPresent(description -> dynamoTourStop.setDescription(description));

        dynamoTourStop.setImages(new ArrayList<>());

        Optional.ofNullable(tourStopRequest.getTourStopImages())
                .ifPresent(images -> {
                    images.forEach(image -> {
                        DynamoImage dynamoImage = new DynamoImage(image.getImagePath());
                        dynamoTourStop.getImages().add(dynamoImage);
                    });
                });

        Optional.ofNullable(tourStopRequest.getStopOrder())
                .ifPresent(order -> dynamoTourStop.setStopOrder(order));

        Optional.ofNullable(tourStopRequest.getCity())
                .ifPresent(city -> dynamoTourStop.setCity(city));

        Optional.ofNullable(tourStopRequest.getCountry())
                .ifPresent(country -> dynamoTourStop.setCountry(country));

        Optional.ofNullable(tourStopRequest.getStreet())
                .ifPresent(street -> dynamoTourStop.setStreet(street));

        Optional.ofNullable(tourStopRequest.getTargetLat())
                .ifPresent(lat -> dynamoTourStop.setLatitude(lat));

        Optional.ofNullable(tourStopRequest.getTargetLong())
                .ifPresent(longitude -> dynamoTourStop.setLongitude(longitude));

        return dynamoTourStop;
    }
}
