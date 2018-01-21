package com.globati.service;

import com.globati.dbmodel.Property;
import com.globati.repository.PropertyRepository;
import com.globati.utildb.ImageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    private static final Logger log = LogManager.getLogger(PropertyService.class);


    public Property createPropertyObject(Property property){
        return propertyRepository.save(property);
    }

    public Property getPropertyByEmail(String email){
        Property property = null;
        try{
            property =  propertyRepository.getPropertyByEmail(email);
        }catch(Exception e){
            log.warn("** Globati service exception: Could not get property by the email address: "+email);
            e.printStackTrace();
        }
        return property;
    }

    public Property updateProperty(Property property){
        Property propertyToSave = null;
        try{
            propertyToSave = propertyRepository.save(property);
        }catch(Exception e){
            log.warn("** Globati service exception: Could not update a property");
            e.printStackTrace();
        }
        return propertyToSave;
    }

    public List<Property> getAllProperties(){
        List<Property> properties = null;
        try{
            properties =  propertyRepository.getAllProperties();
        }catch(Exception e){
            log.warn("** Globati service exception: Could not get all properties.");
            e.printStackTrace();
        }
        return properties;
    }

    public boolean updatePropertiesInDatabase(){
        List<Property> propertyList = ImageHandler.transformPropertyFileToPropertyObjectsAndReturnThem();
        Integer insertions = 0;

        for( Property property: propertyList ){
            Property checkedProperty = getPropertyByEmail(property.getEmail());
            if(checkedProperty == null){
                createPropertyObject(property);
                insertions++;
            }
        }

        System.out.println("There were this many properties added to the database: "+insertions);

        return true;
    }

}
