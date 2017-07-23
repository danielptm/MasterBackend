package com.globati.webmodel;

/**
 * Created by daniel on 12/24/16.
 */
public class Employee {

    //String name, String lastName, String email, String username,
    // String password, double latvalue, double longvalue

    String _firstName;
    String _lastName;
    String _email;
    String _globatiUsername;
    double _propLat;
    double _propLong;
    String _globatiPassword;

    public Employee(){}

    public Employee(String _firstName, String _lastName, String _email, String _globatiUsername, double _propLat, double _propLong, String _globatiPassword) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._email = _email;
        this._globatiUsername = _globatiUsername;
        this._propLat = _propLat;
        this._propLong = _propLong;
        this._globatiPassword = _globatiPassword;
    }

    public String get_firstName() {
        return _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public String get_email() {
        return _email;
    }

    public String get_globatiUsername() {
        return _globatiUsername;
    }

    public double get_propLat() {
        return _propLat;
    }

    public double get_propLong() {
        return _propLong;
    }

    public String get_globatiPassword() {
        return _globatiPassword;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public void set_globatiUsername(String _globatiUsername) {
        this._globatiUsername = _globatiUsername;
    }

    public void set_propLat(double _propLat) {
        this._propLat = _propLat;
    }

    public void set_propLong(double _propLong) {
        this._propLong = _propLong;
    }

    public void set_globatiPassword(String _globatiPassword) {
        this._globatiPassword = _globatiPassword;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "_firstName='" + _firstName + '\'' +
                ", _lastName='" + _lastName + '\'' +
                ", _email='" + _email + '\'' +
                ", _globatiUsername='" + _globatiUsername + '\'' +
                ", _propLat=" + _propLat +
                ", _propLong=" + _propLong +
                ", _globatiPassword='" + _globatiPassword + '\'' +
                '}';
    }
}
