package com.globati.repository;

import com.globati.dbmodel.TourImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourImageRepository extends CrudRepository<TourImage, Long> {

    @Query("SELECT bi FROM BusinessImage bi WHERE bi.tour.id=:id")
    List<TourImage> getImagesByEntityId(@Param("id") Long id);

}
