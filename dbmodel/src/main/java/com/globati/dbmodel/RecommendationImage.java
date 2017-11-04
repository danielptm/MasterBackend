package com.globati.dbmodel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recommendationimage")
public class RecommendationImage extends ImageEntity{

    @ManyToOne
    @JoinColumn(name = "recommendationid")
    private Recommendation recommendation;

    public RecommendationImage(){}

    public RecommendationImage(Recommendation recommendation, String path){
        this.recommendation = recommendation;
        this.path = path;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }




}
