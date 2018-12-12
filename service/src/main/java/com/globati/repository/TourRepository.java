package com.globati.repository;

import com.globati.dbmodel.Recommendation;
import com.globati.dbmodel.Tour;
import com.globati.dbmodel.TourStop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepository  extends CrudRepository<Tour, Long> {

    @Query("SELECT t FROM Tour t WHERE t.property.id=:id")
    List<Tour> getToursByPropertyId(@Param("id") Long id);

}
