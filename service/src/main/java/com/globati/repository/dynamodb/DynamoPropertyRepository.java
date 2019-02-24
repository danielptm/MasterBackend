package com.globati.repository.dynamodb;

import com.globati.dynamodb.DynamoProperty;
import org.springframework.data.repository.CrudRepository;

public interface DynamoPropertyRepository extends CrudRepository<DynamoProperty, String>{

}
