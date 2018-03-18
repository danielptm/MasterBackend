package com.globati.deserialization_beans.response.employee;

import java.util.Date;

public class ResponseTip {
    Date date;
    Long id;
    Double tipAmount;
    String transactionId;
    String email;
    Boolean isPaid;

    public ResponseTip(Date date, Long id, Double tipAmount, String transactionId, String email, Boolean isPaid) {
        this.date = date;
        this.id = id;
        this.tipAmount = tipAmount;
        this.transactionId = transactionId;
        this.email = email;
        this.isPaid = isPaid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
