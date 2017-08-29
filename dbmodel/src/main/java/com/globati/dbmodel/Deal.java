package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globati.enums.DealPlan;
import com.globati.enums.DealType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by daniel on 12/20/16.
 */
@Entity
@Table(name = "deal")
public class Deal extends BusinessEntity {


    @Column(length = 100, name = "billingstreet")
    String billingStreet;
    @Column(length = 100, name = "billingcity")
    String billingCity;
    @Column(length = 100, name = "billingcountry")
    String billingCountry;
    @Column(length = 100, name = "billingregion")
    String billingRegion;
    @Column(length = 100, name = "dealtype")
    @Enumerated(EnumType.STRING)
    DealType dealtype;
    @Column(length = 400, name = "website")
    String website;
    @Column(length = 100, name = "plan")
    @Enumerated(EnumType.STRING)
    DealPlan plan;
    @Column(length = 200, name = "email")
    String email;
    @Column(length = 100, name = "transactionid")
    String transactionId;
    @Column(name = "cost")
    double cost;
    @Column(name = "datemade")
    private Date datemade;
    @Column(name = "nonrecruiterdistance")
    double nonRecruiterDistance;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    Employee employee;

    public Deal() {
    }

    public Deal(
            String image, String image2, String image3, String title, String description, String location,
            double targetLat, double targetLong, Employee employee, String country, String street, String city,
            DealType dealtype, String website, String email, DealPlan plan, double cost, String transactionId,
            String billingStreet, String billingCity, String billingRegion, String billingCountry) {

        this.image = image;
        this.image2 = image2;
        this.image3 = image3;
        this.title = title;
        this.description = description;
        this.location = location;
        this.targetLat = targetLat;
        this.targetLong = targetLong;
        this.employee = employee;
        this.country = country;
        this.street = street;
        this.city = city;
        this.dealtype = dealtype;
        this.website = website;
        this.datemade = new Date();
        this.email = email;
        this.cost = cost;
        this.plan = plan;
        this.transactionId = transactionId;
        this.active = true;
        this.billingCity = billingCity;
        this.billingCountry = billingCountry;
        this.billingStreet = billingStreet;
        this.billingRegion = billingRegion;
    }


    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingRegion() {
        return billingRegion;
    }

    public void setBillingRegion(String billingRegion) {
        this.billingRegion = billingRegion;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDatemade() {
        return datemade;
    }

    public void setDatemade(Date datemade) {
        this.datemade = datemade;
    }

    public double getNonRecruiterDistance() {
        return nonRecruiterDistance;
    }

    public void setNonRecruiterDistance(double nonRecruiterDistance) {
        this.nonRecruiterDistance = nonRecruiterDistance;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public DealType getDealtype() {
        return dealtype;
    }

    public void setDealtype(DealType dealtype) {
        this.dealtype = dealtype;
    }

    public DealPlan getPlan() {
        return plan;
    }

    public void setPlan(DealPlan plan) {
        this.plan = plan;
    }


}

