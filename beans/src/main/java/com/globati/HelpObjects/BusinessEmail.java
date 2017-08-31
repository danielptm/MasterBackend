package com.globati.HelpObjects;

/**
 * Created by daniel on 1/21/17.
 */
public class BusinessEmail {

    String businessName;
    String businessEmail;
    Long employeeId;

    public BusinessEmail(String businessName, String businessEmail, Long employeeId){
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.employeeId = employeeId;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }


    @Override
    public String toString() {
        return "BusinessEmail{" +
                "businessName='" + businessName + '\'' +
                ", businessEmail='" + businessEmail + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }

}
