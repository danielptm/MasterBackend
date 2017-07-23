package com.globati.webmodel;

/**
 * Created by daniel on 6/5/17.
 */
public class Recommendation {

    public Recommendation(){}



    Long _employeeId;
    String _title;
    String _description;
    Double _targetLat;
    Double _targetLong;
    String _street;
    String _city;
    String _country;
    String _image1;
    String _image2;
    String _image3;

    public Long get_employeeId() {
        return _employeeId;
    }

    public void set_employeeId(Long _employeeId) {
        this._employeeId = _employeeId;
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

    public String get_image1() {
        return _image1;
    }

    public void set_image1(String _image1) {
        this._image1 = _image1;
    }

    public String get_image2() {
        return _image2;
    }

    public void set_image2(String _image2) {
        this._image2 = _image2;
    }

    public String get_image3() {
        return _image3;
    }

    public void set_image3(String _image3) {
        this._image3 = _image3;
    }


    @Override
    public String toString() {
        return "Recommendation{" +
                "_employeeId=" + _employeeId +
                ", _title='" + _title + '\'' +
                ", _description='" + _description + '\'' +
                ", _targetLat=" + _targetLat +
                ", _targetLong=" + _targetLong +
                ", _street='" + _street + '\'' +
                ", _city='" + _city + '\'' +
                ", _country='" + _country + '\'' +
                ", _image1='" + _image1 + '\'' +
                ", _image2='" + _image2 + '\'' +
                ", _image3='" + _image3 + '\'' +
                '}';
    }



}
