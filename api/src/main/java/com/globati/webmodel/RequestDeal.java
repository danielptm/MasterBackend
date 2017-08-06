package com.globati.webmodel;

/**
 * Created by daniel on 8/6/17.
 */
public class RequestDeal {

    Long _id;
    String _title;
    String _description;
    String _businessName;
    String _website;
    String _category;
    String _plan;
    Double _targetLat;
    Double _targetLong;
    String _street;
    String _city;
    String _country;
    String _nonce;
    String _email;
    String _billingStreet;
    String _billingCity;
    String _billingRegion;
    String _billingCountry;
    Double _cost;
    String _image1;
    String _image2;
    String _image3;

    RequestDeal(){}

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
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

    public String get_businessName() {
        return _businessName;
    }

    public void set_businessName(String _businessName) {
        this._businessName = _businessName;
    }

    public String get_website() {
        return _website;
    }

    public void set_website(String _website) {
        this._website = _website;
    }

    public String get_category() {
        return _category;
    }

    public void set_category(String _category) {
        this._category = _category;
    }

    public String get_plan() {
        return _plan;
    }

    public void set_plan(String _plan) {
        this._plan = _plan;
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

    public String get_nonce() {
        return _nonce;
    }

    public void set_nonce(String _nonce) {
        this._nonce = _nonce;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
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

    public String get_billingRegion() {
        return _billingRegion;
    }

    public void set_billingRegion(String _billingRegion) {
        this._billingRegion = _billingRegion;
    }

    public String get_billingCountry() {
        return _billingCountry;
    }

    public void set_billingCountry(String _billingCountry) {
        this._billingCountry = _billingCountry;
    }

    public Double get_cost() {
        return _cost;
    }

    public void set_cost(Double _cost) {
        this._cost = _cost;
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
        return "RequestDeal{" +
                "_id=" + _id +
                ", _title='" + _title + '\'' +
                ", _description='" + _description + '\'' +
                ", _businessName='" + _businessName + '\'' +
                ", _website='" + _website + '\'' +
                ", _category='" + _category + '\'' +
                ", _plan='" + _plan + '\'' +
                ", _targetLat=" + _targetLat +
                ", _targetLong=" + _targetLong +
                ", _street='" + _street + '\'' +
                ", _city='" + _city + '\'' +
                ", _country='" + _country + '\'' +
                ", _nonce='" + _nonce + '\'' +
                ", _email='" + _email + '\'' +
                ", _billingStreet='" + _billingStreet + '\'' +
                ", _billingCity='" + _billingCity + '\'' +
                ", _billingRegion='" + _billingRegion + '\'' +
                ", _billingCountry='" + _billingCountry + '\'' +
                ", _cost=" + _cost +
                ", _image1='" + _image1 + '\'' +
                ", _image2='" + _image2 + '\'' +
                ", _image3='" + _image3 + '\'' +
                '}';
    }

}
