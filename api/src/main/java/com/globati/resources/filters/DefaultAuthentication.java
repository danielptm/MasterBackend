package com.globati.resources.filters;

import com.globati.dynamodb.DynamoProperty;
import com.globati.mysql.dbmodel.PropertyInfo;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.dynamodb.DynamoPropertyService;
import com.globati.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by daniel on 12/25/16.
 */

@GlobatiAuthentication
@Provider
public class DefaultAuthentication implements ContainerRequestFilter {

    @Autowired
    DynamoPropertyService propertyService;


    @Autowired
    JwtService jwtService;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String clientapikey1 = requestContext.getHeaders().get("Authorization").get(0);

        String[] parts = clientapikey1.split(":");

        DynamoProperty dynamoProperty = null;

        try {
            dynamoProperty = propertyService.getDynamoPropertyById(parts[1]);
        }catch(Exception e){
            throw new WebException("Could not get employee by auth token", Response.Status.UNAUTHORIZED);
        }

        if(! jwtService.getPayloadFromJwt(parts[0]).equals(dynamoProperty.getApiToken()) ){
            throw new WebException("The user needs to authenticate themselves", Response.Status.UNAUTHORIZED);
        }

        if(Long.parseLong(dynamoProperty.getApiTokenExpiration())< System.currentTimeMillis() ){
            throw new WebException("The token is expired, the user needs to log in to get a new token", Response.Status.UNAUTHORIZED);
        }
    }
}
