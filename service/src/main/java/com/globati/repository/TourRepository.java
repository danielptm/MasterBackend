package com.globati.repository;

import com.globati.dbmodel.Recommendation;
import com.globati.dbmodel.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository  extends CrudRepository<Tour, Long> {
}
