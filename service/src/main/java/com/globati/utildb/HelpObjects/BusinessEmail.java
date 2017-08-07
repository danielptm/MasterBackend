package com.globati.utildb.HelpObjects;

import com.globati.dbmodel.Employee;

/**
 * Created by daniel on 1/21/17.
 */
public class BusinessEmail {

    String businessName;
    String businessEmail;
    Employee employee;

    public BusinessEmail(String businessName, String businessEmail, Employee employee){
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.employee = employee;
    }

    public BusinessEmail(){}

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "BusinessEmail{" +
                "businessName='" + businessName + '\'' +
                ", businessEmail='" + businessEmail + '\'' +
                ", employee=" + employee.getGlobatiUsername() +
                '}';
    }
}
