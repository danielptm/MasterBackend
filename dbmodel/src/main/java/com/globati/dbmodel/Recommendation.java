package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by daniel on 12/20/16.
 */
@Entity
@Table(name = "recommendation")
public class Recommendation extends BusinessEntity {

    @ManyToOne
    @JoinColumn(name="employeeid")
    @JsonBackReference
    //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    private Employee employee;

    @OneToMany(mappedBy = "recommendation", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    @JsonBackReference
    List<RecommendationImage> recommendationimages;

    public Recommendation(){}

    public Recommendation(Employee employee, String title, String description, double targetLat, double targetLong,
                          String street, String city, String country
                          ) {
        this.employee = employee;
        this.title = title;
        this.description = description;
        this.targetLat = targetLat;
        this.targetLong = targetLong;
        this.street = street;
        this.city = city;
        this.country = country;
        this.active = true;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<RecommendationImage> getRecommendationimages() {
        return recommendationimages;
    }

    public void setRecommendationimages(List<RecommendationImage> recommendationimages) {
        this.recommendationimages = recommendationimages;
    }



}



