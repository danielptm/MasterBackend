package com.globati.webmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by daniel on 6/5/17.
 */
public class Recommendation {



    @JsonProperty("_employeeId")
    Long employeeId;
    @JsonProperty("_title")
    String title;
    @JsonProperty("_description")
    String description;
    @JsonProperty("_targetLat")
    Double targetLat;
    @JsonProperty("_targetLong")
    Double targetLong;
    @JsonProperty("_street")
    String street;
    @JsonProperty("_city")
    String city;
    @JsonProperty("_country")
    String country;
    @JsonProperty("_image1")
    String image1;
    @JsonProperty("_image2")
    String image2;
    @JsonProperty("_image3")
    String image3;

    public Recommendation(){}


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTargetLat() {
        return targetLat;
    }

    public void setTargetLat(Double targetLat) {
        this.targetLat = targetLat;
    }

    public Double getTargetLong() {
        return targetLong;
    }

    public void setTargetLong(Double targetLong) {
        this.targetLong = targetLong;
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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
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





}
