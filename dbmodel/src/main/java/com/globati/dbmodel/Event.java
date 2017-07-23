package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by daniel on 12/20/16.
 */
@Entity
@Table(name="event")
public class Event extends BusinessEntity{

    @Column(name="date")
    private Date _date;
    @Column(length=100, name="repeating") //this has to be repeating, i think repeat is a reserved word in mysql, and while works on derby does not for mysql
    private String _repeat;
    @Column(name="publicvisible")
    private boolean _publicVisible;
    @Column(name="dateinactive")
    private Date _dateInactive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="employeeid")
    @JsonBackReference //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    Employee _employee;

    public Event(){}

    //Took away distance, because this value will vary depending on the receptionists location
    public Event(Employee _employee, Date _date, double _targetLat, double _targetLong, String _street, String _city, String _country, String title, String _description, String imageName1, String imageName2, String imageName3) {
        this._employee = _employee;
        this._date = _date;
        this._targetLat = _targetLat;
        this._targetLong = _targetLong;
        this._repeat = _repeat;
        this._street = _street;
        this._city = _city;
        this._country = _country;
        this._description = _description;
        this._active = true;
        this._title = title;
        this._image = imageName1;
        this._image2 = imageName2;
        this._image3 = imageName3;
    }

    public Date get_date() {
        return _date;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }

    public String get_repeat() {
        return _repeat;
    }

    public void set_repeat(String _repeat) {
        this._repeat = _repeat;
    }

    public boolean is_publicVisible() {
        return _publicVisible;
    }

    public void set_publicVisible(boolean _publicVisible) {
        this._publicVisible = _publicVisible;
    }

    public Date get_dateInactive() {
        return _dateInactive;
    }

    public void set_dateInactive(Date _dateInactive) {
        this._dateInactive = _dateInactive;
    }

    public Employee get_employee() {
        return _employee;
    }

    public void set_employee(Employee _employee) {
        this._employee = _employee;
    }


}
