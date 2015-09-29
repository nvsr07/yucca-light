package org.csi.yucca.gateway.api;

import java.io.IOException;
import java.util.Map;

import org.csi.yucca.gateway.api.dto.StreamSensorEvent;
import org.csi.yucca.gateway.configuration.StreamConfigurationDAO;
import org.csi.yucca.gateway.exception.InsertApiBaseException;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;

public class InputManager {

	public static StreamSensorEvent validate(String tenant, JsonNode event, StreamConfigurationDAO streamConfigurationDAO) throws InsertApiBaseException {

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
			eventDto = mapper.fromJson(event.toString(), StreamSensorEvent.class);
		} catch (Exception e1) {
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_STREAM_NOT_FOUND);
		}

		if (eventDto.getStream() == null || eventDto.getStream().equals("")) {
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_INPUT_STREAM_MANCANTE);
		}
		if ((eventDto.getApplication() == null || eventDto.getApplication().equals("")) && (eventDto.getSensor() == null || eventDto.getSensor().equals(""))) {
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_INPUT_SENSOR_MANCANTE);
		}

		isOk = ParseValidationUtil.validateEventTime(eventDto);
		if (!isOk)
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_INVALID_COMPONENTS);

		try {
			isOk = ParseValidationUtil.isValidVersusSchema(event, tenant, eventDto.getStream(), eventDto.getSensor(), streamConfigurationDAO);
		} catch (InsertApiBaseException | ProcessingException | IOException e) {
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_STREAM_NOT_FOUND);
		}

		if (!isOk)
			throw new InsertApiBaseException(InsertApiBaseException.ERROR_CODE_INVALID_COMPONENTS);

		return eventDto;

	}

	
	public static void main(String[] args) throws Exception {
		Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
		
		
		
		Map<String, Object> obj = mapper.fromJson("{\"pippo\":aaaas}", Map.class);
		System.out.println(obj.getClass().getName());
		System.out.println(obj.get("pippo").getClass().getName());
		
	}
}
