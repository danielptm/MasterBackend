package com.globati.deserialization_beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBlog {

    @JsonProperty("employeeId")
    Long employeeId;
    @JsonProperty("cityAbout")
    String cityAbout;
    @JsonProperty("title")
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("blogLink")
    String blogLink;
    @JsonProperty("imageLink")
    String imageLink;

    public CreateBlog(){}


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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateBlog{");
        sb.append("employeeId=").append(employeeId);
        sb.append(", cityAbout='").append(cityAbout).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", blogLink='").append(blogLink).append('\'');
        sb.append(", imageLink='").append(imageLink).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
