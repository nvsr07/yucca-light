package org.csi.yucca.gateway.api.rest.excepmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.csi.yucca.gateway.api.dto.ErrorOnInbound;

import com.fasterxml.jackson.core.JsonProcessingException;


@Provider
public class JsonInvalidMapper implements ExceptionMapper<JsonProcessingException> {

	@Override
	public Response toResponse(JsonProcessingException arg0) {
		ErrorOnInbound errorOnInbound = new ErrorOnInbound();
		errorOnInbound.setError_code("E012");
		errorOnInbound.setError_name("Json validation failed");
		
		
		return Response.status(503).entity(errorOnInbound).type("application/json")
                .build();
	}

}
