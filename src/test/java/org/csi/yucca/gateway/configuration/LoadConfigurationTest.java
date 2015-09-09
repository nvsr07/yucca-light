package org.csi.yucca.gateway.configuration;

import org.csi.yucca.gateway.YuccaLightApplication;
import org.csi.yucca.gateway.configuration.util.AbstractConfigurationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;


public class LoadConfigurationTest extends AbstractConfigurationTest {

	@Autowired
	private StreamConfigurationManager streamConfigurationManager;
	
	
	public void setStreamConfigurationManager(StreamConfigurationManager streamConfigurationManager) {
		this.streamConfigurationManager = streamConfigurationManager;
	}

	@Test
	public void test() {
		setMockYuccaMetadataServiceServer();
		mockYuccaMetadataServiceServer.expect(MockRestRequestMatchers.
				requestTo("https://yucca-metadatamanagement/management/stream/metadata?tenant=unitTenant&consumerType=yuccaLight")).
				andRespond(MockRestResponseCreators.withSuccess());
		
		streamConfigurationManager.loadConfiguration();
		
		mockYuccaMetadataServiceServer.verify();
		removeMockYuccaMetadataServiceServer();
	}

}
