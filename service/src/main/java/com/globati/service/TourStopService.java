package com.globati.service;


import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourStop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourStopService {

    private static final Logger log = LogManager.getLogger(TourStopService.class);


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
