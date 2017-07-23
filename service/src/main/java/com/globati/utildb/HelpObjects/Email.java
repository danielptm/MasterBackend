package com.globati.utildb.HelpObjects;

import com.globati.dbmodel.Employee;

import java.util.List;

/**
 * Created by daniel on 12/11/16.
 * An Email object, this class will probably be moved to the application level
 * of the new Spring project
 *
 */
public class Email {

    Employee employee;
    private List<String> emails;

    public Email(Employee employee, List<String> emails) {
        this.employee = employee;
        this.emails = emails;
    }

    public Email(){}

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
