package com.globati.service;

import com.globati.dbmodel.BusinessImage;
import com.globati.dbmodel.Property;
import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourStop;
import com.globati.enums.ImageType;
import com.globati.repository.TourRepository;
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

    public Tour createTour(com.globati.request.tour.Tour tour) {
        Property property = null;

        // This should make use of an exception mapper and give a message Property not found when getting a Tour.
        try {
            property = propertyService.getPropertyById(tour.getPropertyId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        Tour tourToCreate = new Tour();
        tourToCreate.setProperty(property);

        tourToCreate.setTargetLong(tour.getTargetLong());
        tourToCreate.setTargetLat(tour.getTargetLat());
        tourToCreate.setCity(tour.getCity());
        tourToCreate.setCountry(tour.getCountry());
        tourToCreate.setStreet(tour.getStreet());
        tourToCreate.setDescription(tour.getDescription());
        tourToCreate.setTitle(tour.getTitle());

        //Map with image service
        List<BusinessImage> images = imageService.mapImagesToBusinessImages(tour.getImages(), tourToCreate);
        tourToCreate.setTourImages(images);

//        Map with tourStopService
        List<TourStop> tourStops = tourStopService.mapRequestTourStopsToDbModelTourStops(tourToCreate, tour.getTourStops());
        tourToCreate.setTourStops(tourStops);

        return tourRepository.save(tourToCreate);

    }

    public List<Tour> getToursByPropertyId(Long id) {
        List<Tour> tours = tourRepository.getToursByPropertyId(id);
        for(Tour tour: tours) {
            List<TourStop> tourStops = tourStopService.getTourStopsByTourId(tour.getId());
            List<BusinessImage> tourImages = imageService.getImagesByTourId(tour.getId());
            tour.setTourStops(tourStops);
            tour.setTourImages(tourImages);
        }
        return tours;
    }

    public Tour updateTour(com.globati.request.tour.Tour tour) {
        Tour oldTour = tourRepository.findOne(tour.getId());
        oldTour.setTitle(tour.getTitle());
        oldTour.setTourImages(imageService.mapImagesToBusinessImages(tour.getImages()));
        oldTour.setDescription(tour.getDescription());
        oldTour.setTourStops(tourStopService.mapRequestTourStopsToDbModelTourStops(tour, tour.getTourStops()));
        oldTour.setCity(tour.getCity());
        oldTour.setStreet(tour.getStreet());
        oldTour.setTargetLat(tour.getTargetLat());
        oldTour.setTargetLong(tour.getTargetLong());
        oldTour.setCountry(tour.getCountry());
        return tourRepository.save(oldTour);
    }
}
