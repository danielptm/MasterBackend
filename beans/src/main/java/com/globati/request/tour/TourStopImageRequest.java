package com.globati.request.tour;

public class TourStopImageRequest {
    Long id;
    String imagePath;

    public TourStopImageRequest() {}

    public TourStopImageRequest(Long id, String path) {
        this.id = id;
        this.imagePath = path;
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
