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

    public List<Recommendation> getAllRecommendationsByCountry(String country);

    @Query("SELECT r FROM Recommendation r WHERE r.property.id=:id AND r.active=:active")
    public List<Recommendation> getAllRecommendationsByPropertyIdAndActive(@Param("id") Long id, @Param("active") boolean active);

}
