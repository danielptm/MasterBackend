package com.globati.repository;

import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourStop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourStopRepository extends CrudRepository<TourStop, Long> {

    @Query("SELECT t FROM TourStop t WHERE t.tour.id=:id")
    List<TourStop> getTourStopsByTourId(@Param("id") Long id);

}
