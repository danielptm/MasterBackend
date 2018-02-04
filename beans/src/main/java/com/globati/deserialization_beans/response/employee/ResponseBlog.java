package com.globati.deserialization_beans.response.employee;

public class ResponseBlog {

    Long id;
    String cityAbout;
    String title;
    String description;
    String blogLink;
    String imageLink;

    public ResponseBlog(Long id, String title, String cityAbout, String description, String blogLink, String imageLink) {
        this.id = id;
        this.title = title;
        this.cityAbout = cityAbout;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseBlog{");
        sb.append("cityAbout='").append(cityAbout).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", blogLink='").append(blogLink).append('\'');
        sb.append(", imageLink='").append(imageLink).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
