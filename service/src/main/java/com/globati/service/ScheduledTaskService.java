package com.globati.service;

import com.globati.dbmodel.Property;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.DateTools;
import com.globati.utildb.ImageHandler;
import com.globati.utildb.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.globati.mail.beans.WelcomeToGlobati;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ScheduledTaskService {

    @Autowired
    PropertyService propertyService;

    //http://pojo.sodhanalibrary.com/string.html
    @Scheduled(cron = "0 30 3 * * ?")
    public void sendMarketingMail() throws Exception {
        System.out.println("Sending the marketing mail");
        List<Property> properties = propertyService.getAllProperties();
        StringBuilder propertyBuilder = new StringBuilder("Automated marketing mail was sent to the following properties: ");
        Integer numberOfSentMails = 0;

        for(Property property: properties){
            if( property.getDate() == null || ! DateTools.isWithinTheLast30Days(property.getDate()) ) {
                propertyBuilder.append(property.getPropertyName()+" ");
                propertyBuilder.append(property.getEmail()+", ");
//                System.out.println("Fake sending an email: "+property.getEmail());
                SendMail.sendWelcomeToGlobatiMail(property.getEmail(), WelcomeToGlobati.getMail());
                property.setDate(new Date());
                propertyService.updateProperty(property);
                numberOfSentMails++;
            }
        }

        if(numberOfSentMails > 0){
            SendMail.sendCustomMailToGlobatiStaff("danie@globati.com", propertyBuilder.toString());
        }
        if(numberOfSentMails == 0){
            SendMail.sendCustomMailToGlobatiStaff("daniel@globati.com", "The sendMarketingMail function was run, but no emails were sent because all properties have received a mail within the last 30 days.");
        }


    }

    @Scheduled(cron = "0 15 3 * * ?")
    public void updatePropertiesInDatabase() {
        propertyService.updatePropertiesInDatabase();
    }



}
