package com.globati.repository;

import com.globati.dbmodel.Employee;
import com.globati.dbmodel.EmployeeInfo;
import com.globati.enums.Verified;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by daniel on 1/17/17.
 */
public interface EmployeeInfoRepsitory extends CrudRepository<EmployeeInfo, Long> {

    public EmployeeInfo getBy_employeeId(Long id);

    public EmployeeInfo getBy_authToken(String token);

    public EmployeeInfo getBy_facebookId(String id);

    public List<EmployeeInfo> getBy_verified(Verified verified);


}
