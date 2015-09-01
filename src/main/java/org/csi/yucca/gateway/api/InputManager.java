package org.csi.yucca.gateway.api;

import java.io.IOException;

import org.csi.yucca.gateway.api.dto.StreamSensorEvent;
import org.csi.yucca.gateway.exception.InsertApiBaseException;
import org.springframework.integration.support.json.Jackson2JsonMessageParser;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.examples.Utils;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import com.github.fge.jsonschema.messages.JsonSchemaValidationBundle;

public class InputManager {

	public static StreamSensorEvent validate(String tenant, JsonNode event) throws InsertApiBaseException {
		
		boolean isOk = false;

		try {
			isOk = ParseValidationUtil.isValidVersusSchema(event, ParseValidationUtil.GENERIC_SCHEMA);
		} catch (IOException | ProcessingException e2) {
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_STREAM_NOT_FOUND);
		}

		if (!isOk)
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_STREAM_NOT_FOUND);
		
		Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
		StreamSensorEvent eventDto = null;
		try {
			eventDto =mapper.fromJson(event.toString(), StreamSensorEvent.class);
		} catch (Exception e1) {
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_STREAM_NOT_FOUND);
		}
		
		if (eventDto.getStream()==null || eventDto.getStream().equals(""))
		{
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_INPUT_STREAM_MANCANTE);
		}
		if (
				(eventDto.getApplication()==null || eventDto.getApplication().equals("")) &&
				(eventDto.getSensor()==null || eventDto.getSensor().equals(""))
			)
		{
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_INPUT_SENSOR_MANCANTE);
		}

		
		
		isOk = ParseValidationUtil.validateEventTime(eventDto);
		if (!isOk)
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_INVALID_COMPONENTS);
		
		
		
		try {
			isOk = ParseValidationUtil.isValidVersusSchema(event, eventDto.getSchema());
		} catch (IOException | ProcessingException e2) {
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_STREAM_NOT_FOUND);
		}

		if (!isOk)
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_INVALID_COMPONENTS);

		
       	return eventDto;
        
	}

}
