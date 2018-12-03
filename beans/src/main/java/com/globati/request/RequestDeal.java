package com.globati.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by daniel on 8/6/17.
 */
public class RequestDeal {

    @JsonProperty("id")
    Long id;
    @JsonProperty("title")
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("businessName")
    String businessName;
    @JsonProperty("website")
    String website;
    @JsonProperty("category")
    String category;
    @JsonProperty("plan")
    String plan;
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
    @JsonProperty("nonce")
    String nonce;
    @JsonProperty("email")
    String email;
    @JsonProperty("billingStreet")
    String billingStreet;
    @JsonProperty("billingCity")
    String billingCity;
    @JsonProperty("billingRegion")
    String billingRegion;
    @JsonProperty("billingCountry")
    String billingCountry;
    @JsonProperty("cost")
    Double cost;
    @JsonProperty("image1")
    String image1;
    @JsonProperty("image2")
    String image2;
    @JsonProperty("image3")
    String image3;

    RequestDeal(){}

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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
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

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingRegion() {
        return billingRegion;
    }

    public void setBillingRegion(String billingRegion) {
        this.billingRegion = billingRegion;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    @Override
    public String toString() {
        return "RequestDeal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", businessName='" + businessName + '\'' +
                ", website='" + website + '\'' +
                ", category='" + category + '\'' +
                ", plan='" + plan + '\'' +
                ", targetLat=" + targetLat +
                ", targetLong=" + targetLong +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", nonce='" + nonce + '\'' +
                ", email='" + email + '\'' +
                ", billingStreet='" + billingStreet + '\'' +
                ", billingCity='" + billingCity + '\'' +
                ", billingRegion='" + billingRegion + '\'' +
                ", billingCountry='" + billingCountry + '\'' +
                ", cost=" + cost +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", image3='" + image3 + '\'' +
                '}';
    }


}
