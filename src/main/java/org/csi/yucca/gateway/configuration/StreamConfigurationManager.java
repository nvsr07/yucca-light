package org.csi.yucca.gateway.configuration;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.csi.yucca.gateway.configuration.dto.StreamConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StreamConfigurationManager {

	// public static final String USERPORTAL_API_URL =
	// "http://localhost:8080/userportal/";
	// public static final String STREAM_CONFIGURATION_URL =
	// "secure/proxy/management/stream/metadata?consumerType=yuccaLight&tenant=";

	Logger logger = Logger.getLogger("StreamConfigurationManager");
	
	private String baseUrl;

	private String codTenant;

	@Autowired
	private RestTemplate metadataRestTemplate;

	@Autowired
	private StreamConfigurationDAO streamConfigurationDAO;

	public StreamConfigurationManager(String baseUrl, String codTenant) {
		this.baseUrl = baseUrl;
		this.codTenant = codTenant;
	}

	public void refreshConfiguration() {
		String configJson = loadConfiguration();
		if (configJson!=null)
			saveConfiguration(configJson);
	}

	public String loadConfiguration() {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		String streamMetadataUrl = baseUrl + "/management/stream/metadata";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(streamMetadataUrl).queryParam("tenant", codTenant).queryParam("consumerType", "yuccaLight");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response;
		try {
			response = metadataRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
			String streamJson = response.getBody();
			logger.fine("loadConfiguration:["+streamJson+"]");
			return streamJson;
		} catch (RestClientException e) {
			logger.log(Level.SEVERE,"Error in loadConfiguration",e);
			return null;
		}
	}

	public void saveConfiguration(String streamJson) {

		ObjectMapper mapper = new ObjectMapper();

		StreamConfiguration[] streamConfigurations = null;

		try {
			streamConfigurations = mapper.readValue(streamJson, StreamConfiguration[].class);
			if (streamConfigurations != null) {

				for (StreamConfiguration streamConfiguration : streamConfigurations) {
					StringWriter sw = new StringWriter();
					mapper.writeValue(sw, streamConfiguration);
					streamConfiguration.setJsonMetadata(sw.toString());

					streamConfigurationDAO.saveStreamMetadataConfiguration(streamConfiguration, streamJson);

					System.out.println(streamConfiguration.getJSonSchema());
				}
			}
		} catch (JsonParseException e) {
			logger.log(Level.SEVERE,"Error in saveConfiguration",e);
		} catch (JsonMappingException e) {
			logger.log(Level.SEVERE,"Error in saveConfiguration",e);
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error in saveConfiguration",e);
		}

	}

}
