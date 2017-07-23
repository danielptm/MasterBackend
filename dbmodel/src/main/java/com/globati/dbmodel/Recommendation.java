package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by daniel on 12/20/16.
 */
@Entity
@Table(name = "recommendation")
public class Recommendation extends BusinessEntity {

    @ManyToOne
    @JoinColumn(name = "employeeid")
    @JsonBackReference
    //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    private Employee _employee;


    public Recommendation() {
    }

    public Recommendation(Employee employee, String _title, String _description, double _targetLat, double _targetLong, String _street, String _city, String _country, String _image, String _image2, String _image3) {
        this._employee = employee;
        this._title = _title;
        this._description = _description;
        this._targetLat = _targetLat;
        this._targetLong = _targetLong;
        this._street = _street;
        this._city = _city;
        this._country = _country;
        this._image = _image;
        this._active = true;
        this._image2 = _image2;
        this._image3 = _image3;
    }


    public Employee get_employee() {
        return _employee;
    }

    public void set_employee(Employee _employee) {
        this._employee = _employee;
    }


}



