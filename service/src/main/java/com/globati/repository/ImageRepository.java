package com.globati.repository;

import com.globati.dbmodel.BusinessImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends CrudRepository<BusinessImage, Long> {

    @Query("SELECT bi FROM BusinessImage bi WHERE bi.tour.id=:id")
    List<BusinessImage> getImagesByEntityId(@Param("id") Long id);

}
