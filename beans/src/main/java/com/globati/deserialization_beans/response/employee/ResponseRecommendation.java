package com.globati.deserialization_beans.response.employee;

import java.util.List;

public class ResponseRecommendation {

    private Long id;
    private String city;
    private String country;
    private String description;
    private String location;
    private String street;
    private Double targetLat;
    private Double targetLong;
    private String title;
    private boolean active;
    private List<ResponseImage> images;

    public ResponseRecommendation() {
    }

    public ResponseRecommendation(Long id, String city, String country, String description,  String location, String street, Double targetLat, Double targetLong, String title, boolean active, List<ResponseImage> recommendationImages) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.description = description;
        this.location = location;
        this.street = street;
        this.targetLat = targetLat;
        this.targetLong = targetLong;
        this.title = title;
        this.images = recommendationImages;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }


    public String getLocation() {
        return location;
    }

    public String getStreet() {
        return street;
    }

    public Double getTargetLat() {
        return targetLat;
    }

    public Double getTargetLong() {
        return targetLong;
    }

    public String getTitle() {
        return title;
    }

    public boolean isActive() {
        return active;
    }

    public List<ResponseImage> getImages() {
        return images;
    }


}
