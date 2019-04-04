package com.globati.resources;

import com.globati.dynamodb.DynamoProperty;
import com.globati.dynamodb.DynamoRecommendation;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.dynamodb.DynamoRecommendationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by daniel on 12/13/16.
 *
 * Refactoring for this class
 * Objects in parameters belong to the model of this layer.
 *
 *
 * Needs work:
 * Could return the UriInfo when creating a recommendation, this could improve performance,
 * because now all recommendations are returned.
 * Otherwise this class is ok
 *
 *
 */

@Component
@Path("recommendations")
@GlobatiAuthentication
public class RecommendationResource {

    private static final Logger log = LogManager.getLogger(RecommendationResource.class);

    @Autowired
    DynamoRecommendationService recommendationService;

    @Context
    UriInfo uriInfo;

    /**
     * Gets all recommendations for an employee by that employees name
     *
     * Refactoring:
     * ok
     *
     * @param id
     * @return
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecommendationsByEmployee(@QueryParam("id") String id){
        try{
            List<DynamoRecommendation> recommendationList = recommendationService.getRecommendationsByEmployeeEmail(id);
            return Response.ok(recommendationList).build();
        }catch(Exception e){
            throw new WebException("Could not receive recommendations for employee", Response.Status.BAD_REQUEST);
        }
    }


    /**
     * Used by myglobatiadmin to delete a recommendation
     *
     * Refactoring:
     *ok
     *
     * @param
     * @return
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{email}/{id}")
    public Response delete(@PathParam("email") String email, @PathParam("id") String id) {
        try{
            recommendationService.deleteRecommendation(email, id);
            return Response.ok().build();
        }catch(Exception e){
            throw new WebException("Could not update recommendation", Response.Status.CONFLICT);
        }
    }

    /**
     *
     * Refactoring:
     * This is ok, but I could return the UriInfo, and build the path to the created image, return that
     * And then use that in a get api on the angular side.
     *
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(com.globati.api.Recommendation recommendation){
        try{
             DynamoProperty dynamoProperty = recommendationService.createRecommendation(recommendation);
            return Response.ok(dynamoProperty).build();
        }catch(Exception e){
            throw new WebException("Could not create new recommendation", Response.Status.CONFLICT);
        }
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") Long id,  com.globati.api.Recommendation recommendation){
        try{
            DynamoProperty dynamoProperty = recommendationService.updateRecommendation(recommendation);
            return Response.ok(dynamoProperty).build();
        }catch(Exception e){
            throw new WebException("Could not update new recommendation", Response.Status.CONFLICT);
        }
    }
}
