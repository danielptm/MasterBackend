package com.globati.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class responsible for building a JWT and validating it. The jwtSecret is read in from a properties file.
 */

@Service
public class JwtService {

    @Autowired
    PropertiesService propertiesService;

    /**
     * Builds a JWT
     * @param apiToken
     * @return
     */
    public String buildJwt(String apiToken){
        String jwtSecret = propertiesService.getJwtSecret();
        return Jwts.builder().setSubject(apiToken).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }


    /**
     * Validates the JWT.
     * @param compactJws
     * @return
     */
    public boolean validateApiTokenFromJwt(String compactJws, String apiToken){
        String jwtSecret = propertiesService.getJwtSecret();
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(compactJws).getBody().getSubject().equals(apiToken);
    }

    public String getPayloadFromJwt(String jwt){
        String jwtSecret = propertiesService.getJwtSecret();
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }


}
