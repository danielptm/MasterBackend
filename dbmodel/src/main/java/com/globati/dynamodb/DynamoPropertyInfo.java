package com.globati.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

import java.util.Date;

public class DynamoPropertyInfo {

    @DynamoDBAttribute (attributeName = "password")
    private String password;
    @DynamoDBAttribute (attributeName = "salt")
    private byte[] salt;
    @DynamoDBAttribute (attributeName = "lastLogin")
    private Date lastLogin;
    @DynamoDBAttribute (attributeName = "dateCreated")
    private Date dateCreated;
    @DynamoDBAttribute (attributeName = "apiToken")
    private String apiToken;
    @DynamoDBAttribute (attributeName = "tokenExpiration")
    private String tokenExpiration;

}
