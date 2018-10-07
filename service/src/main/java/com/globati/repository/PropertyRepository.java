package com.globati.repository;

import com.globati.dbmodel.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by daniel on 12/21/16.
 */
public interface PropertyRepository extends CrudRepository<Property, Long> {

    @Query("SELECT e FROM Property e WHERE e.id =:id " )
    public Property getPropertyByid(@Param("id") Long id );

    public Property getPropertyByGlobatiUsername(String username);

    public Property getPropertyByEmail(String email);

    public List<Property> getPropertyByCountry(String country);

    @Query("SELECT e FROM Property e WHERE e.city =:city " )
    public List<Property> getPropertyByCity(@Param("city") String city);

    @Query("SELECT e FROM Property e")
    public List<Property> getAllProperties();





}
