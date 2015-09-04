package org.csi.yucca.gateway.api.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
    	register(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
    	register(RestApiController.class);
        packages("org.csi.yucca.gateway.api.rest", "org.csi.yucca.gateway.api.dto");
    }
}
