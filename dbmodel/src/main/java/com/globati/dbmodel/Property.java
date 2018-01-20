package com.globati.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="property")
public class Property {

    @Id
    @Column(name="id")
    String id;
    @Column(name="propertyname")
    String propertyName;
    @Column(name="email")
    String email;
    @Column(name="address")
    String address;
    @Column(name="website")
    String website;
    @Column(name="city")
    String city;

    public Property(String propertyName, String email, String address, String website, String city) {
        this.id = UUID.randomUUID().toString();
        this.propertyName = propertyName;
        this.email = email;
        this.address = address;
        this.website = website;
        this.city = city;
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
}
