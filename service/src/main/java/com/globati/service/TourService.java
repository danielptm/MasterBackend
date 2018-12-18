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

    public Tour createTour(com.globati.request.tour.Tour tour) throws ServiceException {
        Property property = propertyService.getPropertyById(tour.getPropertyId());
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
//            List<BusinessImage> tourImages = imageService.getImagesByTourId(tour.getId());
            tour.setTourStops(tourStops);
            tour.setTourImages(null);
        }
        return tours;
    }
}
