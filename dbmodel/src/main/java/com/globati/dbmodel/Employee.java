package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by daniel on 12/20/16.
 *
 * It is possibly a problem with the facebookid. It should be unique, but it also has to be nullable which hibernate does not allow.
 * As of now, I think it is unlikely that a duplicate facebookid will be generated.
 *
 */
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    @Column(length=100, name="firstname")
    private String _firstName;
    @Column(name="image")
    private String _image;
    @Column(name="image2")
    private String _image2;
    @Column(name="image3")
    private String _image3;
    @Column(unique = true, length=300, name="email", nullable = true)
    private String _email;
    @Column(length=100, name="paypalemail")
    private String _paypalEmail;
    @Column(length=3000, name="about")
    private String _about;
    @Column(length=3000, name = "welcomemail")
    private String _welcomeMail;
    @Column(length = 3000, name="recruitmentmail")
    private String _recruitmentMail;
    @Column(length=1000, name="instagramuser")
    private String _instagramUser;
    @Column(length=100, name="instagramuserid")
    private String _instagramUserId;
    @Column(length=100, name="instagramusertoken")
    private String _instagramUserToken;
    @Column(name="proplat")
    private double _propLat;
    @Column(name="proplong")
    private double _propLong;
    @Column(length=100, name="street")
    private String _street;
    @Column(length=100, name="city")
    private String _city;
    @Column(length=100, name="country")
    private String _country;
    @Column(length=1000, name="display")
    private String _display;
    @Column(name="addamount")
    private double _addAmount;
    @Column(name="add2month")
    private double _add2month;
    @Column(name="add3month")
    private double _add3month;
    @Column(unique = true, name="globatiusername")
    private String _globatiUsername;
    @Column(name="active")
    private boolean _active;
    @Column(name="facebookprofile")
    private boolean _facebookProfile=false;
    @Column(name="visitcounter")
    private Integer _visitCounter;


    @OneToMany(mappedBy = "_employee", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Deal> _deals;

    @OneToMany(mappedBy = "_employee", fetch = FetchType.LAZY)
    @JsonManagedReference //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    List<Recommendation> _recommendations;

    @OneToMany(mappedBy = "_employee", fetch = FetchType.LAZY)
    @JsonManagedReference //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    List<Event> _events;

    public Employee(){}

    public Employee(String firstName, String email, String userName, double locLat, double locLong, String image, String street, String city, String country) {
        this._firstName = firstName;
        this._email = email;
        this._globatiUsername = userName;
        this._propLat = locLat;
        this._propLong = locLong;
        this._image = image;
        this._street = street;
        this._city = city;
        this._country = country;
        this._active = true;
        this._addAmount = 10;
        this._add2month = 10;
        this._add3month = 10;
        this._about=""; //set these to empty strings, to avoid strangeness related to null on client side.
        this._display="";
        this._facebookProfile = false;
        this._visitCounter=0;
    }

    //Used for a facebook login/create account
    public Employee(String name, String _email, String _username, String _image){
        this._firstName = name;
        this._email = _email;
        this._globatiUsername = _username;
        this._image = _image;
        this._addAmount = 10;
        this._add2month = 10;
        this._add3month = 10;
        this._facebookProfile = true;
        this._visitCounter=0;
    }


    public String get_firstName() {
        return _firstName;
    }

    public String get_image() {
        return _image;
    }

    public String get_email() {
        return _email;
    }

    public String get_paypalEmail() {
        return _paypalEmail;
    }

    public String get_about() {
        return _about;
    }

    public String get_welcomeMail() {
        return _welcomeMail;
    }

    public String get_recruitmentMail() {
        return _recruitmentMail;
    }

    public String get_instagramUser() {
        return _instagramUser;
    }

    public String get_instagramUserId() {
        return _instagramUserId;
    }

    public String get_instagramUserToken() {
        return _instagramUserToken;
    }

    public double get_propLat() {
        return _propLat;
    }

    public double get_propLong() {
        return _propLong;
    }

    public String get_street() {
        return _street;
    }

    public String get_city() {
        return _city;
    }

    public String get_country() {
        return _country;
    }

    public String get_display() {
        return _display;
    }

    public String get_globatiUsername() {
        return _globatiUsername;
    }

    public boolean is_active() {
        return _active;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public void set_image(String _image) {
        this._image = _image;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public void set_paypalEmail(String _paypalEmail) {
        this._paypalEmail = _paypalEmail;
    }

    public void set_about(String _about) {
        this._about = _about;
    }

    public void set_welcomeMail(String _welcomeMail) {
        this._welcomeMail = _welcomeMail;
    }

    public void set_recruitmentMail(String _recruitmentMail) {
        this._recruitmentMail = _recruitmentMail;
    }

    public void set_instagramUser(String _instagramUser) {
        this._instagramUser = _instagramUser;
    }

    public void set_instagramUserId(String _instagramUserId) {
        this._instagramUserId = _instagramUserId;
    }

    public void set_instagramUserToken(String _instagramUserToken) {
        this._instagramUserToken = _instagramUserToken;
    }

    public void set_propLat(double _propLat) {
        this._propLat = _propLat;
    }

    public void set_propLong(double _propLong) {
        this._propLong = _propLong;
    }

    public void set_street(String _street) {
        this._street = _street;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public void set_country(String _country) {
        this._country = _country;
    }

    public void set_display(String _display) {
        this._display = _display;
    }

    public void set_globatiUsername(String _globatiUsername) {
        this._globatiUsername = _globatiUsername;
    }

    public void set_active(boolean _active) {
        this._active = _active;
    }

    public double get_addAmount() {
        return _addAmount;
    }

    public void set_addAmount(double _addAmount) {
        this._addAmount = _addAmount;
    }

    public double get_add2month() {
        return _add2month;
    }

    public void set_add2month(double _add2month) {
        this._add2month = _add2month;
    }

    public double get_add3month() {
        return _add3month;
    }

    public void set_add3month(double _add3month) {
        this._add3month = _add3month;
    }

    public List<Deal> get_deals() {
        return _deals;
    }

    public void set_deals(List<Deal> _deals) {
        this._deals = _deals;
    }

    public List<Recommendation> get_recommendations() {
        return _recommendations;
    }

    public void set_recommendations(List<Recommendation> _recommendations) {
        this._recommendations = _recommendations;
    }

    public List<Event> get_events() {
        return _events;
    }

    public void set_events(List<Event> _events) {
        this._events = _events;
    }

    public boolean is_facebookProfile() {
        return _facebookProfile;
    }

    public void set_facebookProfile(boolean _facebookProfile) {
        this._facebookProfile = _facebookProfile;
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

    public Integer get_visitCounter() {
        return _visitCounter;
    }

    public void set_visitCounter(Integer _visitCounter) {
        this._visitCounter = _visitCounter;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "_firstName='" + _firstName + '\'' +
                ", _image='" + _image + '\'' +
                ", _image2='" + _image2 + '\'' +
                ", _image3='" + _image3 + '\'' +
                ", _email='" + _email + '\'' +
                ", _paypalEmail='" + _paypalEmail + '\'' +
                ", _about='" + _about + '\'' +
                ", _welcomeMail='" + _welcomeMail + '\'' +
                ", _recruitmentMail='" + _recruitmentMail + '\'' +
                ", _instagramUser='" + _instagramUser + '\'' +
                ", _instagramUserId='" + _instagramUserId + '\'' +
                ", _instagramUserToken='" + _instagramUserToken + '\'' +
                ", _propLat=" + _propLat +
                ", _propLong=" + _propLong +
                ", _street='" + _street + '\'' +
                ", _city='" + _city + '\'' +
                ", _country='" + _country + '\'' +
                ", _display='" + _display + '\'' +
                ", _addAmount=" + _addAmount +
                ", _add2month=" + _add2month +
                ", _add3month=" + _add3month +
                ", _globatiUsername='" + _globatiUsername + '\'' +
                ", _active=" + _active +
                ", _facebookProfile=" + _facebookProfile +
                ", _visitCounter=" + _visitCounter +
                ", _deals=" + _deals +
                ", _recommendations=" + _recommendations +
                ", _events=" + _events +
                '}';
    }




}


