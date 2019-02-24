package com.globati.dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.mysql.enums.Verified;

@DynamoDBTable(tableName="Property")
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

    public DynamoProperty() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileVisitCounter() {
        return mobileVisitCounter;
    }

    public void setMobileVisitCounter(String mobileVisitCounter) {
        this.mobileVisitCounter = mobileVisitCounter;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Verified getVerified() {
        return verified;
    }

    public void setVerified(Verified verified) {
        this.verified = verified;
    }

    public DynamoPropertyInfo getDynamoPropertyInfo() {
        return dynamoPropertyInfo;
    }

    public void setDynamoPropertyInfo(DynamoPropertyInfo dynamoPropertyInfo) {
        this.dynamoPropertyInfo = dynamoPropertyInfo;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }
}
