package com.globati.resources;

import com.braintreegateway.*;
import com.globati.config.Paths;
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
public class BrainTreeResource {

    @Autowired
    DealService dealService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    BraintreeService braintreeService;


    private static String merchantId=null;

    private static String publicKey=null;

    private static String privateKey=null;

    private static Environment environment=null;

    static {
        merchantId= Paths.getMerchantId();
        publicKey = Paths.getPublicKey();
        privateKey = Paths.getPrivateKey();
        environment = Paths.getBraintreeEnvironment();

    }

    private static BraintreeGateway gateway = new BraintreeGateway(
            environment,
            merchantId,
            publicKey,
            privateKey
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response gettoken() {
//        throw new WebApplicationException(Response.Status.BAD_GATEWAY);
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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response checkout(
            @FormDataParam("image1") String image1,
            @FormDataParam("image2") String image2,
            @FormDataParam("image3") String image3,
            @FormDataParam("title") String title,
            @FormDataParam("description") String description,
            @FormDataParam("businessName") String businessName,
            @FormDataParam("website") String website,
            @FormDataParam("category") String category,
            @FormDataParam("plan") String plan,
            @FormDataParam("lat") Double targetLat,
            @FormDataParam("long") Double targetLong,
            @FormDataParam("street") String street,
            @FormDataParam("city") String city,
            @FormDataParam("country") String country,
            @FormDataParam("nonce") String nonce,
            @FormDataParam("email") String email,
            @FormDataParam("employeeId") Long id,
            @FormDataParam("billingStreet") String billingStreet,
            @FormDataParam("billingCity") String billingCity,
            @FormDataParam("billingRegion") String billingRegion,
            @FormDataParam("billingCountry") String billingCountry,
            @FormDataParam("cost") Double cost
    ) throws Exception {
        String response = braintreeService.makeTransaction(
                image1, image2, image3,
                title,description,businessName,
                website, category, plan, targetLat,
                targetLong, street, city, country,
                nonce, email, id, billingStreet, billingCity,
                billingRegion, billingCountry, cost
        );

        if(response !=null){
            return Response.ok(response).build();
        }
        else{
            throw new WebException("Could not create deal", Response.Status.CONFLICT);
        }
    }
}