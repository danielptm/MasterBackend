package com.globati.resources;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.springframework.stereotype.Component;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

@Component
@Path("thirdparty")
public class ThirdPartyApi {

    @GET
    @Path("places")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaces() throws IOException {
//        InputStream is = ThirdPartyApi.class.getResourceAsStream("mycities.json");
//
//        String jsonText = IOUtils.toString(is);
//        JSONArray array = new JSONArray(jsonText);

        return Response.ok().build();
    }
}
