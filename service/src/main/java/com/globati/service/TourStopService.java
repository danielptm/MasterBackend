package com.globati.service;

import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourStop;
import com.globati.repository.TourStopRepository;
import com.globati.request.tour.TourStopRequest;
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


    public List<TourStop> mapRequestTourStopsToDbModelTourStops(Tour tour, List<TourStopRequest> tourStopRequests){
        List<TourStop> dbTourStops = new ArrayList<>();
        for(TourStopRequest tourStopRequest : tourStopRequests){
            TourStop dbTourStop = new TourStop();
            dbTourStop.setStopOrder(tourStopRequest.getOrderNumber());
            dbTourStop.setTour(tour);
            dbTourStop.setActive(true);
            dbTourStop.setCity(tourStopRequest.getCity());
            dbTourStop.setCountry(tourStopRequest.getCountry());
            dbTourStop.setDescription(tourStopRequest.getDescription());
            dbTourStop.setStreet(tourStopRequest.getStreet());
            dbTourStop.setTargetLat(tourStopRequest.getTargetLat());
            dbTourStop.setTargetLong(tourStopRequest.getTargetLong());
            dbTourStop.setTitle(tourStopRequest.getTitle());
            dbTourStops.add(dbTourStop);
        }
        return dbTourStops;
    }

}
