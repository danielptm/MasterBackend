package com.globati.repository;

import com.globati.mysql.dbmodel.TourStopImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourStopImageRepository extends CrudRepository <TourStopImage, Long>{

    @Query("SELECT tis FROM TourStopImage tis WHERE tis.tourstop.id=:id")
    List<TourStopImage> getImagesByTourStopId(@Param("id") Long id);
}
