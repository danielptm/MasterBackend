package com.globati.repository;

import com.globati.dbmodel.RecommendationImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RecommendationImageRepository extends CrudRepository<RecommendationImage, Long> {


    @Query(value = "DELETE FROM RecommendationImage r WHERE r.recommendationid =:recommendationid", nativeQuery =  true)
    public void deleteAllRecommendationImagesWithRecommendationId(@Param("recommendationid") Long recommendationid);

}
