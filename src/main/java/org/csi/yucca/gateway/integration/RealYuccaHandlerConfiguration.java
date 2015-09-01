package org.csi.yucca.gateway.integration;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.aop.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.handler.advice.RequestHandlerCircuitBreakerAdvice;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.MessageHandler;
import org.springframework.web.client.RestTemplate;



@Configuration
public class RealYuccaHandlerConfiguration {

	@Value("${yucca.realtime.http.endpoint}")
	private String baseUrl;
	
	@Value("${yucca.tenant.code}")
	private String codTenant;
	
	@Value("${yucca.tenant.username}")
	private String username;
	
	@Value("${yucca.tenant.password}")
	private String password;
	
	@Bean
	public Advice circuitBreakerAdvice(){
		RequestHandlerCircuitBreakerAdvice advice = new RequestHandlerCircuitBreakerAdvice();
		advice.setHalfOpenAfter(12000);
		advice.setThreshold(2);
		return advice;
	}

	
	@Bean(name="realYuccaHandler")
	@Autowired
	@ServiceActivator(inputChannel="toYuccaRTChannelString", outputChannel="outputYuccaChannel", poller = @Poller(fixedDelay = "100")
	)
	public MessageHandler realYuccaHandler(){
		RestTemplate restTemplate = new TestRestTemplate(username,password);
		HttpRequestExecutingMessageHandler httpHandler = new HttpRequestExecutingMessageHandler(baseUrl+"/"+codTenant+"/",restTemplate);
		httpHandler.setHttpMethod(HttpMethod.POST);
		List<Advice> advices = new ArrayList<Advice>();
		advices.add(circuitBreakerAdvice());
		httpHandler.setAdviceChain(advices);
		httpHandler.setSendTimeout(10000);
		httpHandler.setExpectedResponseType(java.lang.String.class);
		httpHandler.setOutputChannelName("outputYuccaChannel");
		return httpHandler;
	}
	
}
