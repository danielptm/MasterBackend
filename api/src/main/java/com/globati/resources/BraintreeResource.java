package com.globati.resources;

import com.braintreegateway.*;
import com.globati.dbmodel.Tip;
import com.globati.deserialization_beans.request.TipPayment;
import com.globati.resources.exceptions.WebException;
import com.globati.service.BraintreeService;
import com.globati.service.DealService;
import com.globati.service.EmployeeService;
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


    private static String merchantId="yrs52pkkfq3sxrh7";

    private static String publicKey="72c7qyjtntx4nknk";

    private static String privateKey="8b8f6fc74fd65cb073e6f6cc7d892f15";

    private static Environment environment = Environment.SANDBOX;

    private static BraintreeGateway gateway = new BraintreeGateway(
            environment,
            merchantId,
            publicKey,
            privateKey
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response gettoken() {
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