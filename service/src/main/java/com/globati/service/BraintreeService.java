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

    private static Environment environment;

    static {
        merchantId= Paths.getMerchantId();
        publicKey = Paths.getPublicKey();
        privateKey = Paths.getPrivateKey();
        environment = Paths.getBraintreeEnvironment();
    }



    @Autowired
    EmployeeService employeeService;

    @Autowired
    DealService dealService;

    private static BraintreeGateway gateway = new BraintreeGateway(
            environment,
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
        log.info("***");
        log.info("***");
        log.info("Making braintree transaction: ");
        log.info("***");
        log.info("image1 " + image1);
        log.info("image2 " + image2);
        log.info("image3 " + image3);
        log.info("title " + title);
        log.info("description " + description);
        log.info("businessName " + businessName);
        log.info("website " + website);
        log.info("category " + category);
        log.info("plan " + plan);
        log.info("lat " + targetLat);
        log.info("long " + targetLong);
        log.info("street " + street);
        log.info("city " + city);
        log.info("country " + country);
        log.info("nonce " + nonce);
        log.info("email " + email);
        log.info("employeeId " + id);
        log.info("billingStreet " + billingStreet);
        log.info("billingCity " + billingCity);
        log.info("billingRegion " + billingRegion);
        log.info("billingCountry " + billingCountry);
        log.info("cost " + cost);
        log.info("***");
        log.info("***");


        Employee employee = employeeService.getEmployeeById(id);
        Deal createdDeal = null;

            TransactionRequest request = new TransactionRequest()
                    .amount(new BigDecimal(cost))
                    .paymentMethodNonce(nonce)
                    .options()
                    .submitForSettlement(true)
                    .done();

            Result<Transaction> result = gateway.transaction().sale(request);

            log.info("Braintree transaction success: " + result.isSuccess());

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

                return receipt.getEmailText();

            } else {
                return null;
            }
    }
}