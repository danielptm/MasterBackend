package com.globati.service;



import com.globati.dbmodel.BusinessImage;
import com.globati.dbmodel.Tour;
import com.globati.enums.ImageType;
import com.globati.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public java.util.List<BusinessImage> getImagesByTourId(Long id) {
        return imageRepository.getImagesByEntityId(id);
    }

    public java.util.List<BusinessImage> mapImagesToBusinessImages(List<com.globati.request.tour.BusinessImage> imagePaths, Tour tour) {

        List<BusinessImage> businessImages = new ArrayList<>();

        for(com.globati.request.tour.BusinessImage bi: imagePaths) {
            BusinessImage businessImage = new BusinessImage();
            businessImage.setImageType(ImageType.valueOf(bi.getImageType()));
            businessImage.setPath(bi.getImagePath());
            businessImage.setTour(tour);
            businessImage.setStopOrder(bi.getStopOrder());
            businessImages.add(businessImage);
        }

        return businessImages;
    }
}
