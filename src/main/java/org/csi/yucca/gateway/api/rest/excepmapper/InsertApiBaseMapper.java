package org.csi.yucca.gateway.api.rest.excepmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.csi.yucca.gateway.api.dto.ErrorOnInbound;
import org.csi.yucca.gateway.exception.InsertApiBaseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;



@Provider
public class InsertApiBaseMapper implements ExceptionMapper<InsertApiBaseException> {

	@Override
	public Response toResponse(InsertApiBaseException  e) {
		ErrorOnInbound errorOnInbound = new ErrorOnInbound();
		errorOnInbound.setError_code(e.getErrorCode());
		errorOnInbound.setError_name(e.getErrorName());
		errorOnInbound.setMessage(e.getMessage());
		// TODO inserire messaggio originale se possibile
		
		return Response.status(503).entity(errorOnInbound).type("application/json")
                .build();
	}

}
