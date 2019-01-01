package com.globati.service;

import com.globati.dbmodel.*;
import com.globati.repository.TourRepository;
import com.globati.request.tour.TourRequest;
import com.globati.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TourService {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    PropertyService propertyService;

    @Autowired
    TourStopService tourStopService;

    @Autowired
    ImageService imageService;

    private static final Logger log = LogManager.getLogger(TourService.class);

    public Tour createTour(TourRequest tourRequest) {
        Property property = null;

        // This should make use of an exception mapper and give a message Property not found when getting a TourRequest.
        try {
            property = propertyService.getPropertyById(tourRequest.getPropertyId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        Tour tourToCreate = new Tour();
        tourToCreate.setProperty(property);

        tourToCreate.setTargetLong(tourRequest.getTargetLong());
        tourToCreate.setTargetLat(tourRequest.getTargetLat());
        tourToCreate.setCity(tourRequest.getCity());
        tourToCreate.setCountry(tourRequest.getCountry());
        tourToCreate.setStreet(tourRequest.getStreet());
        tourToCreate.setDescription(tourRequest.getDescription());
        tourToCreate.setTitle(tourRequest.getTitle());

        Tour persistedTour = tourRepository.save(tourToCreate);

        //Map with image service
        List<TourImage> images = imageService.mapTourImageRequestsToTourImages(tourRequest.getTourImages(), persistedTour);
        persistedTour.setTourImages(images);

//        Map with tourStopService
        List<TourStop> tourStops = tourStopService.mapRequestTourStopsToDbModelTourStops(persistedTour, tourRequest.getTourStopRequests());
        persistedTour.setTourStops(tourStops);

        Tour persistedTourWithData = tourRepository.save(persistedTour);

        return persistedTourWithData;

    }

    public List<Tour> getToursByPropertyId(Long id) {
        List<Tour> tours = tourRepository.getToursByPropertyId(id);
        for(Tour tour: tours) {
            List<TourStop> tourStops = tourStopService.getTourStopsByTourId(tour.getId());
            for(TourStop tourStop: tourStops) {
                tourStop.setTourStopImages(imageService.getTourStopImagesByTourStopId(tourStop.getId()));
            }
            tour.setTourStops(tourStops);
        }
        return tours;
    }

    public Tour getTourByTourId(Long id) {
        Tour tour = tourRepository.findOne(id);
        List<TourStop> tourStops = tourStopService.getTourStopsByTourId(id);
        for(TourStop tourStop: tourStops) {
            List<TourStopImage> tourStopImages = imageService.getTourStopImagesByTourStopId(tourStop.getId());
            tourStop.setTourStopImages(tourStopImages);
        }
        tour.setTourStops(tourStops);
        return tour;
    }

    public Tour updateTour(TourRequest tourRequest) {
        Tour oldTour = getTourByTourId(tourRequest.getId());
        oldTour.setTitle(tourRequest.getTitle());
        oldTour.setTourImages(imageService.mapTourImageRequestsToTourImages(tourRequest.getTourImages(), oldTour));
        oldTour.setTourStops(tourStopService.mapRequestTourStopsToDbModelTourStops(oldTour, tourRequest.getTourStopRequests()));
        oldTour.setDescription(tourRequest.getDescription());
        oldTour.setCity(tourRequest.getCity());
        oldTour.setStreet(tourRequest.getStreet());
        oldTour.setTargetLat(tourRequest.getTargetLat());
        oldTour.setTargetLong(tourRequest.getTargetLong());
        oldTour.setCountry(tourRequest.getCountry());
        Tour updatadTour = tourRepository.save(oldTour);
        return getTourByTourId(updatadTour.getId());
    }
}
