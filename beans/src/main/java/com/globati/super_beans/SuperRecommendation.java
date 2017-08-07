package com.globati.super_beans;

/**
 * Created by daniel on 8/7/17.
 */
public abstract class SuperRecommendation {

    Long employeeId;
    Long id;
    String title;
    String description;
    String location;
    String image1;
    String image2;
    String image3;
    String street;
    String city;
    String country;
    Double targetLat;
    Double targetLong;

    public SuperRecommendation(Long employeeId, Long id, String title, String description, String location, String image1, String image2, String image3, String street, String city, String country, Double targetLat, Double targetLong) {
        this.employeeId = employeeId;
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.street = street;
        this.city = city;
        this.country = country;
        this.targetLat = targetLat;
        this.targetLong = targetLong;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    @Override
    public String toString() {
        return "SuperRecommendation{" +
                "employeeId=" + employeeId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", image3='" + image3 + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", targetLat=" + targetLat +
                ", targetLong=" + targetLong +
                '}';
    }


}
