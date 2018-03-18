package com.globati.resources;

import com.braintreegateway.*;
import com.globati.dbmodel.Tip;
import com.globati.deserialization_beans.request.TipPayment;
import com.globati.resources.exceptions.WebException;
import com.globati.service.BraintreeService;
import com.globati.service.DealService;
import com.globati.service.EmployeeService;
import com.globati.service.PropertiesService;
import com.globati.third_party_api.BraintreeToken;
import org.glassfish.jersey.media.multipart.FormDataParam;
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
    BraintreeService braintreeService;

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
            environment = Environment.PRODUCTION;
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


    /**
     * private _title:string;
     *
     * @return
     * @throws Exception
     */


    @POST
    @Path("checkout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkout(TipPayment tipPayment) throws Exception {

        System.out.println(tipPayment.toString());

        Tip response = braintreeService.makeTransaction(
                tipPayment.getId(), tipPayment.getTipAmount(), tipPayment.getNonce(), tipPayment.getEmail());

        if(response !=null){
            return Response.ok(response).build();
        }
        else{
            throw new WebException("Could not create deal", Response.Status.CONFLICT);
        }
    }
}