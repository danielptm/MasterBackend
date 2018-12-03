package com.globati.service;

import com.globati.dbmodel.Property;
import com.globati.dbmodel.Tour;
import com.globati.repository.TourRepository;
import com.globati.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TourService {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    PropertyService propertyService;

    private static final Logger log = LogManager.getLogger(TourService.class);

    public Tour createTour(com.globati.request.tour.Tour tour) throws ServiceException {
        Property property = propertyService.getPropertyById(tour.getPropertyId());
        Tour tourToCreate = new Tour();
        tourToCreate.setProperty(property);

        tourRepository.save(tourToCreate);
        return null;
    }
}
