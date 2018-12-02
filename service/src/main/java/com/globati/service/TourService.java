package com.globati.service;

import com.globati.dbmodel.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TourService {

    private static final Logger log = LogManager.getLogger(TourService.class);

    public Tour createTour(Long id, String title, String description, double targetLat,
                           double targetLong, String street, String city, String country,
                           List<String> images){

        return null;
    }
}
