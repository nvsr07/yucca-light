package org.csi.yucca.gateway.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.MessageHandlerChain;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.integration.transformer.ContentEnricher;
import org.springframework.integration.transformer.ExpressionEvaluatingTransformer;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.ExpressionEvaluatingHeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;


@Configuration
@IntegrationComponentScan("org.csi.yucca.gateway.integration")
@EnableIntegration
@ImportResource("spring-integration.xml")
public class InfrastructureConfiguration {

//	@Bean
//	public  ThreadPoolTaskExecutor taskExecutor()
//	{
//		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//		taskExecutor.setCorePoolSize(10);
//		taskExecutor.initialize();
//		return taskExecutor;
//	}
	
	
	
//	@Bean
//	@Transformer(inputChannel="yuccaLikeRtChannel", outputChannel="requestGwChannel")
//	public org.springframework.integration.transformer.Transformer transformAddBusinessIdHandler()
//	{
//		ExpressionEvaluatingHeaderValueMessageProcessor<String> proc = new ExpressionEvaluatingHeaderValueMessageProcessor<>("headers.id", String.class);
//
//		
//		Map<String, ExpressionEvaluatingHeaderValueMessageProcessor<String>> headerToAdd = new HashMap();
//
//		headerToAdd.put("businessId", proc);
//		
//		HeaderEnricher enrich = new HeaderEnricher(headerToAdd );
//
//		return enrich;
//	}
	
	
//	@Bean
//	public MessageChannel requestGwChannel(){
//		return new PublishSubscribeChannel();
//	}
//	
//	@Bean
//	public MessageChannel toInserChannel(){
//		return new DirectChannel();
//	}
//
//	@Bean
//	public MessageChannel toYuccaRTChannel(){
//		return new QueueChannel(100);
//	}
//	
//	@Bean
//	public MessageChannel outputYuccaChannel(){
//		return new DirectChannel();
//	}
//
//	@Bean
//	public MessageChannel outputFailureYuccaChannel(){
//		return new PublishSubscribeChannel();
//	}
//
//	@Bean
//	public MessageChannel toUpdateChannel(){
//		return new PublishSubscribeChannel();
//	}
//


	
	
//	@Bean
//	@ServiceActivator(inputChannel="outputFailureYuccaChannel", outputChannel="toUpdateChannel")
//	public MessageHandler transformFailedHandler()
//	{
//		ExpressionParser parser = new SpelExpressionParser();
//		ExpressionEvaluatingHeaderValueMessageProcessor<String> proc = 
//				new ExpressionEvaluatingHeaderValueMessageProcessor<>("payload.error", String.class);
//
//		Expression expression =  parser.parseExpression("payload.failedMessage");
//
//		Map<String, HeaderValueMessageProcessor<?>> headerToAdd = new HashMap();
//		headerToAdd.put("error", proc);
//		
//		ContentEnricher enrich = new ContentEnricher();
//		enrich.setHeaderExpressions(headerToAdd);
//		enrich.setRequestPayloadExpression(expression);
//		return enrich;
//	}

	
//	@Bean
//	@ServiceActivator(inputChannel = "requestGwChannel")
//	public MessageHandler routerHandler()
//	{
//		RecipientListRouter chainRouter = new RecipientListRouter();
//		List<MessageChannel> channels = new ArrayList<>();
//		channels.add(toInserChannel());
//		channels.add(toYuccaRTChannel());
//		chainRouter.setChannels(channels);
//		return chainRouter;
//	}



	//	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>();
//		list.add("pippo");
//		list.add("pippo");
//		System.out.println(list.size());
//		System.out.println(new HashSet<String>(list).size());
// 
//	}

	
	
}
