package com.globati.service;

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
    TourStopService tourStopService;

    private static final Logger log = LogManager.getLogger(TourStopService.class);

    public List<TourStop> getTourStopsByPropertyId(Long id) {
        return tourStopService.getTourStopsByPropertyId(id);
    }


    public List<TourStop> mapRequestTourStopsToDbModelTourStops(List<com.globati.request.tour.TourStop> tourStops){
        List<TourStop> dbTourStops = new ArrayList<>();
        for(com.globati.request.tour.TourStop tourStop: tourStops){
            TourStop tourStop1 = new TourStop();
            //Do this



            dbTourStops.add(tourStop1);
        }
        return dbTourStops;
    }

}
