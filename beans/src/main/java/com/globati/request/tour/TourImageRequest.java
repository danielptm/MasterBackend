package com.globati.request.tour;

public class TourImageRequest {
    Long id;
    String imagePath;

    public TourImageRequest() {
    }

    public TourImageRequest(String imagePath) {
        this.imagePath = imagePath;
    }

    public TourImageRequest(Long id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
