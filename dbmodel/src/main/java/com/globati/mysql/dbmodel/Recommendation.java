package com.globati.mysql.dbmodel;

import com.fasterxml.jackson.annotation.*;
import com.globati.mysql.enums.Category;

import javax.persistence.*;
import java.util.List;

/**
 * Created by daniel on 12/20/16.
 */
@Entity
@Table(name = "recommendation")
public class Recommendation extends BusinessEntity {

    @ManyToOne
    @JoinColumn(name="propertyid")
    @JsonBackReference
    //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    private Property property;

    @OneToMany(mappedBy = "recommendation", fetch = FetchType.EAGER,  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<RecommendationImage> images;

    @Column(name = "category", nullable=false, columnDefinition="VARCHAR(40) default 'NONE'")
    @Enumerated(EnumType.STRING)
    Category category;

    public Recommendation(){}

    public Recommendation(Property property, String title, String description, double targetLat, double targetLong,
                          String street, String city, String country, Category category
                          ) {
        this.property = property;
        this.title = title;
        this.description = description;
        this.targetLat = targetLat;
        this.targetLong = targetLong;
        this.street = street;
        this.city = city;
        this.country = country;
        this.active = true;
        this.category = category;
    }


    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public List<RecommendationImage> getImages() {
        return images;
    }

    public void setImages(List<RecommendationImage> recommendationImages) {
        this.images = recommendationImages;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}



