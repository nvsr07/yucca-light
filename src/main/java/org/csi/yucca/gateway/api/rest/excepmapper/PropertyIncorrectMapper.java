package org.csi.yucca.gateway.api.rest.excepmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.csi.yucca.gateway.api.dto.ErrorOnInbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;



@Provider
public class PropertyIncorrectMapper implements ExceptionMapper<PropertyBindingException> {

	@Override
	public Response toResponse(PropertyBindingException  arg0) {
		ErrorOnInbound errorOnInbound = new ErrorOnInbound();
		errorOnInbound.setError_code("E013");
		errorOnInbound.setError_name("Json components are not coherent with stream definition");
//		errorOnInbound.setMessage(arg0.getLocation().toString());
		// TODO inserire messaggio originale se possibile
		
		return Response.status(503).entity(errorOnInbound).type("application/json")
                .build();
	}

}
