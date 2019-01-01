package com.globati.service;

import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourStop;
import com.globati.dbmodel.TourStopImage;
import com.globati.repository.TourStopImageRepository;
import com.globati.repository.TourStopRepository;
import com.globati.request.tour.TourStopImageRequest;
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

    @Autowired
    TourStopImageRepository tourStopImageRepository;

    private static final Logger log = LogManager.getLogger(TourStopService.class);

    public TourStop createTourStop(TourStop tour) {
        return tourStopRepository.save(tour);
    }

    public List<TourStop> getTourStopsByTourId(Long id) {
        List<TourStop> tourStops = tourStopRepository.getTourStopsByTourId(id);
        for (TourStop tourStop: tourStops) {
            List<TourStopImage> tourStopImages = tourStopImageRepository.getImagesByTourStopId(tourStop.getId());
            tourStop.setTourStopImages(tourStopImages);
        }
        return tourStops;
    }


    public List<TourStop> mapRequestTourStopsToDbModelTourStops(Tour tour, List<TourStopRequest> tourStopRequests){
        List<TourStop> dbTourStops = new ArrayList<>();

        for(TourStopRequest tourStopRequest : tourStopRequests){
            List<TourStopImage> tourStopImages = new ArrayList<>();
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

            if(tourStopRequest.getImages() != null && tourStopRequest.getImages().size() > 0) {
                TourStopImage tourStopImage = null;
                for (TourStopImageRequest tourStopImageRequest : tourStopRequest.getImages()) {
                    if (tourStopImageRequest.getId() != null) {
                        tourStopImage = tourStopImageRepository.findOne(tourStopImageRequest.getId());
                    }
                    if (tourStopImage == null ) {
                        tourStopImage = new TourStopImage(dbTourStop, tourStopImageRequest.getPath());
                    }
                    tourStopImages.add(tourStopImage);
                }
            }
            dbTourStop.setTourStopImages(tourStopImages);
        }
        return dbTourStops;
    }
}
