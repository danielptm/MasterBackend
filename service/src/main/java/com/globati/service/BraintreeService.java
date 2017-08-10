package com.globati.service;

import com.braintreegateway.*;
import com.globati.config.Paths;
import com.globati.dbmodel.Deal;
import com.globati.dbmodel.Employee;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.GlobatiUtilException;
import com.globati.utildb.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.globati.mail.beans.*;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class BraintreeService {

    private static final Logger log = LogManager.getLogger(BraintreeService.class);

    private static String merchantId=null;

    private static String publicKey=null;

    private static String privateKey=null;

    static {
        merchantId= Paths.getMerchantId();
        publicKey = Paths.getPublicKey();
        privateKey = Paths.getPrivateKey();
    }



    @Autowired
    EmployeeService employeeService;

    @Autowired
    DealService dealService;

    private static BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            merchantId,
            publicKey,
            privateKey
    );

    /**
     * This needs to be refactored so that the html is not in here with the code.
     * @param image1
     * @param image2
     * @param image3
     * @param title
     * @param description
     * @param businessName
     * @param website
     * @param category
     * @param plan
     * @param targetLat
     * @param targetLong
     * @param street
     * @param city
     * @param country
     * @param nonce
     * @param email
     * @param id
     * @param billingStreet
     * @param billingCity
     * @param billingRegion
     * @param billingCountry
     * @param cost
     * @return
     * @throws Exception
     */

    public String makeTransaction(
            String image1,
            String image2,
            String image3,
            String title,
            String description,
            String businessName,
            String website,
            String category,
            String plan,
            Double targetLat,
            Double targetLong,
            String street,
            String city,
            String country,
            String nonce,
            String email,
            Long id,
            String billingStreet,
            String billingCity,
            String billingRegion,
            String billingCountry,
            Double cost
    ) throws Exception {
        System.out.println("image1 " + image1);
        System.out.println("image2 " + image2);
        System.out.println("image3 " + image3);
        System.out.println("title " + title);
        System.out.println("description " + description);
        System.out.println("businessName " + businessName);
        System.out.println("website " + website);
        System.out.println("category " + category);
        System.out.println("plan " + plan);
        System.out.println("lat " + targetLat);
        System.out.println("long " + targetLong);
        System.out.println("street " + street);
        System.out.println("city " + city);
        System.out.println("country " + country);
        System.out.println("nonce " + nonce);
        System.out.println("email " + email);
        System.out.println("employeeId " + id);
        System.out.println("billingStreet " + billingStreet);
        System.out.println("billingCity " + billingCity);
        System.out.println("billingRegion " + billingRegion);
        System.out.println("billingCountry " + billingCountry);
        System.out.println("cost " + cost);


        Employee employee = employeeService.getEmployeeById(id);
        Deal createdDeal = null;

//            double cost=0;
//
//            switch(plan){
//                case "THIRTY_DAYS": cost =  employee.get_addAmount(); break;
//                case "SIXTY_DAYS": cost = employee.get_add2month();break;
//                case "NINETY_DAYS": cost = employee.get_add3month(); break;
//                default : cost=10;
//            }

            TransactionRequest request = new TransactionRequest()
                    .amount(new BigDecimal(cost))
                    .paymentMethodNonce(nonce)
                    .options()
                    .submitForSettlement(true)
                    .done();

            Result<Transaction> result = gateway.transaction().sale(request);

            System.out.println("Braintree transactino success: " + result.isSuccess());

            if (result.isSuccess()) {
                String transactionId = result.getTarget().getId();
                createdDeal = dealService.createDeal(
                        image1, image2,
                        image3, title, description, businessName,
                        targetLat, targetLong, id, country, street, city,
                        category, website, email, plan, cost,
                        transactionId, billingStreet, billingCity,
                        billingRegion, billingCountry
                );

                SendMail.sendReceipt(createdDeal);

                return new AdReceipt(transactionId, new Date(), businessName, street, city, country,cost.toString(), plan).getEmailText();

//                return "<!DOCTYPE html>" +
//                        "<html lang=\"\">" +
//                        "" +
//                        "<head>" +
//                        "    <meta charset=\"utf-8\">" +
//                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
//                        "    <link href=\"https://fonts.googleapis.com/css?family=Lora|Ubuntu\" rel=\"stylesheet\">" +
//                        "    <title>Globati Receipt</title>" +
//                        "</head>" +
//                        "" +
//                        "<body>" +
//                        "    <table style=\"box-sizing: border-box;  border-collapse: collapse; text-align: center; font-family: Ubuntu, sans-serif; border-top: 3px solid; border-right: 3px solid; border-left: 3px solid; border-bottom: 3px solid; table-layout: fixed; width: 1260px; height: 1782px;\">" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 24px; padding: 0 0 0 0; background-color: #DC143C;\" colspan=\"6\"><img src=\"https://s3.eu-central-1.amazonaws.com/globatiimages/splash/Logo_and_Name.png\" width=624px height=272px alt=\"Globati Logo\" /></td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; background-color: #DC143C;\"></td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; background-color: #DC143C;\"></td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; background-color: #DC143C;\"></td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 24px; font-weight: 700; padding: 0.25em 0.5em 0.25em 0.5em; background-color: #DC143C; color: ;\" colspan=\"3\">Order Number" +
//                        "                <br> " + result.getTarget().getId() + "</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 48px; padding: 2em 0.25em 0em 0.25em; background-color: #DC143C; color: #FFF;\" colspan=\"12\">Thanks for advertising with us!</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.5em 5em 0em 5em; text-align: justify; background-color: #DC143C; color: #FFF;\" colspan=\"12\"></td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 48px; font-weight: 700; padding: 1em 0.25em 1em 0.25em; background-color: #DC143C; color: #FFF;\" colspan=\"12\">I N V O I C E</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 36px; font-weight:700; padding: 1em 0.25em 1em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"12\">Order Summary</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 24px; padding: 0.25em 0.25em 0.25em 0.25em; border-bottom: 2px solid; background-color: #323333; color: #FFF;\" colspan=\"9\">Online advertisement</td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 36px; padding: 0 0 0 0; background-color: #323333; color: #FFF;\" rowspan=\"2\" colspan=\"3\"></td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.25em 0.25em 0em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">Business Type</td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.25em 0.25em 0em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">Validity Period</td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.25em 0.25em 0em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">Cost</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; font-style: italic; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">" + category + "</td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; font-style: italic; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">" + plan + "</td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; font-style: italic; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">" + cost + "</td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\"></td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 36px; font-weight: 700; padding: 1em 0.25em 1em 0.25em;\" colspan=\"12\">Customer Information</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 21px; font-weight: 700; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">Location</td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 21px; font-weight: 700; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">Billing Address</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">" + businessName + "</td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">" + businessName + "</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">" + street + "</td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">" + street + "</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\"></td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">" + billingRegion + "</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 1em 0.25em;\" colspan=\"6\">" + country + "</td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 1em 0.25em; border-bottom: 1px dashed;\" colspan=\"6\">" + country + "</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px;\" colspan=\"6\"></td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.75em 0.25em 0.25em 0.25em;\" colspan=\"6\">You will also get a receipt with the transaction details by our payment partner Braintree</td>" +
//                        "        </tr>" +
//                        "        <tr>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px;\" colspan=\"6\"></td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.75em 0.25em 0.25em 0.25em;\" colspan=\"6\">Contact " + employee.get_firstName() + " at " + employee.get_email() + " if you have any questions</td>" +
//                        "        </tr>" +
//                        "        <tr style=\"border-bottom: 3px solid;\">" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px;\" colspan=\"6\"></td>" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 2em 0.25em;\" colspan=\"6\"></td>" +
//                        "        </tr>" +
//                        "        <tr style=\"background-color: #323333; color: #FFF;\">" +
//                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; font-weight: 700; padding: 1em 0.25em 1em 0.25em;\" colspan=\"12\">© 2017 Globati. All rights reserved.</td>" +
//                        "        </tr>" +
//                        "    </table>" +
//                        "</body>" +
//                        "" +
//                        "</html>";
            } else {
                return null;
            }
    }
}