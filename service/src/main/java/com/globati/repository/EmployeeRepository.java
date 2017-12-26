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

    @Query("SELECT e FROM Employee e WHERE e.id =:id " )
    public Employee getEmployeeByid( @Param("id") Long id );

    public Employee getEmployeeByGlobatiUsername(String username);

    public Employee getEmployeeByEmail(String email);

    public List<Employee> getEmployeeByCountry(String country);

    @Query("SELECT e FROM Employee e WHERE e.city =:city " )
    public List<Employee> getEmployeeByCity(@Param("city") String city);

    @Query("SELECT e FROM Employee e")
    public List<Employee> getAllEmployees();




}
