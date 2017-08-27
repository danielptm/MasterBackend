package com.globati.webmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by daniel on 12/16/16.
 *
 * Email will probably be moved to the application level
 * of the new Spring project
 */
public class Event {

    @JsonProperty("_date")
    private String date;
    @JsonProperty("_title")
    private String title;
    @JsonProperty("_description")
    private String description;
    @JsonProperty("_targetLat")
    private Double targetLat;
    @JsonProperty("_targetLong")
    private Double targetLong;
    @JsonProperty("_street")
    private String street;
    @JsonProperty("_city")
    private String city;
    @JsonProperty("_country")
    private String country;
    @JsonProperty("_employeeId")
    private Long employeeId;
    @JsonProperty("_imageName1")
    private String imageName1;
    @JsonProperty("_imageName2")
    private String imageName2;
    @JsonProperty("_imageName3")
    private String imageName3;

    public Event() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getImageName1() {
        return imageName1;
    }

    public void setImageName1(String imageName1) {
        this.imageName1 = imageName1;
    }

    public String getImageName2() {
        return imageName2;
    }

    public void setImageName2(String imageName2) {
        this.imageName2 = imageName2;
    }

    public String getImageName3() {
        return imageName3;
    }

    public void setImageName3(String imageName3) {
        this.imageName3 = imageName3;
    }





}
