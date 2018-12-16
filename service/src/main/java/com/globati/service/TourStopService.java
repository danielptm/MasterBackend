package com.globati.service;

import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourStop;
import com.globati.repository.TourStopRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;



@Service
public class TourStopService {

    @Autowired
    TourStopRepository tourStopRepository;

    private static final Logger log = LogManager.getLogger(TourStopService.class);

    public TourStop createTourStop(TourStop tour) {
        return tourStopRepository.save(tour);
    }

    public List<TourStop> getTourStopsByTourId(Long id) {
        return tourStopRepository.getTourStopsByTourId(id);
    }


    public List<TourStop> mapRequestTourStopsToDbModelTourStops(Tour tour, List<com.globati.request.tour.TourStop> tourStops){
        List<TourStop> dbTourStops = new ArrayList<>();
        for(com.globati.request.tour.TourStop tourStop: tourStops){
            TourStop dbTourStop = new TourStop();
            dbTourStop.setStopOrder(tourStop.getOrderNumber());
            dbTourStop.setTour(tour);
            dbTourStop.setActive(true);
            dbTourStop.setCity(tour.getCity());
            dbTourStop.setCountry(tour.getCountry());
            dbTourStop.setDescription(tour.getDescription());
            dbTourStop.setLocation(tour.getLocation());
            dbTourStop.setStreet(tour.getStreet());
            dbTourStop.setTargetLat(tour.getTargetLat());
            dbTourStop.setTargetLong(tour.getTargetLong());
            dbTourStop.setTitle(tour.getTitle());
            dbTourStops.add(dbTourStop);
        }
        return dbTourStops;
    }

}
