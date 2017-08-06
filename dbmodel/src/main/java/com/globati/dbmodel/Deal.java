package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by daniel on 12/20/16.
 */
@Entity
@Table(name = "deal")
public class Deal extends BusinessEntity {


    @Column(length = 100, name = "billingstreet")
    String _billingStreet;
    @Column(length = 100, name = "billingcity")
    String _billingCity;
    @Column(length = 100, name = "billingcountry")
    String _billingCountry;
    @Column(length = 100, name = "billingregion")
    String _billingRegion;
    @Column(length = 100, name = "dealtype")
    String _dealtype;
    @Column(length = 400, name = "website")
    String _website;
    @Column(length = 100, name = "plan")
    String _plan;
    @Column(length = 200, name = "email")
    String _email;
    @Column(length = 100, name = "transactionid")
    String _transactionId;
    @Column(name = "cost")
    double _cost;
    @Column(name = "datemade")
    private Date _datemade;
    @Column(name = "nonrecruiterdistance")
    double _nonRecruiterDistance;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    Employee _employee;

    public Deal() {
    }

    public Deal(
            String image, String image2, String image3, String title, String description, String location,
            double targetLat, double targetLong, Employee employee, String country, String street, String city,
            String dealtype, String website, String email, String plan, double cost, String transactionId,
            String _billingStreet, String _billingCity, String _billingRegion, String _billingCountry) {

        this._image = image;
        this._image2 = image2;
        this._image3 = image3;
        this._title = title;
        this._description = description;
        this._location = location;
        this._targetLat = targetLat;
        this._targetLong = targetLong;
        this._employee = employee;
        this._country = country;
        this._street = street;
        this._city = city;
        this._dealtype = dealtype;
        this._website = website;
        this._datemade = new Date();
        this._email = email;
        this._cost = cost;
        this._plan = plan;
        this._transactionId = transactionId;
        this._active = true;
        this._billingCity = _billingCity;
        this._billingCountry = _billingCountry;
        this._billingStreet = _billingStreet;
        this._billingRegion = _billingRegion;
    }


    public String get_dealtype() {
        return _dealtype;
    }

    public String get_website() {
        return _website;
    }

    public String get_plan() {
        return _plan;
    }

    public String get_email() {
        return _email;
    }

    public String get_transactionId() {
        return _transactionId;
    }

    public double get_cost() {
        return _cost;
    }

    public Date get_datemade() {
        return _datemade;
    }

    public Employee get_employee() {
        return _employee;
    }

    public void set_dealtype(String _dealtype) {
        this._dealtype = _dealtype;
    }

    public void set_website(String _website) {
        this._website = _website;
    }

    public void set_plan(String _plan) {
        this._plan = _plan;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public void set_transactionId(String _transactionId) {
        this._transactionId = _transactionId;
    }

    public void set_cost(double _cost) {
        this._cost = _cost;
    }

    public void set_datemade(Date _datemade) {
        this._datemade = _datemade;
    }

    public void set_employee(Employee _employee) {
        this._employee = _employee;
    }

    public double get_nonRecruiterDistance() {
        return _nonRecruiterDistance;
    }

    public void set_nonRecruiterDistance(double _nonRecruiterDistance) {
        this._nonRecruiterDistance = _nonRecruiterDistance;
    }

    public String get_billingStreet() {
        return _billingStreet;
    }

    public void set_billingStreet(String _billingStreet) {
        this._billingStreet = _billingStreet;
    }

    public String get_billingCity() {
        return _billingCity;
    }

    public void set_billingCity(String _billingCity) {
        this._billingCity = _billingCity;
    }

    public String get_billingCountry() {
        return _billingCountry;
    }

    public void set_billingCountry(String _billingCountry) {
        this._billingCountry = _billingCountry;
    }

    public String get_billingRegion() {
        return _billingRegion;
    }

    public void set_billingRegion(String _billingRegion) {
        this._billingRegion = _billingRegion;
    }


}

