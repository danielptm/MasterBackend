package com.globati.utildb.HelpObjects;

/**
 * Created by daniel on 1/19/17.
 */
public class MassPayEntry {

    String paypalEmail;
    double cost;
    String currency;
    Long employeeId;
    String note;

    public MassPayEntry(String paypalEmail, String cost, String currency, String employeeId, String note){
        this.paypalEmail = paypalEmail;
        this.cost = Double.parseDouble(cost);
        this.currency = currency;
        this.employeeId = Long.parseLong(employeeId);
        this.note = note;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public Double getCost() {
        return cost;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "MassPayEntry{" +
                "paypalEmail='" + paypalEmail + '\'' +
                ", cost='" + cost + '\'' +
                ", currency='" + currency + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
