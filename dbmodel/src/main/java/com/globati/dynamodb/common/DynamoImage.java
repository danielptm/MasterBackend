package com.globati.dynamodb.common;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

import java.util.UUID;

public abstract class DynamoImage {
    @DynamoDBAttribute
    private String id;
    @DynamoDBAttribute
    private String imagePath;
}
