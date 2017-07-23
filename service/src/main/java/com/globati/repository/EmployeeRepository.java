package com.globati.repository;

import com.globati.dbmodel.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 12/21/16.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e._id =:_id " )
    public Employee getEmployeeBy_id( @Param("_id") Long _id );

    public Employee getEmployeeBy_globatiUsername(String username);

//    public Employee getEmployeeBy_facebookId(String id);

    public Employee getEmployeeBy_email(String email);

    public List<Employee> getEmployeeBy_country(String country);

    public List<Employee> getEmployeeBy_city(String city);


}
