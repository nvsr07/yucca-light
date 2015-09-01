package org.csi.yucca.gateway.api;

import java.net.HttpRetryException;
import java.net.URI;
import java.net.URISyntaxException;

import org.csi.yucca.gateway.YuccaLightApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("int")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = YuccaLightApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class InputApiControllerTest {

	private RestTemplate restTemplate = new TestRestTemplate("sandbox","sandbox$1");

	// TODO: Validare uno stream con tutte le componenti, inclusi datetime e longit/latitud 
	 
    @Test
    public void testCredentialInvalid() throws URISyntaxException {
    	restTemplate = new TestRestTemplate("sandbox","saggndbox$1");
    	
    	MultiValueMap<String, String> headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    	headers.add(HttpHeaders.ACCEPT, "application/json");
    	
    	RequestEntity<String> json = new RequestEntity<String>("{ a }",headers,
    												HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));
    	
    	ResponseEntity<String> result;
		try {
			result = restTemplate.exchange(json,String.class);
		} catch (RestClientException e) {
			Assert.assertTrue(true);
			Assert.assertTrue(e.getCause() instanceof HttpRetryException);
			Assert.assertTrue(((HttpRetryException)e.getCause()).responseCode() == 401);
			return;
		}

		Assert.assertFalse(true);
    }

	 
	 @Test
    public void testJsonInvalid() throws URISyntaxException {
    	MultiValueMap<String, String> headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    	headers.add(HttpHeaders.ACCEPT, "application/json");
    	
    	RequestEntity<String> invalidJson = new RequestEntity<String>("{ a }",headers,
    												HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));
    	
    	ResponseEntity<String> result = restTemplate.exchange(invalidJson,String.class);

    	Assert.assertTrue(result.getStatusCode().is5xxServerError());
    	Assert.assertTrue(result.getBody().toString().contains("E012"));

    }
	 
	@Test
    public void testJsonValidWithNoSensorOrStream() throws URISyntaxException {
    	MultiValueMap<String, String> headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    	headers.add(HttpHeaders.ACCEPT, "application/json");
    	
    	RequestEntity<String> jsonValidButDifferent = new RequestEntity<String>("{ \"a\":\"a\" }",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	ResponseEntity<String> result = restTemplate.exchange(jsonValidButDifferent,String.class);
		
		Assert.assertTrue(result.getStatusCode().is5xxServerError());
		Assert.assertTrue(result.getBody().toString().contains("E011"));
    	
    	
    	
    }
	@Test
    public void testJsonValidWithDifferentComponents() throws URISyntaxException {
    	MultiValueMap<String, String> headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    	headers.add(HttpHeaders.ACCEPT, "application/json");
    	
    	RequestEntity<String> jsonValidButDiffComponent = new RequestEntity<String>("{"
    			+ "  \"stream\":\"temperature\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": [{"
  		+ "\"time\": \"2014-05-13T17:08:58Z\",\"components\": { \"c00000\": \"17.4\" }}]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	ResponseEntity<String> result = restTemplate.exchange(jsonValidButDiffComponent,String.class);
		
		Assert.assertTrue(result.getStatusCode().is5xxServerError());
		Assert.assertTrue(result.getBody().toString().contains("E013"));
    	
    	
    }
	
	@Test
    public void testJsonValidWithoutSchema() throws URISyntaxException {
    	MultiValueMap<String, String> headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    	headers.add(HttpHeaders.ACCEPT, "application/json");
    	
    	RequestEntity<String> jsonValidButDiffComponent = new RequestEntity<String>("{"
    			+ "  \"stream\":\"ss\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": [{"
  		+ "\"time\": \"2014-05-13T17:08:58Z\",\"components\": { \"c00000\": \"17.4\" }}]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	ResponseEntity<String> result = restTemplate.exchange(jsonValidButDiffComponent,String.class);
		
		Assert.assertTrue(result.getStatusCode().is5xxServerError());
		Assert.assertTrue(result.getBody().toString().contains("E011"));
    	
    	
    }
	@Test
    public void testJsonValid() throws URISyntaxException {
    	MultiValueMap<String, String> headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    	headers.add(HttpHeaders.ACCEPT, "application/json");
    	
    	RequestEntity<String> jsonValid = new RequestEntity<String>("{"
    			+ "  \"stream\":\"temperature\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": [{"
  		+ "\"time\": \"2014-05-13T17:08:58Z\",\"components\": { \"c0\": \"17.4\" }}]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	ResponseEntity<String> result = restTemplate.exchange(jsonValid,String.class);
		
		Assert.assertTrue(result.getStatusCode()== HttpStatus.ACCEPTED);
    	
    	
    }
	
	
	@Test
    public void testJsonDifferentTimeformat() throws URISyntaxException {
    	MultiValueMap<String, String> headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    	headers.add(HttpHeaders.ACCEPT, "application/json");
    	
    	RequestEntity<String> jsonValid = new RequestEntity<String>("{"
    			+ "  \"stream\":\"temperature\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": [{"
  		+ "\"time\": \"2014-05-13T17:08:58Z\",\"components\": { \"c0\": \"17.4\" }}]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	ResponseEntity<String> result = restTemplate.exchange(jsonValid,String.class);
		
		Assert.assertTrue(result.getStatusCode().is2xxSuccessful());
    	
    	jsonValid = new RequestEntity<String>("{"
    			+ "  \"stream\":\"temperature\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": [{"
  		+ "\"time\": \"2014-05-13T17:08:58+0200\",\"components\": { \"c0\": \"17.4\" }}]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	result = restTemplate.exchange(jsonValid,String.class);
		
		Assert.assertTrue(result.getStatusCode().is2xxSuccessful());
    	
    	jsonValid = new RequestEntity<String>("{"
    			+ "  \"stream\":\"temperature\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": [{"
  		+ "\"time\": \"2014-05-13T17:08:58.000+0200\",\"components\": { \"c0\": \"17.4\" }}]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	result = restTemplate.exchange(jsonValid,String.class);
		
		Assert.assertTrue(result.getStatusCode().is2xxSuccessful());
    	
		jsonValid = new RequestEntity<String>("{"
    			+ "  \"stream\":\"temperature\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": [{"
  		+ "\"time\": \"2014-05-13T17:08:58.000+02:00\",\"components\": { \"c0\": \"17.4\" }}]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	result = restTemplate.exchange(jsonValid,String.class);
		
		Assert.assertTrue(result.getStatusCode().is2xxSuccessful());

		jsonValid = new RequestEntity<String>("{"
    			+ "  \"stream\":\"temperature\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": [{"
  		+ "\"time\": \"2014-05-13T17:08:58.000Z\",\"components\": { \"c0\": \"17.4\" }}]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	result = restTemplate.exchange(jsonValid,String.class);
		
		Assert.assertTrue(result.getStatusCode().is2xxSuccessful());

		jsonValid = new RequestEntity<String>("{"
    			+ "  \"stream\":\"temperature\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": [{"
  		+ "\"time\": \"2014-ss05-13T17:08:58.000Z\",\"components\": { \"c0\": \"17.4\" }}]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	result = restTemplate.exchange(jsonValid,String.class);
		
		Assert.assertTrue(result.getStatusCode().is5xxServerError());

		jsonValid = new RequestEntity<String>("{"
    			+ "  \"stream\":\"temperature\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": ["
    			+ "{\"time\": \"2014-05-13T17:08:58.000Z\",\"components\": { \"c0\": \"17.4\" }},"
    			+ "{\"time\": \"2014-05-13T17:08:58Z\",\"components\": { \"c0\": \"17.4\" }},"
    			+ "{\"time\": \"2014-05-1ss3T17:08:58+0200\",\"components\": { \"c0\": \"17.4\" }},"
    			+ "{\"time\": \"2014-05-13T17:08:58.000+02:00\",\"components\": { \"c0\": \"17.4\" }}"
    			+ "]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	result = restTemplate.exchange(jsonValid,String.class);
		
		Assert.assertTrue(result.getStatusCode().is5xxServerError());

		jsonValid = new RequestEntity<String>("{"
    			+ "  \"stream\":\"temperature\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": ["
    			+ "{\"time\": \"2014-05-13T17:08:58.000Z\",\"components\": { \"c0\": \"17.4\" }},"
    			+ "{\"time\": \"2014-05-13T17:08:58Z\",\"components\": { \"c0\": \"17.4\" }},"
    			+ "{\"time\": \"2014-05-13T17:08:58+0200\",\"components\": { \"c0\": \"17.4\" }},"
    			+ "{\"time\": \"2014-05-13T17:08:58.000+02:00\",\"components\": { \"c0\": \"17.4\" }}"
    			+ "]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	result = restTemplate.exchange(jsonValid,String.class);
		
		Assert.assertTrue(result.getStatusCode().is2xxSuccessful());
	
	}
	
	@Test
    public void testJsonNoStream() throws URISyntaxException {
    	MultiValueMap<String, String> headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    	headers.add(HttpHeaders.ACCEPT, "application/json");
    	
    	RequestEntity<String> json = new RequestEntity<String>("{"
    			+ "  \"stream\":\"\",\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": [{"
  		+ "\"time\": \"2014-05s-13T17:08:58Z\",\"components\": { \"c0\": \"17.4\" }}]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	ResponseEntity<String> result = restTemplate.exchange(json,String.class);
		
		Assert.assertTrue(result.getStatusCode().is5xxServerError());
		Assert.assertTrue(result.getBody().toString().contains("E017"));
    	
    	json = new RequestEntity<String>("{"
    			+ "  \"stream\":null,\"sensor\": \"550e8400-e29b-41d4-a716-446655440000\",\"values\": [{"
  		+ "\"time\": \"2014-05s-13T17:08:58Z\",\"components\": { \"c0\": \"17.4\" }}]}",headers,
				HttpMethod.POST,new URI("http://localhost:9000/api/input/sandbox"));

    	result = restTemplate.exchange(json,String.class);
		
		Assert.assertTrue(result.getStatusCode().is5xxServerError());
		Assert.assertTrue(result.getBody().toString().contains("E011"));
    	
    }
}
