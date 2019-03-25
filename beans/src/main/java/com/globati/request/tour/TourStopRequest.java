package com.globati.request.tour;

import java.util.List;

public class TourStopRequest {
    String id;
    String tourId;
    String propertyEmail;
    String title;
    String description;
    Double targetLat;
    Double targetLong;
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
    List<TourStopImageRequest> tourStopImages;

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
