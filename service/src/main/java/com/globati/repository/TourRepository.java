package com.globati.repository;

import com.globati.mysql.dbmodel.Tour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepository  extends CrudRepository<Tour, Long> {

    @Query("SELECT t FROM Tour t WHERE t.property.id=:id AND t.active=:active")
    List<Tour> getToursByPropertyId(@Param("id") Long id, @Param("active") boolean active);

}
