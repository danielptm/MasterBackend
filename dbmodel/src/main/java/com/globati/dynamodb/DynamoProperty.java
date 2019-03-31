package com.globati.dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.globati.dynamodb.common.DynamoBusinessInfo;
import com.globati.dynamodb.converters.lists.DynamoRecommendationListConverter;
import com.globati.dynamodb.converters.lists.DynamoTourListConverter;
import com.globati.dynamodb.tour.DynamoTour;
import com.globati.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerRegistry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@DynamoDBTable(tableName="Property")
public class DynamoProperty extends DynamoBusinessInfo {

    private static final Logger LOGGER = LogManager.getLogger(DynamoRecommendationListConverter.class);

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
    @DynamoDBAttribute (attributeName = "salt")
    private byte[] salt;
    @DynamoDBAttribute (attributeName = "hashedPassword")
    private String hashedPassword;
    @DynamoDBAttribute (attributeName = "lastLogin")
    private Date lastLogin;
    @DynamoDBAttribute (attributeName = "apiToken")
    private String apiToken;
    @DynamoDBAttribute (attributeName = "apiTokenExpiration")
    private String apiTokenExpiration;



    @DynamoDBAttribute(attributeName = "recommendations")
    @DynamoDBTypeConverted(converter = DynamoRecommendationListConverter.class)
    List<DynamoRecommendation> dynamoRecommendations;

    @DynamoDBAttribute(attributeName = "tours")
    @DynamoDBTypeConverted(converter = DynamoTourListConverter.class)
    List<DynamoTour> dynamoTours;

    public DynamoProperty() {
        super();
        this.dynamoRecommendations = new ArrayList<DynamoRecommendation>();
        this.dynamoTours = new ArrayList<DynamoTour>();
    }

    public List<DynamoRecommendation> getDynamoRecommendations() {
        return dynamoRecommendations;
    }

    public void setDynamoRecommendations(List<DynamoRecommendation> dynamoRecommendations) {
        this.dynamoRecommendations = dynamoRecommendations;
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

    public List<DynamoTour> getDynamoTours() {
        return dynamoTours;
    }

    public void setDynamoTours(List<DynamoTour> dynamoTours) {
        this.dynamoTours = dynamoTours;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiTokenExpiration() {
        return apiTokenExpiration;
    }

    public void setApiTokenExpiration(String apiTokenExpiration) {
        this.apiTokenExpiration = apiTokenExpiration;
    }

    @Override
    public String toString() {
        String toWrite = null;
        try {
            toWrite = Mapper.getMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            LOGGER.error("DynamoProperty exception: ");
            e.printStackTrace();
        }
        return toWrite;
    }
}
