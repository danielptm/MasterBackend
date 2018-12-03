package com.globati.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by daniel on 7/16/17.
 */
public class CreateProperty {

    @JsonProperty("firstName")
    String firstName;
    @JsonProperty("email")
    String email;
    @JsonProperty("username")
    String username;
    @JsonProperty("password")
    String password;
    @JsonProperty("targetLat")
    Double targetLat;
    @JsonProperty("targetLong")
    Double targetLong;
    @JsonProperty("street")
    String street;
    @JsonProperty("city")
    String city;
    @JsonProperty("country")
    String country;
    @JsonProperty("image")
    String image;

    public CreateProperty(){ }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}