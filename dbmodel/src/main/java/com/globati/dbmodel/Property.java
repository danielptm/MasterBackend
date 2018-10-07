package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.globati.enums.Verified;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel on 12/20/16.
 *
 * It is possibly a problem with the facebookid. It should be unique, but it also has to be nullable which hibernate does not allow.
 * As of now, I think it is unlikely that a duplicate facebookid will be generated.
 *
 */
@Entity
@Table(name = "property")
public class Property extends BaseEntity {

    @Column(length=100, name="firstname")
    private String firstName;
    @Column(name="image")
    private String image;
    @Column(length=300, name="email", nullable = true)
    private String email;
    @Column(length=3000, name="about")
    private String about;
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
    @Column(unique = true, name="globatiusername")
    private String globatiUsername;
    @Column(name="visitcounter")
    private Integer visitCounter;
    @Column(name="bookingurl")
    private String bookingUrl;
    @Column(name="website")
    private String website;
    @Column(name="flyerlink", columnDefinition="VARCHAR(100) default 'https://s3.eu-central-1.amazonaws.com/globatiimages/splash/posters/poster.jpg'")
    private String flyerLink;
    @Column(name="mobilevisitcounter", columnDefinition = "int default 0")
    private Integer mobileVisitCounter;
    @Column(name="lastmobileupdate", columnDefinition="VARCHAR(100) default 'bookingurl'")
    private Date lastMobileUpdate;
    @Enumerated(EnumType.STRING)
    @Column(name="verified")
    private Verified verified;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    @JsonManagedReference //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    List<Recommendation> recommendations;

    public Property(){}

    public Property(String firstName, String email, String userName, double locLat, double locLong, String image, String street, String city, String country, Verified verified) {
        this.firstName = firstName;
        this.email = email;
        this.globatiUsername = userName;
        this.propLat = locLat;
        this.propLong = locLong;
        this.image = image;
        this.street = street;
        this.city = city;
        this.country = country;
        this.about=""; //set these to empty strings, to avoid strangeness related to null on client side.
        this.display="";
        this.visitCounter=0;
        this.verified = verified;
    }

    //Used for a facebook login/create account
    public Property(String name, String _email, String username, String image){
        this.firstName = name;
        this.email = _email;
        this.globatiUsername = username;
        this.image = image;
        this.visitCounter=0;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    public String getGlobatiUsername() {
        return globatiUsername;
    }

    public void setGlobatiUsername(String globatiUsername) {
        this.globatiUsername = globatiUsername;
    }

    public Integer getVisitCounter() {
        return visitCounter;
    }

    public void setVisitCounter(Integer visitCounter) {
        this.visitCounter = visitCounter;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public Integer getMobileVisitCounter() {
        return mobileVisitCounter;
    }

    public void setMobileVisitCounter(Integer mobileVisitCounter) {
        this.mobileVisitCounter = mobileVisitCounter;
    }

    public String getFlyerLink() {
        return flyerLink;
    }

    public void setFlyerLink(String flyerLink) {
        this.flyerLink = flyerLink;
    }

    public String getBookingUrl() {
        return bookingUrl;
    }

    public void setBookingUrl(String bookingUrl) {
        this.bookingUrl = bookingUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                ", about='" + about + '\'' +
                ", propLat=" + propLat +
                ", propLong=" + propLong +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", display='" + display + '\'' +
                ", globatiUsername='" + globatiUsername + '\'' +
                ", visitCounter=" + visitCounter +
                ", recommendations=" + recommendations +
                '}';
    }
}


