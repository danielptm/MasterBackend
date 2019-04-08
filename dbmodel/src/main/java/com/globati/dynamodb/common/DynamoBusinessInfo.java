package com.globati.dynamodb.common;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import java.util.Date;
import java.util.UUID;

@DynamoDBDocument
public abstract  class DynamoBusinessInfo {
    @DynamoDBAttribute (attributeName = "id")
    private String id;
    @DynamoDBAttribute (attributeName = "latitude")
    protected double latitude;
    @DynamoDBAttribute (attributeName = "longitude")
    protected double longitude;
    @DynamoDBAttribute (attributeName = "street")
    protected String street;
    @DynamoDBAttribute (attributeName = "city")
    protected String city;
    @DynamoDBAttribute (attributeName = "country")
    protected String country;
    @DynamoDBAttribute (attributeName = "description")
    protected String description;
    @DynamoDBAttribute (attributeName = "active")
    protected boolean active;
    @DynamoDBAttribute (attributeName = "dateInactive")
    protected Date dateInactive;
    @DynamoDBAttribute (attributeName = "dateActive")
    protected Date dateActive;


    public DynamoBusinessInfo() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDateInactive() {
        return dateInactive;
    }

    public void setDateInactive(Date dateInactive) {
        this.dateInactive = dateInactive;
    }

    public Date getDateActive() {
        return dateActive;
    }

    public void setDateActive(Date dateActive) {
        this.dateActive = dateActive;
    }
}
