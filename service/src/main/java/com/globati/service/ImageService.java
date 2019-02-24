package com.globati.service;



import com.globati.mysql.dbmodel.Tour;
import com.globati.mysql.dbmodel.TourImage;
import com.globati.mysql.dbmodel.TourStop;
import com.globati.mysql.dbmodel.TourStopImage;
import com.globati.repository.mysql.TourImageRepository;
import com.globati.repository.mysql.TourStopImageRepository;
import com.globati.request.tour.TourImageRequest;
import com.globati.request.tour.TourStopImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    TourImageRepository imageRepository;

    @Autowired
    TourStopImageRepository tourStopImageRepository;

    public java.util.List<TourImage> getImagesByTourId(Long id) {
        return imageRepository.getImagesByEntityId(id);
    }

    public java.util.List<TourImage> mapTourImageRequestsToTourImages(List<TourImageRequest> imagePaths, Tour tour) {

        List<TourImage> tourImages = new ArrayList<>();

        for(TourImageRequest bi: imagePaths) {
            TourImage tourImage = null;
            if(bi.getId() != null) {
                tourImage = imageRepository.findOne(bi.getId());
            }
            if(tourImage == null) {
                tourImage = new TourImage(tour, bi.getImagePath());
            } else {
                tourImage.setImagePath(bi.getImagePath());
            }
            tourImages.add(tourImage);
        }
        return tourImages;
    }

    public List<TourStopImage> mapTourStopImageRequestsToTourStopImages(List<TourStopImageRequest> imagesRequests, TourStop tourStop) {
        List<TourStopImage> tourStopImages = new ArrayList<>();

        for(TourStopImageRequest tsif: imagesRequests) {
            TourStopImage tourStopImage = null;
            if(tsif.getId() != null) {
                tourStopImage = tourStopImageRepository.findOne(tsif.getId());
            }
            if(tourStopImage == null) {
                tourStopImage = new TourStopImage(tourStop, tsif.getImagePath());
            } else {
                tourStopImage.setImagePath(tsif.getImagePath());
            }
            tourStopImages.add(tourStopImage);
        }
        return tourStopImages;
    }

    public List<TourStopImage> getTourStopImagesByTourStopId(Long id) {
        return tourStopImageRepository.getImagesByTourStopId(id);
    }
}
