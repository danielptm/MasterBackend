package com.globati.resources;

import com.braintreegateway.*;
import com.globati.service.DealService;
import com.globati.service.EmployeeService;
import com.globati.service.PropertiesService;
import com.globati.third_party_api.BraintreeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Created by daniel on 1/5/17.
 *
 * http://pojo.sodhanalibrary.com/string.html
 *
 * Refactoring:
 * ok
 *
 *
 */

@Component
@Path("braintree")
public class BraintreeResource {

    @Autowired
    DealService dealService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PropertiesService propertiesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response gettoken() {
        String merchantId = propertiesService.getMerchantId();
        String publicKey = propertiesService.getPublicKey();
        String privateKey = propertiesService.getPrivateKey();

        Environment environment = null;

        if(System.getenv("GLOBATI_SERVER_ENV").equals("development")){
            environment = Environment.SANDBOX;
        }
        else{
            System.out.println("** Setting braintree environment to production **");
            environment = Environment.PRODUCTION;
            System.out.println(environment.toString());
        }

        BraintreeGateway gateway = new BraintreeGateway(
                environment,
                merchantId,
                publicKey,
                privateKey
        );

        String to = gateway.clientToken().generate();
        BraintreeToken tok = new BraintreeToken(to);
        return Response.ok(tok).build();
    }

}