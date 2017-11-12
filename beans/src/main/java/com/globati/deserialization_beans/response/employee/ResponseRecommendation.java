package com.globati.deserialization_beans.response.employee;

import java.util.List;

public class ResponseRecommendation {

    protected double targetLat;

    @Override
    public String toString() {
        return "ResponseRecommendation{" +
                "targetLat=" + targetLat +
                ", targetLong=" + targetLong +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", title='" + title + '\'' +
                ", recommendationImages=" + recommendationImages +
                '}';
    }

    protected double targetLong;
    protected String street;
    protected String city;
    protected String country;
    protected String description;
    protected String location;
    protected String title;
    List<String> recommendationImages;

    public ResponseRecommendation(double targetLat, double targetLong, String street, String city, String country, String description, String location, String title, List<String> recommendationImages) {
        this.targetLat = targetLat;
        this.targetLong = targetLong;
        this.street = street;
        this.city = city;
        this.country = country;
        this.description = description;
        this.location = location;
        this.title = title;
        this.recommendationImages = recommendationImages;
    }

    public ResponseRecommendation(){}

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

    public List<String> getRecommendationImages() {
        return recommendationImages;
    }

    public void setRecommendationImages(List<String> recommendationImages) {
        this.recommendationImages = recommendationImages;
    }


}
