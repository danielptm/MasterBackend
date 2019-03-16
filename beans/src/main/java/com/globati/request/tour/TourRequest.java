package com.globati.request.tour;

import java.util.List;

public class TourRequest {
    Long id;
    String propertyEmail;
    String title;
    String description;
    Double targetLat;
    Double targetLong;
    String street;
    String city;
    String country;
    List<TourImageRequest> tourImages;
    List<TourStopRequest> tourStops;

    public TourRequest() {
    }

    public List<TourImageRequest> getTourImages() {
        return tourImages;
    }

    public void setTourImages(List<TourImageRequest> tourImages) {
        this.tourImages = tourImages;
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

    public List<TourStopRequest> getTourStopRequests() {
        return tourStops;
    }

    public void setTourStopRequests(List<TourStopRequest> tourStopRequests) {
        this.tourStops = tourStopRequests;
    }

    public String getPropertyEmail() {
        return propertyEmail;
    }

    public void setPropertyEmail(String propertyId) {
        this.propertyEmail = propertyId;
    }

    public List<TourStopRequest> getTourStops() {
        return tourStops;
    }

    public void setTourStops(List<TourStopRequest> tourStops) {
        this.tourStops = tourStops;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TourRequest{");
        sb.append("id=").append(id);
        sb.append(", propertyEmail=").append(propertyEmail);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", targetLat=").append(targetLat);
        sb.append(", targetLong=").append(targetLong);
        sb.append(", street='").append(street).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", images=").append(tourImages);
        sb.append(", tourStopRequests=").append(tourStops);
        sb.append('}');
        return sb.toString();
    }
}