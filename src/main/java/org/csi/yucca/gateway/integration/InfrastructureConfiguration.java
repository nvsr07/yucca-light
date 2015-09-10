package org.csi.yucca.gateway.integration;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;


@Configuration
@IntegrationComponentScan("org.csi.yucca.gateway.integration")
@EnableIntegration
@EnableJms
@ImportResource("spring-integration.xml")
public class InfrastructureConfiguration {

	@Bean
	public AggregatorEventForA2A aggregatorEventForA2A()
	{
		return new AggregatorEventForA2A();
	}
	
	@Bean
	public SplitterEventForA2A splitterEventForA2A()
	{
		return new SplitterEventForA2A();
	}
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Bean
	
	public JmsTemplate jmsTemplateNoWait()
	{
		JmsTemplate noWait =new JmsTemplate(connectionFactory);
		noWait.setReceiveTimeout(500);
		return noWait;
	}
	
}
