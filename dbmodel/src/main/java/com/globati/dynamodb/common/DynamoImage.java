package com.globati.dynamodb.common;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

import java.util.UUID;

public class DynamoImage {
    @DynamoDBAttribute
    private String id;
    @DynamoDBAttribute
    private String path;

    public DynamoImage() {

    }

    public DynamoImage(String path) {
        this.id = UUID.randomUUID().toString();
        this.path = path;
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
