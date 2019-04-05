package com.globati.api;

public class ImageRequest {
    String id;
    String path;

    public ImageRequest() {
    }

    public ImageRequest(String imagePath) {
        this.path = imagePath;
    }

    public ImageRequest(String id, String imagePath) {
        this.id = id;
        this.path = imagePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
