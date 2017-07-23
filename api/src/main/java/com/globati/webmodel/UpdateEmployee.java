package com.globati.webmodel;

import javax.ws.rs.QueryParam;

/**
 * Created by daniel on 4/15/17.
 * <p>
 * Data that can be updated by employee on the clientside
 */
public class UpdateEmployee {

    private Long _employeeId;
    private String _email;
    private String _username;
    private String _instagramUserName;
    private String _instagramToken;
    private String _instagramUserId;
    private String _paypal;
    private Double _day30;
    private Double _day60;
    private Double _day90;
    private Double _propLat;
    private Double _propLong;
    private String _street;
    private String _city;
    private String _country;
    private String _about;
    private String _display;
    private String _welcomeMail;
    private String _recruitmentMail;
    private String _image;
    private String _image2;
    private String _image3;

    public UpdateEmployee() {}

    public String get_welcomeMail() {
        return _welcomeMail;
    }

    public void set_welcomeMail(String _welcomeMail) {
        this._welcomeMail = _welcomeMail;
    }

    public String get_recruitmentMail() {
        return _recruitmentMail;
    }

    public void set_recruitmentMail(String _recruitmentMail) {
        this._recruitmentMail = _recruitmentMail;
    }

    public String get_about() {
        return _about;
    }

    public void set_about(String _about) {
        this._about = _about;
    }

    public String get_display() {
        return _display;
    }

    public void set_display(String _display) {
        this._display = _display;
    }

    public UpdateEmployee(Long employeeId) {
        this._employeeId = employeeId;
    }

    public Long get_employeeId() {
        return _employeeId;
    }

    public void set_employeeId(Long _employeeId) {
        this._employeeId = _employeeId;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_instagramUserName() {
        return _instagramUserName;
    }

    public void set_instagramUserName(String _instagramUserName) {
        this._instagramUserName = _instagramUserName;
    }

    public String get_instagramToken() {
        return _instagramToken;
    }

    public void set_instagramToken(String _instagramToken) {
        this._instagramToken = _instagramToken;
    }

    public String get_instagramUserId() {
        return _instagramUserId;
    }

    public void set_instagramUserId(String _instagramUserId) {
        this._instagramUserId = _instagramUserId;
    }

    public String get_paypal() {
        return _paypal;
    }

    public void set_paypal(String _paypal) {
        this._paypal = _paypal;
    }

    public Double get_day30() {
        return _day30;
    }

    public void set_day30(Double _day30) {
        this._day30 = _day30;
    }

    public Double get_day60() {
        return _day60;
    }

    public void set_day60(Double _day60) {
        this._day60 = _day60;
    }

    public Double get_day90() {
        return _day90;
    }

    public void set_day90(Double _day90) {
        this._day90 = _day90;
    }

    public Double get_propLat() {
        return _propLat;
    }

    public void set_propLat(Double _propLat) {
        this._propLat = _propLat;
    }

    public Double get_propLong() {
        return _propLong;
    }

    public void set_propLong(Double _propLong) {
        this._propLong = _propLong;
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
        return "UpdateEmployee{" +
                "_employeeId=" + _employeeId +
                ", _email='" + _email + '\'' +
                ", _username='" + _username + '\'' +
                ", _instagramUserName='" + _instagramUserName + '\'' +
                ", _instagramToken='" + _instagramToken + '\'' +
                ", _instagramUserId='" + _instagramUserId + '\'' +
                ", _paypal='" + _paypal + '\'' +
                ", _day30=" + _day30 +
                ", _day60=" + _day60 +
                ", _day90=" + _day90 +
                ", _propLat=" + _propLat +
                ", _propLong=" + _propLong +
                ", _street='" + _street + '\'' +
                ", _city='" + _city + '\'' +
                ", _country='" + _country + '\'' +
                ", _about='" + _about + '\'' +
                ", _display='" + _display + '\'' +
                ", _welcomeMail='" + _welcomeMail + '\'' +
                ", _recruitmentMail='" + _recruitmentMail + '\'' +
                ", _image='" + _image + '\'' +
                ", _image2='" + _image2 + '\'' +
                ", _image3='" + _image3 + '\'' +
                '}';
    }

}
