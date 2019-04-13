package com.globati.service.dynamodb;

import com.globati.HelpObjects.ApiKey;
import com.globati.dynamodb.DynamoProperty;
import com.globati.exceptions.ServiceException;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import com.globati.api.RequestProperty;
import com.globati.service.JwtService;
import com.globati.service.PropertiesService;
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
    PropertiesService propertiesService;

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

        Optional.ofNullable(requestProperty.getMainImage())
                .ifPresent(image -> dynamoProperty.setMainImage(image));

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

        Optional.ofNullable(requestProperty.getMainImage())
                .ifPresent(image -> dynamoProperty.setMainImage(image));

        dynamoPropertyRepository.save(dynamoProperty);

        return dynamoProperty;
    }

    // TODO: Make a test for this sending in the right username but bad password.
    public DynamoProperty authenticate(String userName, String passwordAttempt) {
        LOGGER.info("authenticateRecptionist(): username: " + userName);
        DynamoProperty propertyToAuthenticate = null;
        DynamoProperty authenticatedProperty = null;
        try {
            propertyToAuthenticate = dynamoPropertyRepository.findOne(userName);
            if (PBKDF2.checkPassword(propertyToAuthenticate, passwordAttempt)) {
                propertyToAuthenticate.setLastLogin(new Date());
                ApiKey apiKey = new ApiKey();
                propertyToAuthenticate.setApiTokenExpiration(apiKey.getTime());
                String jwt = jwtService.buildJwt(apiKey.getApiKey());
                propertyToAuthenticate.setApiToken(jwt);
                dynamoPropertyRepository.save(propertyToAuthenticate);
                authenticatedProperty = propertyToAuthenticate;
            }
        } catch (Exception e) {
            LOGGER.error("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: authenticateReceptionist()");
            e.printStackTrace();
        }
        return authenticatedProperty;
    }

    public boolean changePassword(String userEmail, String password, String passwordRepeat) throws ServiceException {
        LOGGER.info("chnagePassword(): employeeEmail: " + userEmail);
        try {
            DynamoProperty dynamoProperty = dynamoPropertyRepository.findOne(userEmail);
            if(password.equals(passwordRepeat)) {
                if (PBKDF2.checkPassword(dynamoProperty, password)) {
                    DynamoProperty updatedProperty = PBKDF2.hashPropertyPassword(dynamoProperty, password);
                    dynamoPropertyRepository.save(updatedProperty);
                    return true;
                } else {
                    return false;
                }
            } else {
                String message = "The passwords did not match eachother. password: " + password + "passwordReapeat: "+ passwordRepeat;
                LOGGER.error(message);
                throw new Exception(message);
            }
        } catch (Exception e) {
            LOGGER.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: changePassword()");
            e.printStackTrace();
            throw new ServiceException("Could not change password at this time. ", e);
        }
    }

    public boolean changePasswordWithToken(String email, String token, String oldPassword, String passwordRepeated) throws ServiceException {
        LOGGER.info("changePasswordWithToken(): token: " + token);
        try {
            DynamoProperty propertyInfo = dynamoPropertyRepository.findOne(email);
            if (oldPassword.equals(passwordRepeated)) {
                if (Long.parseLong(propertyInfo.getApiToken()) > System.currentTimeMillis()) {
                    DynamoProperty dynamoProperty = PBKDF2.hashPropertyPassword(propertyInfo, oldPassword);
                    dynamoPropertyRepository.save(dynamoProperty);
                    return true;
                } else {
                    throw new Exception();
                }
            } else {
                String message = "The passwords did not match eachother. password: " + oldPassword + "passwordReapeat: "+ passwordRepeated;
                LOGGER.error(message);
                throw new Exception(message);
            }
        } catch (Exception e) {
            LOGGER.warn("** GLOBATI SERVICE EXCEPTION ** FOR METHOD: changePasswordWithToken()");
            e.printStackTrace();
            throw new ServiceException("Could not change password at this time ", e);
        }
    }

    public DynamoProperty getPropertyToken(String email, String payloadFromJwt) {
        return null;
    }
}
