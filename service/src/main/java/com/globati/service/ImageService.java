package com.globati.service;



import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourImage;
import com.globati.repository.TourImageRepository;
import com.globati.request.tour.TourImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    TourImageRepository imageRepository;

    public java.util.List<TourImage> getImagesByTourId(Long id) {
        return imageRepository.getImagesByEntityId(id);
    }

    public java.util.List<TourImage> mapImagesToBusinessImages(List<TourImageRequest> imagePaths, Tour tour) {

        List<TourImage> businessImages = new ArrayList<>();

        for(TourImageRequest bi: imagePaths) {
            TourImage businessImage = new TourImage();
            businessImage.setPath(bi.getImagePath());
            businessImage.setTour(tour);
            businessImages.add(businessImage);
        }

        return businessImages;
    }
}
