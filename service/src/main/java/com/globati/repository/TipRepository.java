package com.globati.repository;

import com.globati.dbmodel.Tip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipRepository extends CrudRepository<Tip, Long>{

    @Query("SELECT t FROM Tip t WHERE t.employee.id =:id")
    List<Tip> getTipsByEmployeeId(@Param("id") Long id);

}
