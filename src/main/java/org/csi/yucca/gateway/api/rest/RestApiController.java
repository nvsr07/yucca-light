package org.csi.yucca.gateway.api.rest;

import javax.ws.rs.PathParam;

import java.util.logging.Logger;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.csi.yucca.gateway.api.InputManager;
import org.csi.yucca.gateway.api.dto.StreamSensorEvent;
import org.csi.yucca.gateway.exception.InsertApiBaseException;
import org.csi.yucca.gateway.integration.YuccaLikeService;
import org.csi.yucca.gateway.util.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

@Path("/input/{tenant}")
public class RestApiController {

	Logger logger = Logger.getLogger("RestApiController");
	
	@Autowired
	private YuccaLikeService yuccaLikeService;


	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response receive(@PathParam("tenant") String tenant,JsonNode event) throws InsertApiBaseException {
		StreamSensorEvent eventDto =InputManager.validate(tenant,event);
		if (tenant.equals("exp"))
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_STREAM_NOT_FOUND,"prova");

		
		try {
			yuccaLikeService.sendEventToYucca(Conversion.fromStreamSensorEvent2EventMessage(eventDto));
		} catch (JsonProcessingException e) {
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_INPUT_INVALID_DATA_VALUE,e.getMessage());
		}
				
//		MessagingTemplate msgTemplate = new MessagingTemplate();
//		Message<StreamSensorEvent> msgEventDto = new GenericMessage<StreamSensorEvent>(eventDto);
//		Message reply = msgTemplate.sendAndReceive("requestChannel",msgEventDto);
		
		
		
		// if fail --> Store for task
		
		
		return Response.accepted().build();
	}
	

	public void setYuccaLikeService(YuccaLikeService service)
	{
		this.yuccaLikeService = service;
	}
}
