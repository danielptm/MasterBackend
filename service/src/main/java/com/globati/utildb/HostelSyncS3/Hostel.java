package com.globati.utildb.HostelSyncS3;

public class Hostel {
    private String city;
    private String country;
    private String hostelName;
    private String email;
    private String street;
    private String website;
    private String directBooking;
    private String phoneNumber;
    private String latitude;
    private String longitude;
    private String appImage;

    public Hostel() {
    }

    public Hostel(String city, String country, String hostelName, String email, String street, String website, String directBooking, String phoneNumber, String latitude, String longitude, String appImage) {
        this.city = city;
        this.country = country;
        this.hostelName = hostelName;
        this.email = email;
        this.street = street;
        this.website = website;
        this.directBooking = directBooking;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.appImage = appImage;
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

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hosteName) {
        this.hostelName = hosteName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDirectBooking() {
        return directBooking;
    }

    public void setDirectBooking(String directBooking) {
        this.directBooking = directBooking;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAppImage() {
        return appImage;
    }

    public void setAppImage(String appImage) {
        this.appImage = appImage;
    }
}
