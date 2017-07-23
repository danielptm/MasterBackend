package com.globati.repository;


import com.globati.dbmodel.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 12/20/16.
 */

public interface DealRepository extends CrudRepository<Deal, Long>{

    public List<Deal> getDealsBy_country(String country);

    @Query("SELECT e FROM Employee e WHERE e._id =:_id" )
    public List<Deal> findDealsBy_employee_id( @Param("_id") Long _id );

    @Query("SELECT d FROM Deal d WHERE d._country =:country  AND d._active=:active" )
    public List<Deal> getActiveDealsByCountry(@Param("country") String country, @Param("active") boolean active);

    @Query("SELECT d FROM Deal d WHERE d._employee._id=:_id AND d._active=:active")
    public List<Deal> getActiveDealsByEmployee(@Param("_id") Long _id, @Param("active") boolean active);

    @Query("SELECT d FROM Deal d WHERE MONTH(d._datemade)=?1 AND YEAR(d._datemade)=?2 AND d._employee._id=?3 AND _active=true")
    public List<Deal> getDealsCreatedForMonth(Integer month, Integer year, Long _id);


    @Query("SELECT d FROM Deal d where d._active=:active")
    List<Deal> getAllActiveDeals(@Param("active") boolean active);
}
