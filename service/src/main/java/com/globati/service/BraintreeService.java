package com.globati.service;

import com.braintreegateway.*;
import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Tip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BraintreeService {

    private static final Logger log = LogManager.getLogger(BraintreeService.class);

    private static String merchantId="yrs52pkkfq3sxrh7";

    private static String publicKey="72c7qyjtntx4nknk";

    private static String privateKey="8b8f6fc74fd65cb073e6f6cc7d892f15";

    private static Environment environment = Environment.SANDBOX;



    @Autowired
    EmployeeService employeeService;

    @Autowired
    DealService dealService;

    @Autowired
    TipService tipService;

    @Autowired
    PropertiesService propertiesService;




    /**
     * @return
     * @throws Exception
     */

    public Tip makeTransaction(
            Long id,
            Double tipPayment,
            String nonce,
            String email
    ) throws Exception {

        if (System.getenv("GLOBATI_SERVER_ENV").equals("production")){
            environment = Environment.PRODUCTION;
            System.out.println("** Setting braintree environment to production **");
            System.out.println(environment.toString());
        }

        merchantId = propertiesService.getMerchantId();
        publicKey = propertiesService.getPublicKey();
        privateKey = propertiesService.getPrivateKey();


        BraintreeGateway gateway = new BraintreeGateway(
                environment,
                merchantId,
                publicKey,
                privateKey
        );


        Employee employee = employeeService.getEmployeeById(id);
        Tip createdTip = null;

            TransactionRequest request = new TransactionRequest()
                    .amount(new BigDecimal(tipPayment))
                    .paymentMethodNonce(nonce)
                    .options()
                    .submitForSettlement(true)
                    .done();

            Result<Transaction> result = gateway.transaction().sale(request);

            log.info("Braintree transaction success: " + result.isSuccess());

            if (result.isSuccess()) {
                log.info("Braintree payment success:");
                log.info(result.getMessage());
                String transactionId = result.getTarget().getId();
                createdTip = tipService.createTip(id, transactionId, tipPayment, email );
                return createdTip;

            } else {
                log.info("Braintree payment not success:");
                log.info(result.getMessage());
                return null;
            }
    }
}