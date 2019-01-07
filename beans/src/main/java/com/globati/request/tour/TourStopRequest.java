package com.globati.request.tour;

import java.util.List;

public class TourStopRequest {
    Long id;
    String title;
    String description;
    Double targetLat;
    Double targetLong;
    String street;
    String city;
    String country;
    Integer orderNumber;
    List<TourStopImageRequest> tourStopImages;

    public TourStopRequest() {}

    public TourStopRequest(Long id, Integer orderNumber, String title, String description, Double targetLat, Double targetLong, String street, String city, String country) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.targetLat = targetLat;
        this.targetLong = targetLong;
        this.street = street;
        this.city = city;
        this.country = country;
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
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

    public List<TourStopImageRequest> getTourStopImages() {
        return tourStopImages;
    }

    public void setTourStopImages(List<TourStopImageRequest> tourStopImages) {
        this.tourStopImages = tourStopImages;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TourStopRequest{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", targetLat=").append(targetLat);
        sb.append(", targetLong=").append(targetLong);
        sb.append(", street='").append(street).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", images=").append(tourStopImages);
        sb.append('}');
        return sb.toString();
    }
}
