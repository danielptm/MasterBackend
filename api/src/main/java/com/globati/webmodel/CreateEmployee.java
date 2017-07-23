package com.globati.webmodel;

/**
 * Created by daniel on 7/16/17.
 */
public class CreateEmployee {
    String _firstName;
    String _email;
    String _username;
    String _password;
    Double _targetLat;
    Double _targetLong;
    String _street;
    String _city;
    String _country;
    String _image;

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }
//    }

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
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

    public String get_image() {
        return _image;
    }

    public void set_image(String _image) {
        this._image = _image;
    }


    @Override
    public String toString() {
        return "CreateEmployee{" +
                "_firstName='" + _firstName + '\'' +
                ", _email='" + _email + '\'' +
                ", _username='" + _username + '\'' +
                ", _password='" + _password + '\'' +
                ", _targetLat=" + _targetLat +
                ", _targetLong=" + _targetLong +
                ", _street='" + _street + '\'' +
                ", _city='" + _city + '\'' +
                ", _country='" + _country + '\'' +
                ", _image='" + _image + '\'' +
                '}';
    }

}

