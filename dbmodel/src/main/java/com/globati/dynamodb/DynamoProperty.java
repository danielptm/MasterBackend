package com.globati.dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.mysql.enums.Verified;

@DynamoDBTable(tableName="DynamoProperty")
public class DynamoProperty extends DynamoBusinessInfo {

    @DynamoDBHashKey (attributeName = "email")
    private String email;
    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute
    private String userName;
    @DynamoDBAttribute
    private String mobileVisitCounter;
    @DynamoDBAttribute
    private String website;
    @DynamoDBAttribute
    private Verified verified;
    @DynamoDBAttribute
    DynamoPropertyInfo dynamoPropertyInfo;
    @DynamoDBAttribute
    String mainImage;




}
