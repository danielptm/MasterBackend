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
            dbTourStop.setCity(tourStop.getCity());
            dbTourStop.setCountry(tourStop.getCountry());
            dbTourStop.setDescription(tourStop.getDescription());
            dbTourStop.setStreet(tourStop.getStreet());
            dbTourStop.setTargetLat(tourStop.getTargetLat());
            dbTourStop.setTargetLong(tourStop.getTargetLong());
            dbTourStop.setTitle(tourStop.getTitle());
            dbTourStops.add(dbTourStop);
        }
        return dbTourStops;
    }

}
