package com.globati.repository.mysql;

import com.globati.mysql.dbmodel.TourImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourImageRepository extends CrudRepository<TourImage, Long> {

    @Query("SELECT ti FROM TourImage ti WHERE ti.tour.id=:id")
    List<TourImage> getImagesByEntityId(@Param("id") Long id);

}
