package com.globati.service;

import com.globati.dbmodel.EmployeeInfo;
import com.globati.repository.EmployeeInfoRepsitory;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.PBKDF2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by daniel on 1/17/17.
 */

@Service
public class EmployeeInfoService {

    private static final Logger log = LogManager.getLogger(DealService.class);


    @Autowired
    EmployeeInfoRepsitory employeeInfoRepsitory;

    public EmployeeInfo createEmployeeInfo(Long id, String password) throws ServiceException {
        try{
            EmployeeInfo employeeInfo = new EmployeeInfo(id);
            EmployeeInfo ei = PBKDF2.hashEmployeePassword(employeeInfo, password);
            return employeeInfoRepsitory.save(ei);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not create employee info ", e);
        }
    }

    public EmployeeInfo createEmployeeInfoForFacebookLogin(Long id, String facebookid) throws ServiceException {
        try{
            EmployeeInfo employeeInfo = new EmployeeInfo(id, facebookid);
            return employeeInfoRepsitory.save(employeeInfo);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not create employee info ", e);
        }
    }

    public EmployeeInfo getEmployeeInfoByEmployeeId(Long id) throws ServiceException {
        try{
            return employeeInfoRepsitory.getBy_employeeId(id);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not get info by employee id", e);
        }
    }

    public EmployeeInfo getEmployeeInfoByToken(String token) throws ServiceException {
        try{
            return employeeInfoRepsitory.getBy_authToken(token);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not get employee info by token ", e);
        }
    }

    public EmployeeInfo updateEmployeeInfo(EmployeeInfo employeeInfo) throws ServiceException {
        try{
            return employeeInfoRepsitory.save(employeeInfo);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not update employee info at this time", e);
        }
    }

    public EmployeeInfo getEmployeeInfoByFacebookId(String facebookId) throws ServiceException {
        try{
            return employeeInfoRepsitory.getBy_facebookId(facebookId);

        }catch(Exception e){
            throw new ServiceException("Could not get employee info by facebookid: "+facebookId, e);
        }
    }
}
