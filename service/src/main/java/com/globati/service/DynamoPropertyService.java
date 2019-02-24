package com.globati.service;

import com.globati.dynamodb.DynamoProperty;
import com.globati.repository.dynamodb.DynamoPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamoPropertyService {

    @Autowired
    DynamoPropertyRepository dynamoPropertyRepository;

    public DynamoProperty createDynamoProperty(DynamoProperty dynamoProperty) {
        return dynamoPropertyRepository.save(dynamoProperty);
    }
}
