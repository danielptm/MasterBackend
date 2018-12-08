package com.globati.repository;

import com.globati.dbmodel.BusinessImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<BusinessImage, Long> {

    @Query("SELECT i FROM BusinessImage r WHERE i.tour.id=:id")
    List<BusinessImage> getImagesByEntityId(Long id);

}
