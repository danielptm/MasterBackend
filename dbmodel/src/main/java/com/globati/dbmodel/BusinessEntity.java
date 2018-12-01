package com.globati.dbmodel;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by daniel on 4/2/17.
 */
@MappedSuperclass
public abstract class BusinessEntity extends BaseEntity {

    @Column(name = "targetlat")
    protected double targetLat;
    @Column(name = "targetlong")
    protected double targetLong;
    @Column(length = 100, name = "street")
    protected String street;
    @Column(length = 100, name = "city")
    protected String city;
    @Column(length = 100, name = "country")
    protected String country;
    @Column(name = "image", length = 500)
    protected String image;
    @Column(length = 3000, name = "description")
    protected String description;
    //I think location can be removed ....
    @Column(length = 100, name = "location")
    protected String location;
    @Column(name = "active")
    protected boolean active;
    @Column(length = 100, name = "title")
    protected String title;
    @Column(name="dateinactive")
    protected Date dateInactive;


    public double getTargetLat() {
        return targetLat;
    }

    public void setTargetLat(double targetLat) {
        this.targetLat = targetLat;
    }

    public double getTargetLong() {
        return targetLong;
    }

    public void setTargetLong(double targetLong) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateInactive() {
        return dateInactive;
    }

    public void setDateInactive(Date dateInactive) {
        this.dateInactive = dateInactive;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessEntity{");
        sb.append("targetLat=").append(targetLat);
        sb.append(", targetLong=").append(targetLong);
        sb.append(", street='").append(street).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", active=").append(active);
        sb.append(", title='").append(title).append('\'');
        sb.append(", dateInactive=").append(dateInactive);
        sb.append('}');
        return sb.toString();
    }
}
