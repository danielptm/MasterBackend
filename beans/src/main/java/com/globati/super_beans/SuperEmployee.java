package com.globati.super_beans;

/**
 * Created by daniel on 8/7/17.
 */
public abstract class SuperEmployee {

    Long id;
    String firstName;
    String image1;
    String email;
    String about;
    String welcomeMail;
    String recruitmentMail;
    String instagramUser;
    String instagramUserId;
    String instagramUserToken;
    Double propLat;
    Double propLong;
    String street;
    String city;
    String country;
    String display;
    String globatiUsername;
    boolean facebookProfile;

    public SuperEmployee(){}

    public SuperEmployee(Long id, String firstName, String image1, String email, String about, String welcomeMail, String recruitmentMail, String instagramUser, String instagramUserId, String instagramUserToken, Double propLat, Double propLong, String street, String city, String country, String display, String globatiUsername, boolean facebookProfile) {
        this.id = id;
        this.firstName = firstName;
        this.image1 = image1;
        this.email = email;
        this.about = about;
        this.welcomeMail = welcomeMail;
        this.recruitmentMail = recruitmentMail;
        this.instagramUser = instagramUser;
        this.instagramUserId = instagramUserId;
        this.instagramUserToken = instagramUserToken;
        this.propLat = propLat;
        this.propLong = propLong;
        this.street = street;
        this.city = city;
        this.country = country;
        this.display = display;
        this.globatiUsername = globatiUsername;
        this.facebookProfile = facebookProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
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

    public Double getPropLat() {
        return propLat;
    }

    public void setPropLat(Double propLat) {
        this.propLat = propLat;
    }

    public Double getPropLong() {
        return propLong;
    }

    public void setPropLong(Double propLong) {
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

    public boolean isFacebookProfile() {
        return facebookProfile;
    }

    public void setFacebookProfile(boolean facebookProfile) {
        this.facebookProfile = facebookProfile;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", image1='" + image1 + '\'' +
                ", email='" + email + '\'' +
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
                ", globatiUsername='" + globatiUsername + '\'' +
                ", facebookProfile=" + facebookProfile +
                '}';
    }


}
