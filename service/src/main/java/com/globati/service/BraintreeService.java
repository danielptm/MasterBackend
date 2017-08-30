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
        log.debug("Making braintree transaction: ");
        log.debug("image1 " + image1);
        log.debug("image2 " + image2);
        log.debug("image3 " + image3);
        log.debug("title " + title);
        log.debug("description " + description);
        log.debug("businessName " + businessName);
        log.debug("website " + website);
        log.debug("category " + category);
        log.debug("plan " + plan);
        log.debug("lat " + targetLat);
        log.debug("long " + targetLong);
        log.debug("street " + street);
        log.debug("city " + city);
        log.debug("country " + country);
        log.debug("nonce " + nonce);
        log.debug("email " + email);
        log.debug("employeeId " + id);
        log.debug("billingStreet " + billingStreet);
        log.debug("billingCity " + billingCity);
        log.debug("billingRegion " + billingRegion);
        log.debug("billingCountry " + billingCountry);
        log.debug("cost " + cost);


        Employee employee = employeeService.getEmployeeById(id);
        Deal createdDeal = null;

            TransactionRequest request = new TransactionRequest()
                    .amount(new BigDecimal(cost))
                    .paymentMethodNonce(nonce)
                    .options()
                    .submitForSettlement(true)
                    .done();

            Result<Transaction> result = gateway.transaction().sale(request);

            log.debug("Braintree transactino success: " + result.isSuccess());

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


                log.debug("transaction receipt:");
                log.debug(receipt.toString());
                return receipt.getEmailText();

            } else {
                return null;
            }
    }
}