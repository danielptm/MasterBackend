package com.globati.api.tour;

import com.globati.api.ImageRequest;

import java.util.List;

public class TourStopRequest {
    String id;
    String tourId;
    String propertyEmail;
    String title;
    String description;
    Double latitude;
    Double longitude;
    String street;
    String city;
    String country;

    public Integer getStopOrder() {
        return stopOrder;
    }

    public void setStopOrder(Integer stopOrder) {
        this.stopOrder = stopOrder;
    }

    Integer stopOrder;
    List<ImageRequest> images;

    public String getPropertyEmail() {
        return propertyEmail;
    }

    public void setPropertyEmail(String propertyEmail) {
        this.propertyEmail = propertyEmail;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public TourStopRequest() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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

    public List<ImageRequest> getImages() {
        return images;
    }

    public void setImages(List<ImageRequest> images) {
        this.images = images;
    }
}
