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
import com.globati.mail.beans.ForgotPassword;
import com.globati.mail.beans.Welcome;
import com.globati.service.PropertiesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by daniel on 12/22/16.
 *
 * image lines 627, 1599
 *
 * us this website to convert large html bits of text to strings http://pojo.sodhanalibrary.com/string.html
 */
public class SendMail {

    @Autowired
    public static PropertiesService properties;

    static final String FROM = "noreply@globati.com";  // Replace with your "From" address. This address must be verified.
    static final String TO = "daniel@globati.com"; // Replace with a "To" address. If your account is still in the
    private static final String key = "AKIAJSYT5343PVMDHCRQ";
    private static final String password = "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM";
    // sandbox, this address must be verified.


    private static final Logger log = LogManager.getLogger(SendMail.class);


    public static boolean sendForgottenPasswordEmail(String email, String globatiuser, String apitoken) throws Exception {

        String[] emails = new String[]{email};

        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        // Create the subject and body of the message.
        Content subject = new Content().withData("Here is your Globati username, click on the link to reset your password.");

        //This used the properties service before, but started reutrning null pointer exception, as a workaround i hardcoded the link. As of now
        // the only way to test this is in production with this solution.
        ForgotPassword fp = new ForgotPassword(globatiuser, "https://admin.globati.com/changeyourpassword/" + apitoken);

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

    public static boolean sendCustomMailToGlobatiStaff(String email, String contentMessage) throws Exception {
        String[] emails = new String[]{email};


        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        // Create the subject and body of the message.
        Content subject = new Content().withData("Email from server to Globati AB: This is an automated email sent from the globati server.");

        Content textBody = new Content().withData(
                contentMessage
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



    public static boolean sendInterestMail(String email, String contentMessage) throws Exception {
        String[] emails = new String[]{email};


        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        // Create the subject and body of the message.
        Content subject = new Content().withData("** Interest mail from globati.com **");

        Content textBody = new Content().withData(
                contentMessage
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

    public static boolean sendHelpRecommendationPromp(String username, String email, String contentMessage) throws Exception {
        String[] emails = new String[]{email};


        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        // Create the subject and body of the message.
        Content subject = new Content().withData(username + " has low amount of content on the Globati profile");

        Content textBody = new Content().withData(
                contentMessage
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

    public static boolean sendGlobatiReminder(String username, String email, String contentMessage) throws Exception {
        String[] emails = new String[]{email};


        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        // Create the subject and body of the message.
        Content subject = new Content().withData(username + "'s recommendations are on the Globati app.");

        Content textBody = new Content().withData(
                contentMessage
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