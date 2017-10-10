package com.globati.service_beans.guest;

import com.globati.dbmodel.Deal;
import com.globati.dbmodel.Employee;
import com.globati.HelpObjects.ApiKey;

import java.util.List;

public class EmployeeAndItems {

    Employee employee;

    public EmployeeAndItems(Employee employee) {
        this.employee = employee;
        this.nearByDeals = null;
    }

    @Override
    public String toString() {
        return "EmployeeAndItems{" +
                "employee=" + employee +
                ", nearByDeals=" + nearByDeals +
                ", apiKey=" + apiKey +
                '}';
    }

    List<Deal> nearByDeals;
    String apiKey;

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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
