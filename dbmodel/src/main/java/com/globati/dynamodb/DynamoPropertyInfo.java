package com.globati.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

import java.util.Date;

public class DynamoPropertyInfo {
    @DynamoDBAttribute
    private String password;
    @DynamoDBAttribute
    private byte[] sale;
    @DynamoDBAttribute
    private Date lastLogin;
    @DynamoDBAttribute
    private Date dateCreated;
    @DynamoDBAttribute
    private String apiToken;
    @DynamoDBAttribute
    private String tokenExpiration;

}
