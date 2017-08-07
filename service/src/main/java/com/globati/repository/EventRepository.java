package com.globati.repository;

import com.globati.dbmodel.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by daniel on 12/21/16.
 */
public interface EventRepository extends CrudRepository<Event, Long>{

    public List<Event> findByCountry(String country);

    @Query("SELECT e FROM Event e WHERE e.employee.id=:id AND e.active=:active")
    public List<Event> getAllEventsBy_employee_id(@Param("id") Long id, @Param("active") boolean active);

    @Query("SELECT e FROM Event e WHERE e.active=:active")
    public List<Event> getAllActiveEvents(@Param("active") boolean active);




}
