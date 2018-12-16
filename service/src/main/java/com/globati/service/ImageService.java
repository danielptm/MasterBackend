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

    public java.util.List<BusinessImage> getImagesByEntityId(Long id) {
        return imageRepository.getImagesByEntityId(id);
    }

    public java.util.List<BusinessImage> mapImagesToBusinessImages(List<String> imagePaths, ImageType imageType, Tour tour) {

        List<BusinessImage> businessImages = new ArrayList<>();
        for(String path: imagePaths) {
            BusinessImage businessImage = new BusinessImage();
            businessImage.setImageType(imageType);
            businessImage.setPath(path);
            businessImage.setTour(tour);
            businessImages.add(businessImage);
        }

        return businessImages;
    }
}
