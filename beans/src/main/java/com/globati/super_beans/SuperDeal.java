package com.globati.super_beans;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by daniel on 8/7/17.
 */
public abstract class SuperDeal {

    Long id;
    double targetLat;
    double targetLong;
    double distance;
    String street;
    String city;
    String country;
    String image;
    String description;
    String location;
    String title;
    String image2;
    String image3;
    String website;
    String email;
    double nonRecruiterDistance;

    SuperDeal(){}

    public SuperDeal(Long id, double targetLat, double targetLong, double distance, String street, String city, String country, String image, String description, String location, String title, String image2, String image3, String website, String email, double nonRecruiterDistance) {
        this.id = id;
        this.targetLat = targetLat;
        this.targetLong = targetLong;
        this.distance = distance;
        this.street = street;
        this.city = city;
        this.country = country;
        this.image = image;
        this.description = description;
        this.location = location;
        this.title = title;
        this.image2 = image2;
        this.image3 = image3;
        this.website = website;
        this.email = email;
        this.nonRecruiterDistance = nonRecruiterDistance;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTargetLat() {
        return targetLat;
    }

    public void setTargetLat(double targetLat) {
        this.targetLat = targetLat;
    }

    public double getTargetLong() {
        return targetLong;
    }

    public void setTargetLong(double targetLong) {
        this.targetLong = targetLong;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getNonRecruiterDistance() {
        return nonRecruiterDistance;
    }

    public void setNonRecruiterDistance(double nonRecruiterDistance) {
        this.nonRecruiterDistance = nonRecruiterDistance;
    }




}
