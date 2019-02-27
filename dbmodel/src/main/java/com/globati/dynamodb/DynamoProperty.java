package com.globati.dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.mysql.enums.Verified;

@DynamoDBTable(tableName="Property")
public class DynamoProperty {

    @DynamoDBHashKey (attributeName = "email")
    private String email;
    @DynamoDBAttribute(attributeName="name")
    private String name;
    @DynamoDBAttribute(attributeName="userName")
    private String userName;
    @DynamoDBAttribute(attributeName="mobileVisitCounter")
    private String mobileVisitCounter;
    @DynamoDBAttribute(attributeName="website")
    private String website;


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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Property{");
        sb.append("email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", mobileVisitCounter='").append(mobileVisitCounter).append('\'');
        sb.append(", website='").append(website).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
