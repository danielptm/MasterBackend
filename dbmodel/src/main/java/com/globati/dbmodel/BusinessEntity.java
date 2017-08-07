package com.globati.dbmodel;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by daniel on 4/2/17.
 */
@MappedSuperclass
public abstract class BusinessEntity extends BaseEntity {

    @Column(name = "targetlat")
    protected double targetLat;
    @Column(name = "targetlong")
    protected double targetLong;
    @Column(name = "distance")
    protected double distance;
    @Column(length = 100, name = "street")
    protected String street;
    @Column(length = 100, name = "city")
    protected String city;
    @Column(length = 100, name = "country")
    protected String country;
    @Column(name = "image")
    protected String image;
    @Column(length = 3000, name = "description")
    protected String description;
    @Column(length = 100, name = "location")
    protected String location;
    @Column(name = "active")
    protected boolean active;
    @Column(length = 100, name = "title")
    protected String title;
    @Column(name="dateinactive")
    protected Date dateInactive;
    @Column(name="image2")
    protected String image2;
    @Column(name="image3")
    protected String image3;


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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateInactive() {
        return dateInactive;
    }

    public void setDateInactive(Date dateInactive) {
        this.dateInactive = dateInactive;
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
