package com.globati.service_beans.guest;

import com.globati.dbmodel.Employee;

public class EmployeeAndItems {

    Employee employee;

    public EmployeeAndItems(Employee employee) {
        this.employee = employee;
    }

    String apiKey;
    boolean facebookProfileCreated = false;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public boolean isFacebookProfileCreated() {
        return facebookProfileCreated;
    }

    public void setFacebookProfileCreated(boolean facebookProfileCreated) {
        this.facebookProfileCreated = facebookProfileCreated;
    }
}
