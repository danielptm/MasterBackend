package com.globati.repository;

import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourStop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TourStopRepository extends CrudRepository<Tour, Long> {


    @Query("SELECT ts FROM TourStop ts WHERE ts.property.id=:id")
    List<TourStop> getTourStopsByPropertyId(Long id);

}
