package com.globati.service;

import com.globati.dbmodel.EmployeeInfo;
import com.globati.enums.Verified;
import com.globati.repository.EmployeeInfoRepsitory;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.ImageHandler;
import com.globati.utildb.PBKDF2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by daniel on 1/17/17.
 */

@Service
public class EmployeeInfoService {

    private static final Logger log = LogManager.getLogger(EmployeeInfoService.class);


    @Autowired
    EmployeeInfoRepsitory employeeInfoRepsitory;


    public EmployeeInfo createEmployeeInfo(Long id, String password) throws ServiceException {
        log.info("createEmployeeInfo(): employeeId: "+id);
        try{
            EmployeeInfo employeeInfo = new EmployeeInfo(id);
            EmployeeInfo ei = PBKDF2.hashEmployeePassword(employeeInfo, password);
            ei.set_verified(Verified.NOT);
            return employeeInfoRepsitory.save(ei);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: createEmployeeInfo(): "+id);
            throw new ServiceException("Could not create employee info ", e);
        }
    }

    public EmployeeInfo createEmployeeInfoForFacebookLogin(Long id, String facebookid) throws ServiceException {
        log.info("createEmployeeInfoForFacebookLogin(): employeeId: "+id);
        try{
            EmployeeInfo employeeInfo = new EmployeeInfo(id, facebookid);
            employeeInfo.set_verified(Verified.NOT);
            return employeeInfoRepsitory.save(employeeInfo);
        }catch(Exception e){
            log.warn(e.toString());
            throw new ServiceException("Could not create employee info ", e);
        }
    }

    public EmployeeInfo getEmployeeInfoByEmployeeId(Long id) throws ServiceException {
        log.info("getEmployeeInfoByEmployeeId(): employeeId: "+id);
        try{
            return employeeInfoRepsitory.getByEmployeeId(id);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getEmployeeInfoByEmployeeid(): id: "+id);
            throw new ServiceException("Could not get info by employee id", e);
        }
    }

    public EmployeeInfo getEmployeeInfoByToken(String token) throws ServiceException {
        log.info("getEmployeeInfoByToken(): token: "+token);
        try{
            return employeeInfoRepsitory.getByAuthToken(token);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getEmployeeInfoByToken(): "+token );
            throw new ServiceException("Could not get employee info by token ", e);
        }
    }

    public EmployeeInfo updateEmployeeInfo(EmployeeInfo employeeInfo) throws ServiceException {
        log.info("updateEmployeeInfo(): employeeInfoId: "+employeeInfo.getId());
        try{
            return employeeInfoRepsitory.save(employeeInfo);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: updateEmployeeInfo(): employeeInfoId: "+employeeInfo.getId());
            throw new ServiceException("Could not update employee info at this time", e);
        }
    }

    public EmployeeInfo getEmployeeInfoByFacebookId(String facebookId) throws ServiceException {
        log.info("getEmployeeInfoByFacebookId(): facebookId: "+facebookId);
        try{
            return employeeInfoRepsitory.getByFacebookId(facebookId);

        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getEmployeeInfoByFacebookId(): facebookId: "+facebookId);
            throw new ServiceException("Could not get employee info by facebookid: "+facebookId, e);
        }
    }

    public List<EmployeeInfo> getAllEmployeesByVerified(Verified verified) throws ServiceException {
        log.info("getAllEmployeesByVerified(): verified: "+verified.toString());
        try{
            return employeeInfoRepsitory.getBy_verified(verified);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getAllEmployeesByVerified(): verified: "+verified.toString());
            throw new ServiceException("Could not get employeeinfo by verified", e);
        }
    }




}
