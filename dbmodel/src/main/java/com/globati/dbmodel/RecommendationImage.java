package com.globati.dbmodel;

import javax.persistence.*;

@Entity
@Table(name = "recommendationimage")
public class RecommendationImage extends ImageEntity{

    @ManyToOne
    private Recommendation recommendation;

    public RecommendationImage(){}

    public RecommendationImage(Recommendation recommendation, String path){
        this.recommendation = recommendation;
        this.path = path;
    }

    public RecommendationImage(String path){
        this.path = path;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }




}
