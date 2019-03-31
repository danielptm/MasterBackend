package com.globati.service.dynamodb;

import com.globati.HelpObjects.ApiKey;
import com.globati.dynamodb.DynamoProperty;
import com.globati.mysql.dbmodel.PropertyInfo;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import com.globati.request.RequestProperty;
import com.globati.service.JwtService;
import com.globati.utildb.PBKDF2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DynamoPropertyService {

    private static final Logger LOGGER = LogManager.getLogger(DynamoPropertyService.class);

    @Autowired
    DynamoPropertyRepository dynamoPropertyRepository;

    @Autowired
    JwtService jwtService;

    public DynamoProperty createDynamoProperty(RequestProperty requestProperty) {
        DynamoProperty newProperty = new DynamoProperty();

        DynamoProperty dynamoProperty = PBKDF2.hashPropertyPassword(newProperty, requestProperty.getPassword());

        Optional.ofNullable(requestProperty.getEmail())
                .ifPresent(email -> dynamoProperty.setEmail(email));

        Optional.ofNullable(requestProperty.getUserName())
                .ifPresent(username -> dynamoProperty.setUserName(username));

        Optional.ofNullable(requestProperty.getName())
                .ifPresent(name -> dynamoProperty.setName(name));

        Optional.ofNullable(requestProperty.getLatitude())
                .ifPresent(latitude -> dynamoProperty.setLatitude(latitude));

        Optional.ofNullable(requestProperty.getLongitude())
                .ifPresent(longitude -> dynamoProperty.setLongitude(longitude));

        Optional.ofNullable(requestProperty.getStreet())
                .ifPresent(street -> dynamoProperty.setStreet(street));

        Optional.ofNullable(requestProperty.getCity())
                .ifPresent(city -> dynamoProperty.setCity(city));

        Optional.ofNullable(requestProperty.getCountry())
                .ifPresent(country -> dynamoProperty.setCountry(country));

        Optional.ofNullable(requestProperty.getDescription())
                .ifPresent(description -> dynamoProperty.setDescription(description));

        Optional.ofNullable(requestProperty.getWebsite())
                .ifPresent(website -> dynamoProperty.setWebsite(website));

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoProperty;
    }

    /**
     * Deletes the DynamoProperty and returns the id of the deleted property
     * @param email
     * @return
     */
    public String deleteDynamoProperty(String email) {
        dynamoPropertyRepository.delete(email);
        return email;
    }

    public DynamoProperty getDynamoPropertyById(String email) {
        return dynamoPropertyRepository.findOne(email);
    }

    public DynamoProperty updateDynamoProperty(RequestProperty requestProperty) {
        DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(requestProperty.getEmail());

        Optional.ofNullable(requestProperty.getEmail())
                .ifPresent(email -> dynamoProperty.setEmail(email));


        Optional.ofNullable(requestProperty.getUserName())
                .ifPresent(username -> dynamoProperty.setUserName(username));

        Optional.ofNullable(requestProperty.getName())
                .ifPresent(name -> dynamoProperty.setName(name));

        Optional.ofNullable(requestProperty.getLatitude())
                .ifPresent(latitude -> dynamoProperty.setLatitude(latitude));

        Optional.ofNullable(requestProperty.getLongitude())
                .ifPresent(longitude -> dynamoProperty.setLongitude(longitude));

        Optional.ofNullable(requestProperty.getStreet())
                .ifPresent(street -> dynamoProperty.setStreet(street));


        Optional.ofNullable(requestProperty.getCity())
                .ifPresent(city -> dynamoProperty.setCity(city));


        Optional.ofNullable(requestProperty.getCountry())
                .ifPresent(country -> dynamoProperty.setCountry(country));

        Optional.ofNullable(requestProperty.getDescription())
                .ifPresent(description -> dynamoProperty.setDescription(description));


        Optional.ofNullable(requestProperty.getWebsite())
                .ifPresent(website -> dynamoProperty.setWebsite(website));

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoProperty;
    }

    public DynamoProperty authenticate(String userName, String passwordAttempt) {
        LOGGER.info("authenticateRecptionist(): username: " + userName);
        DynamoProperty property = dynamoPropertyRepository.findOne(userName);
        try {
            if (PBKDF2.checkPassword(property, passwordAttempt)) {
                property.setLastLogin(new Date());
                ApiKey apiKey = new ApiKey();
                property.setApiToken(apiKey.getApiKey());
                property.setApiTokenExpiration(apiKey.getTime());
                String jwt = jwtService.buildJwt(apiKey.getApiKey());
                property.setApiToken(jwt);
            }
        } catch (Exception e) {
            LOGGER.error("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: authenticateReceptionist()");
            e.printStackTrace();
        }
        return property;
    }

    public boolean changePassword(String id, String userName, String password) {
        return false;
    }

    public boolean changePasswordWithToken(String email, String token) {
        return false;
    }

    public PropertyInfo getPropertyToken(String payloadFromJwt) {
        return null;
    }

}
