package com.globati.repository;

import com.globati.dbmodel.Recommendation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by daniel on 12/21/16.
 */
public interface RecommendationRepository extends CrudRepository<Recommendation, Long> {

    public List<Recommendation> getAllRecommendationsBy_country(String country);

    @Query("SELECT r FROM Recommendation r WHERE r._employee.id=:id AND r._active=:active")
    public List<Recommendation> getAllRecommendationsBy_employee_idAndActive(@Param("id") Long id, @Param("active") boolean active);

}
