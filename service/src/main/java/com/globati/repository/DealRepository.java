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

    public List<Deal> getDealsByCountry(String country);

    @Query("SELECT e FROM Employee e WHERE e.id =:id" )
    public List<Deal> findDealsBy_employee_id( @Param("id") Long id );

    @Query("SELECT d FROM Deal d WHERE d.country =:country  AND d.active=:active" )
    public List<Deal> getActiveDealsByCountry(@Param("country") String country, @Param("active") boolean active);

    @Query("SELECT d FROM Deal d WHERE d.employee.id=:id AND d.active=:active")
    public List<Deal> getActiveDealsByEmployee(@Param("id") Long id, @Param("active") boolean active);

    @Query("SELECT d FROM Deal d WHERE MONTH(d.datemade)=?1 AND YEAR(d.datemade)=?2 AND d.employee.id=?3 AND active=true")
    public List<Deal> getDealsCreatedForMonth(Integer month, Integer year, Long _id);

    @Query("SELECT d FROM Deal d where d.active=:active")
    List<Deal> getAllActiveDeals(@Param("active") boolean active);
}
