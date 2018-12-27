package com.globati.request.tour;

public class TourImageRequest {
    String imagePath;
    String imageType;

    public TourImageRequest(String imagePath, String imageType) {
        this.imagePath = imagePath;
        this.imageType = imageType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

}
