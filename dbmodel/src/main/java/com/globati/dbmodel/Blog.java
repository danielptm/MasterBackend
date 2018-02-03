package com.globati.dbmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globati.enums.BlogApprovalStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="blog")
public class Blog extends BaseEntity{

    @Column(name="cityabout")
    String cityAbout;
    @Column(name="title")
    String title;
    @Column(name="description")
    String description;
    @Column(name="bloglink")
    String blogLink;
    @Column(name="imagelink")
    String imageLink;
    @Column(name="date")
    Date date;
    @Column(name = "blogapprovalstatus")
    @Enumerated(EnumType.STRING)
    BlogApprovalStatus blogApprovalStatus;

    @ManyToOne
    @JoinColumn(name="employeeid")
    @JsonBackReference
    Employee employee;

    public Blog(){}

    public Blog(Employee employee, String cityAbout, String title, String description, String blogLink, String imageLink){
        this.employee = employee;
        this.cityAbout = cityAbout;
        this.title = title;
        this.description = description;
        this.blogLink = blogLink;
        this.imageLink = imageLink;
        this.date = new Date();
        this.blogApprovalStatus = BlogApprovalStatus.WAITING_APPROVAL;
    }

    public String getCityAbout() {
        return cityAbout;
    }

    public void setCityAbout(String cityAbout) {
        this.cityAbout = cityAbout;
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

    public String getBlogLink() {
        return blogLink;
    }

    public void setBlogLink(String blogLink) {
        this.blogLink = blogLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BlogApprovalStatus getBlogApprovalStatus() {
        return blogApprovalStatus;
    }

    public void setBlogApprovalStatus(BlogApprovalStatus blogApprovalStatus) {
        this.blogApprovalStatus = blogApprovalStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Blog{");
        sb.append("cityAbout='").append(cityAbout).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", blogLink='").append(blogLink).append('\'');
        sb.append(", imageLink='").append(imageLink).append('\'');
        sb.append(", date=").append(date);
        sb.append(", employee=").append(employee);
        sb.append('}');
        return sb.toString();
    }
}
