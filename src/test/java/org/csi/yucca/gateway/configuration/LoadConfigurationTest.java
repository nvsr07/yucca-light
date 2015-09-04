package org.csi.yucca.gateway.configuration;

import org.csi.yucca.gateway.YuccaLightApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ActiveProfiles("int")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = YuccaLightApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class LoadConfigurationTest {

	@Autowired
	private StreamConfigurationManager streamConfigurationManager;
	
	
	public void setStreamConfigurationManager(StreamConfigurationManager streamConfigurationManager) {
		this.streamConfigurationManager = streamConfigurationManager;
	}

	@Test
	public void test() {
		streamConfigurationManager.loadConfiguration();
		
	}

}
