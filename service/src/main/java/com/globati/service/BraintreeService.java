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



                AdReceipt receipt = new AdReceipt(transactionId, new Date(), businessName, street, city, country,cost.toString(), plan);


                System.out.println("****** receipt");
                    System.out.println(receipt.toString());
                return receipt.getEmailText();

            } else {
                return null;
            }
    }
}