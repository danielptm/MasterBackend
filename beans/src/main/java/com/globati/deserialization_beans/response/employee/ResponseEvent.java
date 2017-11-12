package com.globati.deserialization_beans.response.employee;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

public class ResponseEvent {

    protected double targetLong;
    protected String street;
    protected String city;
    protected String country;
    protected String description;
    protected String title;
    protected Date date;
    List<String> images;

    public ResponseEvent(){}

    public ResponseEvent(double targetLat, double targetLong, String street, String city, String country, String description,  String title, Date date, List<String> images) {
        this.targetLat = targetLat;
        this.targetLong = targetLong;
        this.street = street;
        this.city = city;
        this.country = country;
        this.description = description;
        this.title = title;
        this.date = date;
        this.images = images;
    }
    protected double targetLat;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }


    @Override
    public String toString() {
        return "ResponseEvent{" +
                "targetLong=" + targetLong +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", images=" + images +
                ", targetLat=" + targetLat +
                '}';
    }

}
