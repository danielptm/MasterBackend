package com.globati.service;

import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Tip;
import com.globati.repository.TipRepository;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * This class is untested
 */
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
            SendMail.sendThanksForTipingMail(email, "Thanks for tipping "+employee.getGlobatiUsername()+" Your transaction number is: "+transactionId+" If you have any questions, you can email "+employee.getGlobatiUsername()+" at "+employee.getEmail()+"<br><br> best wishes, <br><br> The globati team");
            return tipRepository.save(tip);
        } catch (ServiceException e) {
            log.warn("** Globati service exception: Could not create tip.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Tip> getTipsByEmployeeId(Long id) {
        List<Tip> tips = null;
        try {
            tips = tipRepository.getTipsByEmployeeId(id);
        }catch(Exception e){
            log.warn("** Globati service exception: Could not get tips by employee id:");
            e.printStackTrace();
        }
        return tips;
    }
}
