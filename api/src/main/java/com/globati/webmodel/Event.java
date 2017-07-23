package com.globati.webmodel;

import java.util.Date;

/**
 * Created by daniel on 12/16/16.
 *
 * Email will probably be moved to the application level
 * of the new Spring project
 */
public class Event {

    private String _date;
    private String _title;
    private String _description;
    private Double _targetLat;
    private Double _targetLong;
    private String _street;
    private String _city;
    private String _country;
    private Long _employeeId;
    private String _imageName1;
    private String _imageName2;
    private String _imageName3;

    public Event() {}

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public Double get_targetLat() {
        return _targetLat;
    }

    public void set_targetLat(Double _targetLat) {
        this._targetLat = _targetLat;
    }

    public Double get_targetLong() {
        return _targetLong;
    }

    public void set_targetLong(Double _targetLong) {
        this._targetLong = _targetLong;
    }

    public String get_street() {
        return _street;
    }

    public void set_street(String _street) {
        this._street = _street;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_country() {
        return _country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }

    public Long get_employeeId() {
        return _employeeId;
    }

    public void set_employeeId(Long _employeeId) {
        this._employeeId = _employeeId;
    }

    public String get_imageName1() {
        return _imageName1;
    }

    public void set_imageName1(String _imageName1) {
        this._imageName1 = _imageName1;
    }

    public String get_imageName2() {
        return _imageName2;
    }

    public void set_imageName2(String _imageName2) {
        this._imageName2 = _imageName2;
    }

    public String get_imageName3() {
        return _imageName3;
    }

    public void set_imageName3(String _imageName3) {
        this._imageName3 = _imageName3;
    }

    @Override
    public String toString() {
        return "Event{" +
                "_date=" + _date +
                ", _title='" + _title + '\'' +
                ", _description='" + _description + '\'' +
                ", _targetLat='" + _targetLat + '\'' +
                ", _targetLong='" + _targetLong + '\'' +
                ", _street='" + _street + '\'' +
                ", _city='" + _city + '\'' +
                ", _country='" + _country + '\'' +
                ", _employeeId='" + _employeeId + '\'' +
                ", _imageName1='" + _imageName1 + '\'' +
                ", _imageName2='" + _imageName2 + '\'' +
                ", _imageName3='" + _imageName3 + '\'' +
                '}';
    }

}
