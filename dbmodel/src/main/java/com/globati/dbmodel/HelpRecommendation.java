package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globati.enums.HelpRecommendationStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "helprecommendation")
public class HelpRecommendation extends BaseEntity{

    @Column(name = "title")
    String title;
    @Column(name = "description")
    String description;
    @Column(name = "datecreated")
    Date dateCreated;

    HelpRecommendationStatus recommendationStatus;

    @ManyToOne
    @JoinColumn(name="employeeid")
    @JsonBackReference
    //This is simply to avoid a stackoverflow error according to this link http://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    private Employee employee;

    public HelpRecommendation() {}

    public HelpRecommendation(String title, String description, Date dateCreated, HelpRecommendationStatus recommendationStatus, Employee employee) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.recommendationStatus = recommendationStatus;
        this.employee = employee;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public HelpRecommendationStatus getRecommendationStatus() {
        return recommendationStatus;
    }

    public void setRecommendationStatus(HelpRecommendationStatus recommendationStatus) {
        this.recommendationStatus = recommendationStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HelpRecommendation{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dateCreated=").append(dateCreated);
        sb.append(", recommendationStatus=").append(recommendationStatus);
        sb.append(", employee=").append(employee);
        sb.append('}');
        return sb.toString();
    }
}
