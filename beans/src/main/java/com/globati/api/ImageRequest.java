package com.globati.api;

public class ImageRequest {
    String id;
    String imagePath;

    public ImageRequest() {
    }

    public ImageRequest(String imagePath) {
        this.imagePath = imagePath;
    }

    public ImageRequest(String id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
