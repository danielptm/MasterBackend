package com.globati.deserialization_beans.response.employee;

public class ResponseImage {
    Long id;
    String path;

    public ResponseImage(Long id, String path) {
        this.id = id;
        this.path = path;
    }

    public ResponseImage() {
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseImage{");
        sb.append("id=").append(id);
        sb.append(", path='").append(path).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
