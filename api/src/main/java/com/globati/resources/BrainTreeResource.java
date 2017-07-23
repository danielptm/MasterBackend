package com.globati.resources;

import com.braintreegateway.*;
import com.globati.dbmodel.Deal;
import com.globati.resources.exceptions.WebException;
import com.globati.service.DealService;
import com.globati.service.EmployeeService;
import com.globati.utildb.SendMail;
import com.globati.webmodel.BraintreeToken;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.InputStream;
import java.math.BigDecimal;

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

    private static final Logger log = LogManager.getLogger(BrainTreeResource.class);

    private static BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            "yrs52pkkfq3sxrh7",
            "72c7qyjtntx4nknk",
            "8b8f6fc74fd65cb073e6f6cc7d892f15"
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response gettoken(){
//        throw new WebApplicationException(Response.Status.BAD_GATEWAY);
        String to = gateway.clientToken().generate();
        BraintreeToken tok = new BraintreeToken(to);
        return Response.ok(tok).build();
    }

    @POST
    @Path("checkout")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response checkout(
                            @FormDataParam("file") InputStream is,
                            @FormDataParam("title") String title,
                            @FormDataParam("description") String description,
                            @FormDataParam("businessName") String businessName,
                            @FormDataParam("website")String website,
                            @FormDataParam("category")String category,
                            @FormDataParam("plan") String plan,
                            @FormDataParam("lat") double targetLat,
                            @FormDataParam("long") double targetLong,
                            @FormDataParam("street") String street,
                            @FormDataParam("city") String city,
                            @FormDataParam("country") String country,
                            @FormDataParam("nonce") String nonce,
                            @FormDataParam("email") String email,
                            @FormDataParam("employeeId") Long id,
                            @FormDataParam("billingStreet") String billingStreet,
                            @FormDataParam("billingCity") String billingCity,
                            @FormDataParam("billingRegion") String billingRegion,
                            @FormDataParam("billingCountry") String billingCountry
    ) throws Exception {
        Deal deal = null;
        try{
            double cost=0;

            switch(plan){
                case "30days": cost =  employeeService.getEmployeeById(id).get_addAmount(); break;
                case "60days": cost = employeeService.getEmployeeById(id).get_add2month();break;
                case "90days": cost = employeeService.getEmployeeById(id).get_add3month(); break;
            }

            TransactionRequest request = new TransactionRequest()
                    .amount(new BigDecimal(cost))
                    .paymentMethodNonce(nonce)
                    .options()
                    .submitForSettlement(true)
                    .done();

            Result<Transaction> result = gateway.transaction().sale(request);

            if (result.isSuccess()) {
                String transactionId = result.getTarget().getId();
                deal = dealService.createDeal(is, title, description, businessName, targetLat, targetLong, id, country, street, city, category, website, email, plan, cost, transactionId, billingStreet, billingCity, billingRegion, billingCountry);
                return Response.ok("<!DOCTYPE html>"+
                        "<html lang=\"\">"+
                        ""+
                        "<head>"+
                        "    <meta charset=\"utf-8\">"+
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
                        "    <link href=\"https://fonts.googleapis.com/css?family=Lora|Ubuntu\" rel=\"stylesheet\">"+
                        "    <title>Globati Receipt</title>"+
                        "</head>"+
                        ""+
                        "<body>"+
                        "    <table style=\"box-sizing: border-box;  border-collapse: collapse; text-align: center; font-family: Ubuntu, sans-serif; border-top: 3px solid; border-right: 3px solid; border-left: 3px solid; border-bottom: 3px solid; table-layout: fixed; width: 1260px; height: 1782px;\">"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 24px; padding: 0 0 0 0; background-color: #DC143C;\" colspan=\"6\"><img src=\"https://i.imgsafe.org/0e6b8045fe.png\" width=624px height=272px alt=\"Globati Logo\" /></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; background-color: #DC143C;\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; background-color: #DC143C;\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; background-color: #DC143C;\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 24px; font-weight: 700; padding: 0.25em 0.5em 0.25em 0.5em; background-color: #DC143C; color: ;\" colspan=\"3\">Order Number"+
                        "                <br> "+result.getTarget().getId()+"</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 48px; padding: 2em 0.25em 0em 0.25em; background-color: #DC143C; color: #FFF;\" colspan=\"12\">Thanks for advertising with us!</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.5em 5em 0em 5em; text-align: justify; background-color: #DC143C; color: #FFF;\" colspan=\"12\"></td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 48px; font-weight: 700; padding: 1em 0.25em 1em 0.25em; background-color: #DC143C; color: #FFF;\" colspan=\"12\">I N V O I C E</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 36px; font-weight:700; padding: 1em 0.25em 1em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"12\">Order Summary</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 24px; padding: 0.25em 0.25em 0.25em 0.25em; border-bottom: 2px solid; background-color: #323333; color: #FFF;\" colspan=\"9\">Online advertisement</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 36px; padding: 0 0 0 0; background-color: #323333; color: #FFF;\" rowspan=\"2\" colspan=\"3\"></td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.25em 0.25em 0em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">Business Type</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.25em 0.25em 0em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">Validity Period</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.25em 0.25em 0em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">Cost</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; font-style: italic; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">"+deal.get_dealtype()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; font-style: italic; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">"+deal.get_plan()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; font-style: italic; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\">"+deal.get_cost()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0em 0.25em 2em 0.25em; background-color: #323333; color: #FFF;\" colspan=\"3\"></td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 36px; font-weight: 700; padding: 1em 0.25em 1em 0.25em;\" colspan=\"12\">Customer Information</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 21px; font-weight: 700; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">Location</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 21px; font-weight: 700; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">Billing Address</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">"+deal.get_location()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">"+deal.get_location()+"</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">"+deal.get_street()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">"+deal.get_billingStreet()+"</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 0.25em 0.25em;\" colspan=\"6\">"+deal.get_billingRegion()+"</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 1em 0.25em;\" colspan=\"6\">"+deal.get_country()+"</td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 1em 0.25em; border-bottom: 1px dashed;\" colspan=\"6\">"+deal.get_country()+"</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px;\" colspan=\"6\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.75em 0.25em 0.25em 0.25em;\" colspan=\"6\">You will also get a receipt with the transaction details by our payment partner Braintree</td>"+
                        "        </tr>"+
                        "        <tr>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px;\" colspan=\"6\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 18px; padding: 0.75em 0.25em 0.25em 0.25em;\" colspan=\"6\">Contact "+deal.get_employee().get_firstName()+" at "+deal.get_employee().get_email()+" if you have any questions</td>"+
                        "        </tr>"+
                        "        <tr style=\"border-bottom: 3px solid;\">"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px;\" colspan=\"6\"></td>"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 15px; padding: 0.25em 0.25em 2em 0.25em;\" colspan=\"6\"></td>"+
                        "        </tr>"+
                        "        <tr style=\"background-color: #323333; color: #FFF;\">"+
                        "            <td style=\"font-family: 'Ubuntu', sans serif; font-size: 12px; font-weight: 700; padding: 1em 0.25em 1em 0.25em;\" colspan=\"12\">© 2017 Globati. All rights reserved.</td>"+
                        "        </tr>"+
                        "    </table>"+
                        "</body>"+
                        ""+
                        "</html>"
                , "text/html"

                ).build();

            } else {
                throw new WebException("Financial transaction failed", Response.Status.NOT_ACCEPTABLE);
            }

        }catch(Exception e){
            throw new WebException("Could not create deal", Response.Status.BAD_REQUEST);
        }finally{
            SendMail.sendReceipt(deal);
        }

    }
}