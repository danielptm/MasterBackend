package com.globati.service;

import com.globati.mysql.dbmodel.Tour;
import com.globati.mysql.dbmodel.TourStop;
import com.globati.mysql.dbmodel.TourStopImage;
import com.globati.repository.mysql.TourStopImageRepository;
import com.globati.repository.mysql.TourStopRepository;
import com.globati.request.tour.TourStopRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Service
public class TourStopService {

    @Autowired
    TourStopRepository tourStopRepository;

    @Autowired
    TourStopImageRepository tourStopImageRepository;

    @Autowired
    ImageService imageService;

    private static final Logger log = LogManager.getLogger(TourStopService.class);

    public TourStop createTourStop(TourStop tour) {
        return tourStopRepository.save(tour);
    }

    public TourStop getTourStopById(Long id) {
        TourStop tourStop = tourStopRepository.findOne(id);
        List<TourStopImage> tourStopImages = tourStopImageRepository.getImagesByTourStopId(tourStop.getId());
        tourStop.setTourStopImages(tourStopImages);
        return tourStop;
    }

    public TourStop updateTourStop(TourStop tourStop) {
        TourStop updatedTourStop = tourStopRepository.save(tourStop);
        return getTourStopById(updatedTourStop.getId());
    }

    public List<TourStop> getTourStopsByTourId(Long id) {
        List<TourStop> tourStops = tourStopRepository.getTourStopsByTourId(id, true);
        for (TourStop tourStop: tourStops) {
            List<TourStopImage> tourStopImages = tourStopImageRepository.getImagesByTourStopId(tourStop.getId());
            tourStop.setTourStopImages(tourStopImages);
        }
        return tourStops;
    }

    public TourStop setTourStopToInactive(Long id) {
        TourStop tourStop = getTourStopById(id);
        tourStop.setActive(false);
        tourStop.setDateInactive(new Date());
        return tourStopRepository.save(tourStop);
    }


    public List<TourStop> mapRequestTourStopsToDbModelTourStops(Tour tour, List<TourStopRequest> tourStopRequests){
        List<TourStop> dbTourStops = new ArrayList<>();

        for(TourStopRequest tourStopRequest : tourStopRequests) {
            List<TourStopImage> tourStopImages = new ArrayList<>();
            TourStop tourStop = null;
            if (tourStopRequest.getId() != null) {
                tourStop = getTourStopById(tourStopRequest.getId());
            }
            if(tourStop == null) {
                tourStop = new TourStop();
                tourStop.setStopOrder(tourStopRequest.getStopOrder());
                tourStop.setTour(tour);
                tourStop.setActive(true);
                tourStop.setDateActive(new Date());
                tourStop.setCity(tourStopRequest.getCity());
                tourStop.setStopOrder(tourStopRequest.getStopOrder());
                tourStop.setCountry(tourStopRequest.getCountry());
                tourStop.setDescription(tourStopRequest.getDescription());
                tourStop.setStreet(tourStopRequest.getStreet());
                tourStop.setTargetLat(tourStopRequest.getTargetLat());
                tourStop.setTargetLong(tourStopRequest.getTargetLong());
                tourStop.setTitle(tourStopRequest.getTitle());
                dbTourStops.add(tourStop);

            } else {
                tourStop.setStopOrder(tourStopRequest.getStopOrder());
                tourStop.setTour(tour);
                tourStop.setActive(true);
                tourStop.setCity(tourStopRequest.getCity());
                tourStop.setCountry(tourStopRequest.getCountry());
                tourStop.setDescription(tourStopRequest.getDescription());
                tourStop.setStreet(tourStopRequest.getStreet());
                tourStop.setTargetLat(tourStopRequest.getTargetLat());
                tourStop.setStopOrder(tourStopRequest.getStopOrder());
                tourStop.setTargetLong(tourStopRequest.getTargetLong());
                tourStop.setTitle(tourStopRequest.getTitle());
                dbTourStops.add(tourStop);
            }
            TourStop persistedTourStop = updateTourStop(tourStop);

            tourStop.setTourStopImages(imageService.mapTourStopImageRequestsToTourStopImages(tourStopRequest.getTourStopImages(), persistedTourStop));
            dbTourStops.add(tourStop);

        }
        return dbTourStops;
    }
}
