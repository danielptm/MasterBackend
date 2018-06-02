package com.globati.service;

import com.globati.dbmodel.Employee;
import com.globati.dbmodel.HelpRecommendation;
import com.globati.enums.HelpRecommendationStatus;
import com.globati.repository.HelpRecommendationRepository;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HelpRecommendationService {

    private static final Logger log = LogManager.getLogger(HelpRecommendationService.class);


    @Autowired
    HelpRecommendationRepository helpRecommendationRepository;

    @Autowired
    EmployeeService employeeService;

    HelpRecommendationService(){}

    public HelpRecommendation createHelpRecommendation(Long employeeId, String title, String description, Date date) throws ServiceException {
        HelpRecommendation helpRecommendation = null;
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            helpRecommendation = new HelpRecommendation(title, description, date, HelpRecommendationStatus.NOT_CREATED, employee);
            SendMail.sendCustomMailToGlobatiStaff("daniel@globati.com", "HelpRecommendation(s) have been created by "+employee.getGlobatiUsername());

        } catch (ServiceException e) {
            log.warn("** Globati service exception for method: createHelpRecommendation: title: "+title);
            e.printStackTrace();
            throw new ServiceException("Could not create HelpRecommendation at this time: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return helpRecommendationRepository.save(helpRecommendation);
    }
}
