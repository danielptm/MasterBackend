package com.globati.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by daniel on 4/15/17.
 * <p>
 * Data that can be updated by employee on the clientside
 */
public class UpdateProperty {

    @JsonProperty("employeeId")
    private Long employeeId;
    @JsonProperty("email")
    private String email;
    @JsonProperty("username")
    private String username;
    @JsonProperty("instagramUserName")
    private String instagramUserName;
    @JsonProperty("instagramToken")
    private String instagramToken;
    @JsonProperty("instagramUserId")
    private String instagramUserId;
    @JsonProperty("paypal")
    private String paypal;
    @JsonProperty("day30")
    private Double day30;
    @JsonProperty("day60")
    private Double day60;
    @JsonProperty("day90")
    private Double day90;
    @JsonProperty("propLat")
    private Double propLat;
    @JsonProperty("propLong")
    private Double propLong;
    @JsonProperty("street")
    private String street;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("about")
    private String about;
    @JsonProperty("display")
    private String display;
    @JsonProperty("welcomeMail")
    private String welcomeMail;
    @JsonProperty("recruitmentMail")
    private String recruitmentMail;
    @JsonProperty("image")
    private String image;
    @JsonProperty("image2")
    private String image2;
    @JsonProperty("image3")
    private String image3;
    @JsonProperty("website")
    private String website;
    @JsonProperty("bookingUrl")
    private String bookingUrl;

    public UpdateProperty() {}


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInstagramUserName() {
        return instagramUserName;
    }

    public void setInstagramUserName(String instagramUserName) {
        this.instagramUserName = instagramUserName;
    }

    public String getInstagramToken() {
        return instagramToken;
    }

    public void setInstagramToken(String instagramToken) {
        this.instagramToken = instagramToken;
    }

    public String getInstagramUserId() {
        return instagramUserId;
    }

    public void setInstagramUserId(String instagramUserId) {
        this.instagramUserId = instagramUserId;
    }

    public String getPaypal() {
        return paypal;
    }

    public void setPaypal(String paypal) {
        this.paypal = paypal;
    }

    public Double getDay30() {
        return day30;
    }

    public void setDay30(Double day30) {
        this.day30 = day30;
    }

    public Double getDay60() {
        return day60;
    }

    public void setDay60(Double day60) {
        this.day60 = day60;
    }

    public Double getDay90() {
        return day90;
    }

    public void setDay90(Double day90) {
        this.day90 = day90;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBookingUrl() {
        return bookingUrl;
    }

    public void setBookingUrl(String bookingUrl) {
        this.bookingUrl = bookingUrl;
    }

    @Override
    public String toString() {
        return "UpdateProperty{" +
                "employeeId=" + employeeId +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", instagramUserName='" + instagramUserName + '\'' +
                ", instagramToken='" + instagramToken + '\'' +
                ", instagramUserId='" + instagramUserId + '\'' +
                ", paypal='" + paypal + '\'' +
                ", day30=" + day30 +
                ", day60=" + day60 +
                ", day90=" + day90 +
                ", propLat=" + propLat +
                ", propLong=" + propLong +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", about='" + about + '\'' +
                ", display='" + display + '\'' +
                ", welcomeMail='" + welcomeMail + '\'' +
                ", recruitmentMail='" + recruitmentMail + '\'' +
                ", image='" + image + '\'' +
                ", image2='" + image2 + '\'' +
                ", image3='" + image3 + '\'' +
                '}';
    }






}