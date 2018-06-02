package com.globati.resources;

import com.globati.deserialization_beans.request.HelpRecommendation;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.service.HelpRecommendationService;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;

@Component
@Path("help-recommendation")
@GlobatiAuthentication
public class HelpRecommendationResource {

    @Autowired
    HelpRecommendationService helpRecommendationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ArrayList<HelpRecommendation> recommendations) throws Exception {
        com.globati.dbmodel.HelpRecommendation helpRecommendationFromdb = null;
        ArrayList<com.globati.dbmodel.HelpRecommendation> recommendations1 = new ArrayList<>();
        try {
            for(HelpRecommendation rec: recommendations) {
                rec.setDate(new Date());
                helpRecommendationFromdb = helpRecommendationService.createHelpRecommendation(rec.getId(), rec.getTitle(), rec.getDescription(), rec.getDate());
                recommendations1.add(helpRecommendationFromdb);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return Response.ok(recommendations1).build();
    }
}
