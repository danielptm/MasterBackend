package com.globati.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="property")
public class Property {

    @Id
    @Column(name="id")
    String id;
    @Column(name="propertyname")
    String propertyName;
    @Column(name="email", unique = true)
    String email;
    @Column(name="address")
    String address;
    @Column(name="website")
    String website;
    @Column(name="city")
    String city;
    @Column(name = "datesent")
    Date date;

    public Property(){
        this.id = UUID.randomUUID().toString();
    }

    public Property(String propertyName, String email, String address, String website, String city) {
        this.id = UUID.randomUUID().toString();
        this.propertyName = propertyName;
        this.email = email;
        this.address = address;
        this.website = website;
        this.city = city;
        this.date = null;
    }

    public String getId() {
        return id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }

    public String getCity() {
        return city;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Property{");
        sb.append("id='").append(id).append('\'');
        sb.append(", propertyName='").append(propertyName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", website='").append(website).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
