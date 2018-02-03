package com.globati.deserialization_beans.response.employee;

public class ResponseBlog {
    String cityAbout;
    String title;
    String description;
    String blogLink;
    String imageLink;

    public ResponseBlog(String cityAbout, String title, String description, String blogLink, String imageLink) {
        this.cityAbout = cityAbout;
        this.title = title;
        this.description = description;
        this.blogLink = blogLink;
        this.imageLink = imageLink;
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
}
