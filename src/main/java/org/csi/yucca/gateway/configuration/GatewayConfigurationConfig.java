package org.csi.yucca.gateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GatewayConfigurationConfig {


	@Value("${yucca.metadata.http.baseurl}")
	private String baseUrl;

	@Value("${yucca.tenant.code}")
	private String codTenant;

	@Value("${yucca.tenant.username}")
	private String username;

	@Value("${yucca.tenant.password}")
	private String password;

	@Bean
	@Autowired
	RestTemplate metadataRestTemplate() {
		return  new TestRestTemplate(username, password);
	}
	
	
	@Bean
	@Autowired
	StreamConfigurationManager streamConfigurationManager() {
		return new StreamConfigurationManager(baseUrl, codTenant);
	}

	@Bean
	@Autowired
	StreamConfigurationDAO streamConfigurationDAO(){
		return new StreamConfigurationDAO();
	}
	
	@Bean
	@Autowired
	ApplicationStartedEventHolderBean applicationStartedEventHolderBean(){
		return new ApplicationStartedEventHolderBean();
	}
	
	
}
