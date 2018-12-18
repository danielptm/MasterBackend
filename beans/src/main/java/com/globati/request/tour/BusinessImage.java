package com.globati.request.tour;

public class BusinessImage {
    String imagePath;
    String imageType;
    int stopOrder;

    public BusinessImage(String imagePath, String imageType, int stopOrder) {
        this.imagePath = imagePath;
        this.imageType = imageType;
        this.stopOrder = stopOrder;
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

    public int getStopOrder() {
        return stopOrder;
    }

    public void setStopOrder(int stopOrder) {
        this.stopOrder = stopOrder;
    }
}
