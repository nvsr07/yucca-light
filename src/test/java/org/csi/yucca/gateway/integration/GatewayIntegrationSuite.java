package org.csi.yucca.gateway.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GatewayIntegrationA2ATest.class, 
				GatewayIntegrationJmsTest.class,
				GatewayIntegrationDBTest.class
})
public class GatewayIntegrationSuite {

}

