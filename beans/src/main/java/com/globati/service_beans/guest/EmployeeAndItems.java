package com.globati.service_beans.guest;

import com.globati.dbmodel.Deal;
import com.globati.dbmodel.Employee;
import com.globati.HelpObjects.ApiKey;

import java.util.List;

public class EmployeeAndItems {

    Employee employee;
    List<Deal> nearByDeals;
    ApiKey apiKey;

    public EmployeeAndItems(Employee employee, List<Deal> nearByDeals){
        this.employee = employee;
        this.nearByDeals = nearByDeals;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Deal> getNearByDeals() {
        return nearByDeals;
    }

    public void setNearByDeals(List<Deal> nearByDeals) {
        this.nearByDeals = nearByDeals;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

}
