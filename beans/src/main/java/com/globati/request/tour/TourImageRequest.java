package com.globati.request.tour;

public class TourImageRequest {
    String imagePath;

    public TourImageRequest(String imagePath) {
        this.imagePath = imagePath;
    }

    public TourImageRequest() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
