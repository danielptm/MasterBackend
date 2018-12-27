package com.globati.service;

import com.globati.dbmodel.Property;
import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourImage;
import com.globati.dbmodel.TourStop;
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

        //Map with image service
        List<TourImage> images = imageService.mapImagesToBusinessImages(tourRequest.getImages(), tourToCreate);
        tourToCreate.setTourImages(images);

//        Map with tourStopService
        List<TourStop> tourStops = tourStopService.mapRequestTourStopsToDbModelTourStops(tourToCreate, tourRequest.getTourStopRequests());
        tourToCreate.setTourStops(tourStops);

        return tourRepository.save(tourToCreate);

    }

    public List<Tour> getToursByPropertyId(Long id) {
        List<Tour> tours = tourRepository.getToursByPropertyId(id);
        for(Tour tour: tours) {
            List<TourStop> tourStops = tourStopService.getTourStopsByTourId(tour.getId());
//            List<TourImageRequest> tourImages = imageService.getImagesByTourId(tour.getId());
            tour.setTourStops(tourStops);
//            tour.setTourImages(tourImages);
        }
        return tours;
    }

    public Tour updateTour(TourRequest tourRequest) {
        Tour oldTour = tourRepository.findOne(tourRequest.getId());
        oldTour.setTitle(tourRequest.getTitle());
        oldTour.setTourImages(imageService.mapImagesToBusinessImages(tourRequest.getImages(), oldTour));
        oldTour.setTourStops(tourStopService.mapRequestTourStopsToDbModelTourStops(oldTour, tourRequest.getTourStopRequests()));
        oldTour.setDescription(tourRequest.getDescription());
        oldTour.setCity(tourRequest.getCity());
        oldTour.setStreet(tourRequest.getStreet());
        oldTour.setTargetLat(tourRequest.getTargetLat());
        oldTour.setTargetLong(tourRequest.getTargetLong());
        oldTour.setCountry(tourRequest.getCountry());
        return tourRepository.save(oldTour);
    }
}
