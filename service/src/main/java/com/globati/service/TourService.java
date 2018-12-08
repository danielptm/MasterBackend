package com.globati.service;

import com.globati.dbmodel.BusinessImage;
import com.globati.dbmodel.Property;
import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourStop;
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

    public Tour createTour(com.globati.request.tour.Tour tour) throws ServiceException {
        Property property = propertyService.getPropertyById(tour.getPropertyId());
        Tour tourToCreate = new Tour();
        tourToCreate.setProperty(property);
        tour.setImages(tour.getImages());
        tour.setTargetLong(tour.getTargetLong());
        tour.setTargetLat(tour.getTargetLat());
        tour.setCity(tour.getCity());
        tour.setCountry(tour.getCountry());
        tour.setStreet(tour.getStreet());
        tour.setDescription(tour.getDescription());
        tour.setTitle(tour.getTitle());
        tour.setTourStops(tour.getTourStops());
        tour.setImages(tour.getImages());
        return tourRepository.save(tourToCreate);

    }

    public Tour getTourById(Long id) {
        Tour tour = tourRepository.findOne(id);
        List<TourStop> tourStops = tourStopService.getTourStopsByPropertyId(id);
        List<BusinessImage> tourImages = imageService.getImagesByEntityId(id);
        tour.setTourImages(tourImages);
        tour.setTourStops(tourStops);
        return tour;
    }
}
