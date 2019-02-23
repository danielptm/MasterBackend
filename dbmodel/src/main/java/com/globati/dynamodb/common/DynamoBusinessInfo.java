package com.globati.dynamodb.common;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

import java.util.Date;
import java.util.UUID;

public abstract  class DynamoBusinessInfo {
    @DynamoDBAttribute
    private UUID id;
    @DynamoDBAttribute
    protected double latitude;
    @DynamoDBAttribute
    protected double longitude;
    @DynamoDBAttribute
    protected String street;
    @DynamoDBAttribute
    protected String city;
    @DynamoDBAttribute
    protected String country;
    @DynamoDBAttribute
    protected String description;
    @DynamoDBAttribute
    protected boolean active;
    @DynamoDBAttribute
    protected String title;
    @DynamoDBAttribute
    protected Date dateInactive;
    @DynamoDBAttribute
    protected Date dateActive;

}
