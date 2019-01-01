package com.globati.request.tour;

public class TourImageRequest {
    Long id;
    String path;

    public TourImageRequest() {
    }

    public TourImageRequest(String imagePath) {
        this.path = imagePath;
    }

    public TourImageRequest(Long id, String imagePath) {
        this.id = id;
        this.path = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
