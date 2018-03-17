package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tip")
public class Tip extends BaseEntity{

    @Column(name = "tipamount")
    Double tipAmount;
    @Column(name = "transactionid")
    String transactionId;
    @Column(name="datecreated")
    Date date;
    @Column(name="email")
    String email;


    @ManyToOne
    @JoinColumn(name="employeeid")
    @JsonBackReference
    Employee employee;

    public Tip(){}

    public Tip(Employee employee, Double tipAmount, String transactionId, String email){
        this.tipAmount = tipAmount;
        this.transactionId = transactionId;
        this.employee = employee;
        this.email = email;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(Double tipAmount) {
        this.tipAmount = tipAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tip{");
        sb.append("tipAmount=").append(tipAmount);
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", employee=").append(employee);
        sb.append('}');
        return sb.toString();
    }
}
