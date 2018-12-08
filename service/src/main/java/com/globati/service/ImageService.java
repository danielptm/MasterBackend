package com.globati.service;



import com.globati.dbmodel.BusinessImage;
import com.globati.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public java.util.List<BusinessImage> getImagesByEntityId(Long id) {
        return imageRepository.getImagesByEntityId(id);
    }
}
