package com.globati.resources.filters;

import com.globati.dbmodel.EmployeeInfo;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.resources.exceptions.WebException;
import com.globati.service.EmployeeInfoService;
import com.globati.service.EmployeeService;
import com.globati.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
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
    EmployeeService employeeService;

    @Autowired
    EmployeeInfoService employeeInfoService;

    @Autowired
    JwtService jwtService;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String clientapikey1 = requestContext.getHeaders().get("Authorization").get(0);
        EmployeeInfo employeeInfo = null;
        String jwt = null;

        try {
            jwt = clientapikey1.substring("Bearer".length()).trim();
            employeeInfo = employeeInfoService.getEmployeeInfoByToken(jwtService.getPayloadFromJwt(jwt));
        }catch(Exception e){
            throw new WebException("Could not get employee by auth token", Response.Status.UNAUTHORIZED);
        }

        if(! jwtService.getPayloadFromJwt(jwt).equals(employeeInfo.getAuthToken()) ){
            throw new WebException("The user needs to authenticate themselves", Response.Status.UNAUTHORIZED);
        }

        if(Long.parseLong(employeeInfo.getTokenExpiration())< System.currentTimeMillis() ){
            throw new WebException("The token is expired, the user needs to log in to get a new token", Response.Status.UNAUTHORIZED);
        }
    }
}
