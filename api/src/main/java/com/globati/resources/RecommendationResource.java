package com.globati.resources;

import com.globati.dbmodel.Recommendation;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.EmployeeService;
import com.globati.service.RecommendationService;
import com.globati.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.InputStream;
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
    RecommendationService recommendationService;

    @Autowired
    EmployeeService employeeService;

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
    public Response getRecommendationsByEmployee(@QueryParam("id") Long id){
        try{
            List<Recommendation> recommendationList = recommendationService.getRecommendationByEmployeeId(id);
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
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) throws ServiceException {
        try{
            recommendationService.inactivateRecommendation(id);
            return Response.ok().build();
        }catch(Exception e){
            throw new WebException("Could not update recommendation", Response.Status.CONFLICT);
        }
    }

    /**
     *
     * Refactoring:
     * This is ok, but I could return the UriInfo, and build the path to the created image, return that
     * And then use that in a get request on the angular side.
     *
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(com.globati.webmodel.Recommendation recommendation){
        try{
            System.out.println(recommendation);
             Recommendation returnRecommendation = recommendationService.createRecommendation(recommendation.getEmployeeId(),
                    recommendation.getTitle(),
                    recommendation.getDescription(),
                    recommendation.getTargetLat(),
                    recommendation.getTargetLong(),
                    recommendation.getStreet(),
                    recommendation.getCity(),
                    recommendation.getCountry(),
                    recommendation.getImage1(),
                    recommendation.getImage2(),
                    recommendation.getImage3()
            );
//            recommendationService.createRecommendation(id, title, description, targetLat, targetLong, street, city, country, is);
            return Response.ok(returnRecommendation).build();
        }catch(Exception e){
            throw new WebException("Could not create new recommendation", Response.Status.CONFLICT);
        }
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") Long id,  com.globati.webmodel.Recommendation recommendation){
//        System.out.println("updateRecommendation()");
//        System.out.println(recommendation);
        try{
            Recommendation returnRecommendation = recommendationService.getRecommendationById(id);
            returnRecommendation.setDescription(recommendation.getDescription());
            returnRecommendation.setImage(recommendation.getImage1());
            returnRecommendation.setImage2(recommendation.getImage2());
            returnRecommendation.setImage3(recommendation.getImage3());
            returnRecommendation.setTitle(recommendation.getTitle());
            recommendationService.updateRecommendation(returnRecommendation);
            return Response.ok(returnRecommendation).build();
        }catch(Exception e){
            throw new WebException("Could not update new recommendation", Response.Status.CONFLICT);
        }
    }
}
