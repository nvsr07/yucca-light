package org.csi.yucca.gateway.configuration.util;

import org.csi.yucca.gateway.YuccaLightApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("unit")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = YuccaLightApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class AbstractConfigurationTest {
	
	
	@Autowired
	private RestTemplate metadataRestTemplate;
	
	public MockRestServiceServer mockYuccaMetadataServiceServer; 



	private ClientHttpRequestFactory originalYuccaMetadataRequestFactory;

	@Before
	public void setUp() throws Exception {
		originalYuccaMetadataRequestFactory = metadataRestTemplate.getRequestFactory();
    }

	public void setMockYuccaMetadataServiceServer()
	{
		mockYuccaMetadataServiceServer = MockRestServiceServer.createServer(metadataRestTemplate);
	}


	public void removeMockYuccaMetadataServiceServer()
	{
		metadataRestTemplate.setRequestFactory(originalYuccaMetadataRequestFactory);
	}

}
