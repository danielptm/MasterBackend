package com.globati.super_beans;

import java.util.Date;

/**
 * Created by daniel on 8/7/17.
 */
public abstract class SuperEvent {

    String title;
    Date date;
    String description;
    Double targetLat;
    Double targetLong;
    Double distance;
    String street;
    String city;
    String country;
    String image1;
    String image2;
    String image3;
    String location;
    Long employeeId;

    public SuperEvent(String title, Date date, String description, Double targetLat, Double targetLong, Double distance, String street, String city, String country, String image1, String image2, String image3, String location, Long employeeId) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.targetLat = targetLat;
        this.targetLong = targetLong;
        this.distance = distance;
        this.street = street;
        this.city = city;
        this.country = country;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.location = location;
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

}
