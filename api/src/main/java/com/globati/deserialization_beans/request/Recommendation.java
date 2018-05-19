package com.globati.deserialization_beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by daniel on 6/5/17.
 */
public class Recommendation {

    @JsonProperty("id")
    Long id;
    @JsonProperty("employeeId")
    Long employeeId;
    @JsonProperty("title")
    String title;
    @JsonProperty("description")
    String description;
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
    @JsonProperty("image1")
    String image1;
    @JsonProperty("image2")
    String image2;
    @JsonProperty("image3")
    String image3;
    @JsonProperty("images")
    List<String> images;
    @JsonProperty("category")
    String category;

    public Recommendation(){}

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Recommendation{");
        sb.append("id=").append(id);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", targetLat=").append(targetLat);
        sb.append(", targetLong=").append(targetLong);
        sb.append(", street='").append(street).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", image1='").append(image1).append('\'');
        sb.append(", image2='").append(image2).append('\'');
        sb.append(", image3='").append(image3).append('\'');
        sb.append(", images=").append(images);
        sb.append(", category='").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
