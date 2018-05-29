package com.globati.repository;

import com.globati.dbmodel.HelpRecommendation;
import com.globati.dbmodel.Recommendation;
import org.springframework.data.repository.CrudRepository;

public interface HelpRecommendationRepository extends CrudRepository<HelpRecommendation, Long> {
}
