package com.globati.service.dynamodb;

import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.common.DynamoImage;
import com.globati.mysql.dbmodel.PropertyInfo;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import com.globati.request.RequestProperty;
import com.globati.service_beans.guest.PropertyAndItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamoPropertyService {

    @Autowired
    DynamoPropertyRepository dynamoPropertyRepository;

    public DynamoProperty createDynamoProperty(RequestProperty requestProperty) {
        DynamoProperty dynamoProperty = new DynamoProperty();
        dynamoProperty.setEmail(requestProperty.getEmail());
        dynamoProperty.setUserName(requestProperty.getUserName());
        dynamoProperty.setName(requestProperty.getName());
        dynamoProperty.setLatitude(requestProperty.getLatitude());
        dynamoProperty.setLongitude(requestProperty.getLongitude());
        dynamoProperty.setStreet(requestProperty.getStreet());
        dynamoProperty.setCity(requestProperty.getCity());
        dynamoProperty.setCountry(requestProperty.getCountry());
        dynamoProperty.setDescription(requestProperty.getDescription());
        dynamoProperty.setWebsite(requestProperty.getWebsite());
        return dynamoPropertyRepository.save(dynamoProperty);
    }

    /**
     * Deletes the DynamoProperty and returns the id of the deleted property
     * @param id
     * @return
     */
    public String deleteDynamoProperty(String id) {
        dynamoPropertyRepository.delete(id);
        return id;
    }

    public DynamoProperty getDynamoPropertyById(String email) {
        return dynamoPropertyRepository.findOne(email);
    }

    public DynamoProperty updateDynamoProperty(RequestProperty requestProperty) {
        DynamoProperty dynamoProperty = getDynamoPropertyById(requestProperty.getEmail());
        dynamoProperty.setEmail(requestProperty.getEmail());
        dynamoProperty.setUserName(requestProperty.getUserName());
        dynamoProperty.setName(requestProperty.getName());
        dynamoProperty.setLatitude(requestProperty.getLatitude());
        dynamoProperty.setLongitude(requestProperty.getLongitude());
        dynamoProperty.setStreet(requestProperty.getStreet());
        dynamoProperty.setCity(requestProperty.getCity());
        dynamoProperty.setCountry(requestProperty.getCountry());
        dynamoProperty.setDescription(requestProperty.getDescription());
        dynamoProperty.setWebsite(requestProperty.getWebsite());
        return dynamoPropertyRepository.save(dynamoProperty);
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

    public PropertyAndItems authenticate(String username, String password) {
        return null;
    }
}
