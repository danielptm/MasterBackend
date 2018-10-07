package com.globati.service;

import com.globati.dbmodel.PropertyInfo;
import com.globati.repository.PropertyInfoRepsitory;
import com.globati.service.exceptions.ServiceException;
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
public class PropertyInfoService {

    private static final Logger log = LogManager.getLogger(PropertyInfoService.class);
    
    @Autowired
    PropertyInfoRepsitory employeeInfoRepsitory;


    public PropertyInfo createPropertyInfo(Long id, String password) throws ServiceException {
        log.info("createEmployeeInfo(): employeeId: "+id);
        try{
            PropertyInfo propertyInfo = new PropertyInfo(id);
            PropertyInfo ei = PBKDF2.hashPropertyPassword(propertyInfo, password);
            return employeeInfoRepsitory.save(ei);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: createEmployeeInfo(): "+id);
            throw new ServiceException("Could not create employee info ", e);
        }
    }

    public PropertyInfo createPropertyInfoForFacebookLogin(Long id, String facebookid) throws ServiceException {
        log.info("createEmployeeInfoForFacebookLogin(): employeeId: "+id);
        try{
            PropertyInfo propertyInfo = new PropertyInfo(id, facebookid);
            return employeeInfoRepsitory.save(propertyInfo);
        }catch(Exception e){
            log.warn(e.toString());
            throw new ServiceException("Could not create employee info ", e);
        }
    }

    public PropertyInfo getPropertyInfoByPropertyId(Long id) throws ServiceException {
        log.info("getPropertyInfoByEmployeeId(): employeeId: "+id);
        try{
            return employeeInfoRepsitory.getByPropertyId(id);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getEmployeeInfoByEmployeeid(): id: "+id);
            throw new ServiceException("Could not get info by employee id", e);
        }
    }

    public PropertyInfo getPropertyInfoByToken(String token) throws ServiceException {
        log.info("getPropertyInfoByToken(): token: "+token);
        try{
            return employeeInfoRepsitory.getByAuthToken(token);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getEmployeeInfoByToken(): "+token );
            throw new ServiceException("Could not get employee info by token ", e);
        }
    }

    public PropertyInfo updatePropertyInfo(PropertyInfo propertyInfo) throws ServiceException {
        log.info("updateEmployeeInfo(): employeeInfoId: "+ propertyInfo.getId());
        try{
            return employeeInfoRepsitory.save(propertyInfo);
        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: updateEmployeeInfo(): employeeInfoId: "+ propertyInfo.getId());
            throw new ServiceException("Could not update employee info at this time", e);
        }
    }

    public PropertyInfo getPropertyInfoByFacebookId(String facebookId) throws ServiceException {
        log.info("getEmployeeInfoByFacebookId(): facebookId: "+facebookId);
        try{
            return employeeInfoRepsitory.getByFacebookId(facebookId);

        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getEmployeeInfoByFacebookId(): facebookId: "+facebookId);
            throw new ServiceException("Could not get employee info by facebookid: "+facebookId, e);
        }
    }

//    public List<PropertyInfo> getAllEmployeesByVerified(Verified verified) throws ServiceException {
//        log.info("getAllEmployeesByVerified(): verified: "+verified.toString());
//        try{
//            return employeeInfoRepsitory.getBy_verified(verified);
//        }catch(Exception e){
//            log.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: getAllEmployeesByVerified(): verified: "+verified.toString());
//            throw new ServiceException("Could not get employeeinfo by verified", e);
//        }
//    }


    public List<PropertyInfo> getAllPropertyInfos(){
        return employeeInfoRepsitory.getAllPropertyInfos();
    }

}
