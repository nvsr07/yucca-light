package org.csi.yucca.gateway.configuration;

import java.io.IOException;
import java.io.StringWriter;

import org.csi.yucca.gateway.configuration.dto.StreamConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

	private String baseUrl;

	private String codTenant;

	private String username;

	private String password;

	@Autowired
	private StreamConfigurationDAO streamConfigurationDAO;

	public StreamConfigurationManager(String baseUrl, String codTenant, String username, String password) {
		this.username = username;
		this.password = password;
		this.baseUrl = baseUrl;
		this.codTenant = codTenant;
	}

	public void refreshConfiguration() {
		String configJson = loadConfiguration();
		saveConfiguration(configJson);
	}

	public String loadConfiguration() {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		String streamMetadataUrl = baseUrl + "/management/stream/metadata";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(streamMetadataUrl).queryParam("tenant", codTenant).queryParam("consumerType", "yuccaLight");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		RestTemplate restTemplate = new TestRestTemplate(username, password);

		HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
		String streamJson = response.getBody();
		System.out.println(streamJson);
		return streamJson;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public static void main(String[] args) {
	//
	// String jsonS =
	// "[{\"streamCode\":\"MultiTenantStr\",\"streamName\":\"ale\",\"configData\":{\"tenantCode\":\"sandbox\"},\"streams\":{\"stream\":{\"virtualEntityName\":\"ProvaDue\",\"virtualEntityDescription\":\"Descrizione
	// MultiTenantStr\",\"virtualEntityCode\":\"3e81f75f-aee6-4ac0-c4c3-e6a6dd0e7547\",\"virtualEntityType\":\"Device\",\"virtualEntityCategory\":\"Smart\",\"domainStream\":\"ENVIRONMENT\",\"visibility\":\"private\",\"deploymentVersion\":1,\"streamIcon\":\"img/stream-icon-default.png\",\"components\":{\"element\":"
	// +
	// "[{\"componentName\":\"wins\",\"componentAlias\":\"wins\",\"tolerance\":12,\"measureUnit\":\"lux\",\"measureUnitCategory\":\"lightning\",\"phenomenon\":\"air
	// temperature\",\"phenomenonCategory\":\"environment\",\"dataType\":\"float\"},"
	// +
	// "{\"componentName\":\"secondo\",\"componentAlias\":\"wins\",\"tolerance\":12,\"measureUnit\":\"lux\",\"measureUnitCategory\":\"lightning\",\"phenomenon\":\"air
	// temperature\",\"phenomenonCategory\":\"environment\",\"dataType\":\"string\"}"
	// + "]},\"streamTags\":{\"tags\":[
	// ]},\"virtualEntityPositions\":{\"position\":[{\"lon\":0,\"lat\":0,\"elevation\":0,\"floor\":0,\"room\":\"0\"}]}}}}]";
	//
	// new StreamConfigurationManager().saveConfiguration(jsonS);
	// }
}
