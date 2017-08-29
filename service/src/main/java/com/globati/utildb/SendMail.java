package com.globati.utildb;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.globati.config.Paths;
import com.globati.dbmodel.Deal;
import com.globati.mail.beans.AdReceipt;
import com.globati.mail.beans.ForgotPassword;
import com.globati.mail.beans.Recruitment;
import com.globati.mail.beans.Welcome;
import com.globati.utildb.HelpObjects.Email;
import com.globati.dbmodel.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by daniel on 12/22/16.
 *
 * image lines 627, 1599
 *
 * us this website to convert large html bits of text to strings http://pojo.sodhanalibrary.com/string.html
 */
public class SendMail {

    static final String FROM = "noreply@globati.com";  // Replace with your "From" address. This address must be verified.
    static final String TO = "daniel@globati.com"; // Replace with a "To" address. If your account is still in the
    private static final String key = "AKIAJSYT5343PVMDHCRQ";
    private static final String password = "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM";
    // sandbox, this address must be verified.


    private static final Logger log = LogManager.getLogger(SendMail.class);


    public static boolean sendGuestMail(Employee employee, List<String> mails) throws Exception {
        System.out.println("Sending guest email "+mails.get(0));

        String[] emails = new String[10];

        for(int i=0 ; i< mails.size(); i++){
            emails[i] = mails.get(i);
        }

        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

//        System.out.println(mail.getEmployee().getImage());

        // Create the subject and body of the message.

        Content subject = new Content().withData("My globati ~"+employee.getFirstName());

        Welcome welcome = new Welcome(employee.getFirstName(), employee.getCity(), employee.getEmail(), employee.getGlobatiUsername());
        Content textBody = new Content().withData(
            welcome.getWelcomeMail()
        );


        Body body = new Body().withHtml(textBody);

        // Create a message with the specified subject and body.
        com.amazonaws.services.simpleemail.model.Message message = new com.amazonaws.services.simpleemail.model.Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

        try {
            AWSCredentials credentials = new BasicAWSCredentials(
                    key,
                    password);

            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

            Region REGION = Region.getRegion(Regions.EU_WEST_1);
            client.setRegion(REGION);

            // Send the email.
            client.sendEmail(request);
            return true;
        } catch (Exception ex) {
            log.error("Email send through AWS not sent.");
            log.error("Error message: " + ex.getMessage());
            throw new Exception(ex.toString());
        }
    }

    public static boolean sendReceipt(Deal deal) throws Exception {
        log.debug("SendReceipt(deal): ");
        log.debug(deal.toString());
        String[] emails = new String[]{deal.getEmail()};


        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        // Create the subject and body of the message.
        Content subject = new Content().withData("Your receipt for advertising on globati");
        AdReceipt adReceipt = new AdReceipt(deal.getTransactionId(), new Date(), deal.getLocation(), deal.getStreet(), deal.getCity(), deal.getCountry(), Double.toString(deal.getCost()), deal.getPlan().toString());
        Content textBody = new Content().withData(
                adReceipt.getEmailText()
            );

        Body body = new Body().withHtml(textBody);

        // Create a message with the specified subject and body.
        com.amazonaws.services.simpleemail.model.Message message = new com.amazonaws.services.simpleemail.model.Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

        try {
            AWSCredentials credentials = new BasicAWSCredentials(
                    key,
                    password);

            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

            Region REGION = Region.getRegion(Regions.EU_WEST_1);
            client.setRegion(REGION);

            // Send the email.
            client.sendEmail(request);
            return true;
        } catch (Exception ex) {
            log.error("Email send through AWS not sent.");
            log.error("Error message: " + ex.getMessage());
            throw new Exception(ex.toString());
        }
    }



    public static boolean sendRecruitmentMail(Employee employee, String email, String businessName) throws Exception {

        String[] emails = new String[]{email};


        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        Recruitment recruitment = new Recruitment(businessName, employee.getFirstName(), Paths.getActiveCreateAddLink()+employee.getId());

        // Create the subject and body of the message.
        Content subject = new Content().withData(employee.getFirstName()+" is inviting "+businessName+" to develop a business partnership on globati");
        Content textBody = new Content().withData(
                   recruitment.getRecruitmentMail()
            );

        Body body = new Body().withHtml(textBody);

        // Create a message with the specified subject and body.
        com.amazonaws.services.simpleemail.model.Message message = new com.amazonaws.services.simpleemail.model.Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

        try {
            AWSCredentials credentials = new BasicAWSCredentials(
                    key,
                    password);

            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

            Region REGION = Region.getRegion(Regions.EU_WEST_1);
            client.setRegion(REGION);

            // Send the email.
            client.sendEmail(request);
            return true;
        } catch (Exception ex) {
            log.error("Email send through AWS not sent.");
            log.error("Error message: " + ex.getMessage());
            throw new Exception(ex.toString());
        }

    }

    public static boolean sendForgottenPasswordEmail(String email, String globatiuser, String apitoken) throws Exception {

        String[] emails = new String[]{email};


        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        // Create the subject and body of the message.
        Content subject = new Content().withData("Here is your Globati username, click on the link to reset your password.");
        ForgotPassword fp = new ForgotPassword(globatiuser, Paths.getActiveStaticGlobati() + "changeyourpassword/" + apitoken);

        System.out.println(fp.toString());
        Content textBody = new Content().withData(
                fp.getEmailText()
        );

        Body body = new Body().withHtml(textBody);

        // Create a message with the specified subject and body.
        com.amazonaws.services.simpleemail.model.Message message = new com.amazonaws.services.simpleemail.model.Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

        try {
            AWSCredentials credentials = new BasicAWSCredentials(
                    key,
                    password);

            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

            Region REGION = Region.getRegion(Regions.EU_WEST_1);
            client.setRegion(REGION);

            // Send the email.
            client.sendEmail(request);
            return true;
        } catch (Exception ex) {
            log.error("Email send through AWS not sent.");
            log.error("Error message: " + ex.getMessage());
            throw new Exception(ex.toString());
        }

    }



}