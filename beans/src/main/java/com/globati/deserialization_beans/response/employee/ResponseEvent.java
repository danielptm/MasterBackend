package com.globati.deserialization_beans.response.employee;

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
    private Date date;
    private List<ResponseImage> images;

    public ResponseEvent() {
    }

    public ResponseEvent(Long id, String city, String country, String dateInactive, String description, String location, String street, Double targetLat, Double targetLong, String title, List<ResponseImage> images, Date date) {
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
        this.images = images;
        this.date = date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public List<ResponseImage> getImages() {
        return images;
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
        sb.append(", images='").append(images).append('\'');
        sb.append('}');
        return sb.toString();
    }
}


