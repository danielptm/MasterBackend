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


    public static boolean sendGuestMail(Email mail) throws Exception {

        String[] emails = new String[10];

        for(int i=0 ; i< mail.getEmails().size(); i++){
            emails[i] = mail.getEmails().get(i);
        }

        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        System.out.println(mail.getEmployee().get_image());

        // Create the subject and body of the message.
        Content subject = new Content().withData("My globati ~"+mail.getEmployee().get_firstName());

        Welcome welcome = new Welcome(mail.getEmployee().get_firstName(), mail.getEmployee().get_city(), mail.getEmployee().get_email(), mail.getEmployee().get_globatiUsername());
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
        String[] emails = new String[]{deal.get_email()};


        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emails);

        // Create the subject and body of the message.
        Content subject = new Content().withData("Your receipt for advertising on globati");
        AdReceipt adReceipt = new AdReceipt(deal.get_transactionId(), new Date(), deal.get_location(), deal.get_street(), deal.get_city(), deal.get_country(), Double.toString(deal.get_cost()), deal.get_plan() );
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

        Recruitment recruitment = new Recruitment(businessName, employee.get_firstName(), Paths.getActiveCreateAddLink()+employee.get_id());

        // Create the subject and body of the message.
        Content subject = new Content().withData(employee.get_firstName()+" is inviting "+businessName+" to develop a business partnership on globati");
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