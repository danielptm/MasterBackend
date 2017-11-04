package com.globati.config;

import com.globati.resources.*;
import com.globati.resources.exceptions.IllegalIUsernameExceptionMapper;
import com.globati.resources.exceptions.UserDoesNotExistMapper;
import com.globati.resources.exceptions.UserNameIsNotUniqueExceptionMapper;
import com.globati.resources.filters.DefaultAuthentication;
import com.globati.resources.filters.ResponseFilter;
import com.globati.service.exceptions.UserNameIsNotUniqueException;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import javax.ws.rs.core.UriInfo;

/**
 * Created by daniel on 12/11/16.
 */
@Component
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig() {
        register(RecommendationResource.class);
        register(EventResource.class);
        register(EmployeeResource.class);
        register(MultiPartFeature.class);
        register(GuestResource.class);
        register(DefaultAuthentication.class);
        register(UserDoesNotExistMapper.class);
        register(IllegalIUsernameExceptionMapper.class);
        register(UserNameIsNotUniqueExceptionMapper.class);
        register(DealResource.class);
        register(BrainTreeResource.class);
        register(AuthenticationResource.class);
        register(ResponseFilter.class);
        register(FacebookConnectionFactory.class);
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        property(ServerProperties.PROCESSING_RESPONSE_ERRORS_ENABLED, true);
        register(ThirdPartyApi.class);

    }
}
