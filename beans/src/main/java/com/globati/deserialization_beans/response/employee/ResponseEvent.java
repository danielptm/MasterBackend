package com.globati.deserialization_beans.response.employee;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

public class ResponseEvent {

    private Long id;
    private String city;
    private String country;
    private String dateInactive;
    private String description;
    private String location;
    private String street;
    private Double targetLat;
    private Double targetLong;
    private String title;
    private String showOnMap;
    private Long imageIncrement; // This should be taken away, because it is a value need on client not server.
    private List<ResponseImage> images;

    public ResponseEvent() {
    }

    public ResponseEvent(Long id, String city, String country, String dateInactive, String description, String location, String street, Double targetLat, Double targetLong, String title, String showOnMap,Long imageIncrement, List<ResponseImage> images) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.dateInactive = dateInactive;
        this.description = description;
        this.location = location;
        this.street = street;
        this.targetLat = targetLat;
        this.targetLong = targetLong;
        this.title = title;
        this.showOnMap = showOnMap;
        this.images = images;
        this.imageIncrement = imageIncrement;

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

    public String getDateInactive() {
        return dateInactive;
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

    public String getShowOnMap() {
        return showOnMap;
    }

    public Long getImageIncrement() {
        return imageIncrement;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseEvent{");
        sb.append("id=").append(id);
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", dateInactive='").append(dateInactive).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", targetLat=").append(targetLat);
        sb.append(", targetLong=").append(targetLong);
        sb.append(", title='").append(title).append('\'');
        sb.append(", showOnMap='").append(showOnMap).append('\'');
        sb.append(", images='").append(images).append('\'');
        sb.append(", imageIncrement=").append(imageIncrement);
        sb.append('}');
        return sb.toString();
    }
}


