package com.globati.repository;

import com.globati.dbmodel.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropertyRepository extends CrudRepository<Property, Long> {

    public Property getPropertyByEmail(String email);

    @Query("SELECT p FROM Property p")
    public List<Property> getAllProperties();

}
