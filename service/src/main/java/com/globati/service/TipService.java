package com.globati.service;

import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Tip;
import com.globati.repository.TipRepository;
import com.globati.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TipService {

    private static final Logger log = LogManager.getLogger(TipService.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    TipRepository tipRepository;

    public Tip createTip(Long id, String transactionId, Double tipPayment, String email){
        try {
            Employee employee = employeeService.getEmployeeById(id);
            Tip tip = new Tip(employee, tipPayment, transactionId, email);
            return tipRepository.save(tip);
        } catch (ServiceException e) {
            log.warn("** Globati service exception: Could not create tip.");
            e.printStackTrace();
        }
        return null;
    }
}
