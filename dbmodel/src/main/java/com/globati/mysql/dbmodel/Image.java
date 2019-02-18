package com.globati.mysql.dbmodel;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image extends BaseEntity {


    @Column(name = "path", length = 500)
    @JoinColumn()
    private String path;

    @ManyToOne
    @JoinColumn(name = "recommendationid")
    Recommendation recommendation;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }

}
