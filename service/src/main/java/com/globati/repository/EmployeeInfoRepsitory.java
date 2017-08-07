package com.globati.repository;

import com.globati.dbmodel.Employee;
import com.globati.dbmodel.EmployeeInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by daniel on 1/17/17.
 */
public interface EmployeeInfoRepsitory extends CrudRepository<EmployeeInfo, Long> {

    public EmployeeInfo getByEmployeeId(Long id);

    public EmployeeInfo getByAuthToken(String token);

    public EmployeeInfo getByFacebookId(String id);


}
