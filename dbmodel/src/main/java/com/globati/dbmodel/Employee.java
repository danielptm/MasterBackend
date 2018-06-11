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
    private String firstName;
    @Column(name="image")
    private String image;
    @Column(name="image2")
    private String image2;
    @Column(name="image3")
    private String image3;
    @Column(length=300, name="email", nullable = true)
    private String email;
    @Column(length=100, name="paypalemail")
    private String paypalEmail;
    @Column(length=3000, name="about")
    private String about;
    @Column(length=3000, name = "welcomemail")
    private String welcomeMail;
    @Column(length = 3000, name="recruitmentmail")
    private String recruitmentMail;
    @Column(length=1000, name="instagramuser")
    private String instagramUser;
    @Column(length=100, name="instagramuserid")
    private String instagramUserId;
    @Column(length=100, name="instagramusertoken")
    private String instagramUserToken;
    @Column(name="proplat")
    private double propLat;
    @Column(name="proplong")
    private double propLong;
    @Column(length=100, name="street")
    private String street;
    @Column(length=100, name="city")
    private String city;
    @Column(length=100, name="country")
    private String country;
    @Column(length=1000, name="display")
    private String display;
    @Column(name="addamount")
    private double addAmount;
    @Column(name="add2month")
    private double add2month;
    @Column(name="add3month")
    private double add3month;
    @Column(unique = true, name="globatiusername")
    private String globatiUsername;
    @Column(name="active")
    private boolean active;
    @Column(name="facebookprofile")
    private boolean facebookProfile=false;
    @Column(name="visitcounter")
    private Integer visitCounter;

    @Column(name="mobilevisitcounter", columnDefinition = "int default 0")
    private Integer mobileVisitCounter;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Deal> deals;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonManagedReference //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    List<Recommendation> recommendations;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonManagedReference //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    List<Event> events;



    public Employee(){}

    public Employee(String firstName, String email, String userName, double locLat, double locLong, String image, String street, String city, String country) {
        this.firstName = firstName;
        this.email = email;
        this.globatiUsername = userName;
        this.propLat = locLat;
        this.propLong = locLong;
        this.image = image;
        this.street = street;
        this.city = city;
        this.country = country;
        this.active = true;
        this.addAmount = 10;
        this.add2month = 10;
        this.add3month = 10;
        this.about=""; //set these to empty strings, to avoid strangeness related to null on client side.
        this.display="";
        this.facebookProfile = false;
        this.visitCounter=0;
        this.paypalEmail = email;
    }

    //Used for a facebook login/create account
    public Employee(String name, String _email, String username, String image){
        this.firstName = name;
        this.email = _email;
        this.globatiUsername = username;
        this.image = image;
        this.addAmount = 10;
        this.add2month = 10;
        this.add3month = 10;
        this.facebookProfile = true;
        this.visitCounter=0;
        this.paypalEmail = _email;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getWelcomeMail() {
        return welcomeMail;
    }

    public void setWelcomeMail(String welcomeMail) {
        this.welcomeMail = welcomeMail;
    }

    public String getRecruitmentMail() {
        return recruitmentMail;
    }

    public void setRecruitmentMail(String recruitmentMail) {
        this.recruitmentMail = recruitmentMail;
    }

    public String getInstagramUser() {
        return instagramUser;
    }

    public void setInstagramUser(String instagramUser) {
        this.instagramUser = instagramUser;
    }

    public String getInstagramUserId() {
        return instagramUserId;
    }

    public void setInstagramUserId(String instagramUserId) {
        this.instagramUserId = instagramUserId;
    }

    public String getInstagramUserToken() {
        return instagramUserToken;
    }

    public void setInstagramUserToken(String instagramUserToken) {
        this.instagramUserToken = instagramUserToken;
    }

    public double getPropLat() {
        return propLat;
    }

    public void setPropLat(double propLat) {
        this.propLat = propLat;
    }

    public double getPropLong() {
        return propLong;
    }

    public void setPropLong(double propLong) {
        this.propLong = propLong;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public double getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(double addAmount) {
        this.addAmount = addAmount;
    }

    public double getAdd2month() {
        return add2month;
    }

    public void setAdd2month(double add2month) {
        this.add2month = add2month;
    }

    public double getAdd3month() {
        return add3month;
    }

    public void setAdd3month(double add3month) {
        this.add3month = add3month;
    }

    public String getGlobatiUsername() {
        return globatiUsername;
    }

    public void setGlobatiUsername(String globatiUsername) {
        this.globatiUsername = globatiUsername;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isFacebookProfile() {
        return facebookProfile;
    }

    public void setFacebookProfile(boolean facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public Integer getVisitCounter() {
        return visitCounter;
    }

    public void setVisitCounter(Integer visitCounter) {
        this.visitCounter = visitCounter;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Integer getMobileVisitCounter() {
        return mobileVisitCounter;
    }

    public void setMobileVisitCounter(Integer mobileVisitCounter) {
        this.mobileVisitCounter = mobileVisitCounter;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", image='" + image + '\'' +
                ", image2='" + image2 + '\'' +
                ", image3='" + image3 + '\'' +
                ", email='" + email + '\'' +
                ", paypalEmail='" + paypalEmail + '\'' +
                ", about='" + about + '\'' +
                ", welcomeMail='" + welcomeMail + '\'' +
                ", recruitmentMail='" + recruitmentMail + '\'' +
                ", instagramUser='" + instagramUser + '\'' +
                ", instagramUserId='" + instagramUserId + '\'' +
                ", instagramUserToken='" + instagramUserToken + '\'' +
                ", propLat=" + propLat +
                ", propLong=" + propLong +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", display='" + display + '\'' +
                ", addAmount=" + addAmount +
                ", add2month=" + add2month +
                ", add3month=" + add3month +
                ", globatiUsername='" + globatiUsername + '\'' +
                ", active=" + active +
                ", facebookProfile=" + facebookProfile +
                ", visitCounter=" + visitCounter +
                ", deals=" + deals +
                ", recommendations=" + recommendations +
                ", _events=" + events +
                '}';
    }

}


