package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "recommendationimage")
public class RecommendationImage extends ImageEntity{

    @ManyToOne
    @JoinColumn(name="recommendationid")
    @JsonBackReference
    private Recommendation recommendation;

    public RecommendationImage(){}

    public RecommendationImage(Recommendation recommendation, String path){
        this.recommendation = recommendation;
        this.imagePath = path;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }




}
