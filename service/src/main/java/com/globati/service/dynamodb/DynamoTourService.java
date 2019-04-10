package com.globati.service.dynamodb;

import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.dynamodb.tour.DynamoTourStop;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import com.globati.api.tour.TourRequest;
import com.globati.api.tour.TourStopRequest;
import com.globati.utildb.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DynamoTourService {

    @Autowired
    DynamoPropertyRepository dynamoPropertyRepository;


    public DynamoTour createTour(TourRequest tourRequest) {
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

        Optional.ofNullable(tourRequest.getImages())
                .ifPresent(images -> {
                    images.forEach(image -> {
                        DynamoImage dynamoImage = new DynamoImage(image.getPath());
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

        return dynamoTour;
    }

    public DynamoTour updateTour(TourRequest tourRequest) {
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

        return dynamoTour;
    }

    public DynamoProperty deleteTour(String email, String tourId) {
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(email);

        DynamoTour dynamoTour = dynamoProperty.getDynamoTours().stream()
                .filter(tr -> tr.getId().equals(tourId))
                .findFirst().get();

        dynamoProperty.getDynamoTours().remove(dynamoTour);

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoProperty;
    }

    public DynamoTour getTourById(String id) {
        DynamoTour dynamoTour = null;
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(id);

        for (DynamoTour tour: dynamoProperty.getDynamoTours()) {
            if(tour.getId().equals(id)) {
                dynamoTour = tour;
            }
        }
        return dynamoTour;
    }

    public DynamoTourStop getTourStopById(String propertyEmail, String id) {
        DynamoTourStop stopToReturn = null;
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(propertyEmail);

        for(DynamoTour tour: dynamoProperty.getDynamoTours()) {
            for(DynamoTourStop dynamoTourStop: tour.getTourStops()) {
                if(dynamoTourStop.getId().equals(id)) {
                    stopToReturn = dynamoTourStop;
                }
            }
        }
        return stopToReturn;
    }

    public DynamoTourStop createTourStop(TourStopRequest tourStopRequest) {
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(tourStopRequest.getPropertyEmail());
        DynamoTourStop dynamoTourStop = new DynamoTourStop();

        DynamoTour dynamoTour = dynamoProperty.getDynamoTours().stream()
                .filter(tr -> tr.getId().equals(tourStopRequest.getTourId()))
                .findFirst().get();

        int indexToUpdate = dynamoProperty.getDynamoTours().indexOf(dynamoTour);

        Optional.ofNullable(tourStopRequest.getTitle())
                .ifPresent(title -> dynamoTourStop.setTitle(title));

        Optional.ofNullable(tourStopRequest.getDescription())
                .ifPresent(des -> dynamoTourStop.setDescription(des));

        Optional.ofNullable(tourStopRequest.getTargetLong())
            .ifPresent(longitude -> dynamoTourStop.setLongitude(longitude));

        Optional.ofNullable(tourStopRequest.getTargetLat())
                .ifPresent(latitude -> dynamoTourStop.setLatitude(latitude));

        Optional.ofNullable(tourStopRequest.getStreet())
                .ifPresent(street -> dynamoTourStop.setStreet(street));

        Optional.ofNullable(tourStopRequest.getCity())
                .ifPresent(city -> dynamoTourStop.setCity(city));

        Optional.ofNullable(tourStopRequest.getCountry())
                .ifPresent(country -> dynamoTourStop.setCountry(country));

        Optional.ofNullable(tourStopRequest.getStopOrder())
                .ifPresent(so -> dynamoTourStop.setStopOrder(so));

        Optional.ofNullable(tourStopRequest.getTourStopImages())
                .ifPresent(images -> {
                    images.forEach(image ->{
                        DynamoImage dynamoImage = new DynamoImage(image.getPath());
                        dynamoTourStop.getImages().add(dynamoImage);
                    });
                });

        Optional.ofNullable(dynamoTour)
                .ifPresent(dt -> {
                    dt.getTourStops().add(dynamoTourStop);
                });

        dynamoProperty.getDynamoTours().set(indexToUpdate, dynamoTour);

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoTourStop;
    }

    public DynamoProperty deleteTourStop(String propertyEmail, String tourId, String tourStopId) {
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(propertyEmail);

        DynamoTour dynamoTour = dynamoProperty.getDynamoTours().stream()
                .filter(tr -> tr.getId().equals(tourId))
                .findFirst().get();

        int tourIndextoUpdate = dynamoProperty.getDynamoTours().indexOf(dynamoTour);

        DynamoTourStop dynamoTourStop = dynamoTour.getTourStops().stream()
                .filter(tr -> tr.getId().equals(tourStopId))
                .findFirst().get();

        int tourStopIndexToUpdate = dynamoProperty.getDynamoTours()
                .get(tourIndextoUpdate)
                .getTourStops().indexOf(dynamoTourStop);


        dynamoProperty
                .getDynamoTours().get(tourIndextoUpdate)
                .getTourStops().remove(tourStopIndexToUpdate);

        dynamoPropertyRepository.save(dynamoProperty);


        return dynamoProperty;
    }

    public DynamoTourStop updateTourStop(TourStopRequest tourStopRequest) {
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(tourStopRequest.getPropertyEmail());

        DynamoTour dynamoTour = dynamoProperty.getDynamoTours().stream()
                .filter(tr -> tr.getId().equals(tourStopRequest.getTourId()))
                .findFirst().get();

        int tourIndextoUpdate = dynamoProperty.getDynamoTours().indexOf(dynamoTour);

        DynamoTourStop dynamoTourStop = dynamoTour.getTourStops().stream()
                .filter(tr -> tr.getId().equals(tourStopRequest.getId()))
                .findFirst().get();

        int tourStopIndexToUpdate = dynamoProperty.getDynamoTours()
                .get(tourIndextoUpdate)
                .getTourStops().indexOf(dynamoTourStop);


        Optional.ofNullable(tourStopRequest.getTitle())
                .ifPresent(title -> dynamoTourStop.setTitle(title));

        Optional.ofNullable(tourStopRequest.getDescription())
                .ifPresent(des -> dynamoTourStop.setDescription(des));

        Optional.ofNullable(tourStopRequest.getTargetLong())
                .ifPresent(longitude -> dynamoTourStop.setLongitude(longitude));

        Optional.ofNullable(tourStopRequest.getTargetLat())
                .ifPresent(latitude -> dynamoTourStop.setLatitude(latitude));

        Optional.ofNullable(tourStopRequest.getStreet())
                .ifPresent(street -> dynamoTourStop.setStreet(street));

        Optional.ofNullable(tourStopRequest.getCity())
                .ifPresent(city -> dynamoTourStop.setCity(city));

        Optional.ofNullable(tourStopRequest.getCountry())
                .ifPresent(country -> dynamoTourStop.setCountry(country));

        Optional.ofNullable(tourStopRequest.getStopOrder())
                .ifPresent(so -> dynamoTourStop.setStopOrder(so));

        Optional.ofNullable(tourStopRequest.getTourStopImages())
                .ifPresent(images -> {
                    images.forEach(image ->{
                        DynamoImage dynamoImage = new DynamoImage(image.getPath());
                        dynamoTourStop.getImages().add(dynamoImage);
                    });
                });

        dynamoProperty.getDynamoTours()
                .get(tourIndextoUpdate)
                .getTourStops()
                .set(tourStopIndexToUpdate, dynamoTourStop);

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoTourStop;
    }

    public List<DynamoRecommendation> getToursByPropertyEmail(String email ) {
        return dynamoPropertyRepository.findOne(email).getDynamoRecommendations();
    }


}
