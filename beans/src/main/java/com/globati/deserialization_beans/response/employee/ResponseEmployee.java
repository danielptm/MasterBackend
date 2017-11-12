package com.globati.deserialization_beans.response.employee;

import java.util.List;

public class ResponseEmployee {

    private String email;
    private String paypalEmail;
    private String about;
    private String welcomeMail;
    private String recruitmentMail;
    private String instagramUser;
    private String instagramUserId;
    private String instagramUserToken;
    private double propLat;
    private double propLong;
    private String street;
    private String city;
    private String country;
    private String display;
    private String globatiUsername;
    List<ResponseRecommendation> recommendations;
    List<ResponseEvent> events;

    public ResponseEmployee(){}

    public ResponseEmployee(String email, String paypalEmail, String about, String welcomeMail, String recruitmentMail, String instagramUser, String instagramUserId, String instagramUserToken, double propLat, double propLong, String street, String city, String country, String display, String globatiUsername, List<ResponseRecommendation> recommendations, List<ResponseEvent> events, String firstName) {
        this.email = email;
        this.paypalEmail = paypalEmail;
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
        this.firstName = firstName;
        this.recommendations= recommendations;
        this.events = events;
    }

    private String firstName;

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

    public List<ResponseRecommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<ResponseRecommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public List<ResponseEvent> getEvents() {
        return events;
    }

    public void setEvents(List<ResponseEvent> events) {
        this.events = events;
    }


    @Override
    public String toString() {
        return "ResponseEmployee{" +
                "email='" + email + '\'' +
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
                ", globatiUsername='" + globatiUsername + '\'' +
                ", recommendations=" + recommendations +
                ", events=" + events +
                ", firstName='" + firstName + '\'' +
                '}';
    }


}
