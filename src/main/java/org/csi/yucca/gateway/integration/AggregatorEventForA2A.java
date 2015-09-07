package org.csi.yucca.gateway.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.csi.yucca.gateway.integration.dto.EventMessage;
import org.csi.yucca.gateway.util.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.aggregator.CorrelationStrategy;
import org.springframework.integration.aggregator.HeaderAttributeCorrelationStrategy;
import org.springframework.integration.support.MessageBuilderFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

public class AggregatorEventForA2A implements CorrelationStrategy {

	@Autowired
	private MessageBuilderFactory messageBuilderFactory;
	
	public Message<?> aggregate(List<Message<?>> messages) {
		EventMessage msg = new EventMessage();
		String measures = "";

		for (Iterator iterator = messages.iterator(); iterator.hasNext();) {
			Message<?> message = (Message<?>) iterator.next();
			EventMessage dto = (EventMessage) message.getPayload();
			measures += Conversion.extractMeasuresWithoutBrackets(dto.getMeasures());
			if (!iterator.hasNext())
			{
				msg.setApplication(dto.isApplication());
				msg.setSourceCode(dto.getSourceCode());
				msg.setStreamCode(dto.getStreamCode());
				msg.setMeasures("["+measures+"]");
			}
			else {
				measures += ",";
			}
				
		}
		
		
		return messageBuilderFactory.withPayload(msg).copyHeaders(aggregatedHeaders(messages)).build();
	}	

	 public Object getCorrelationKey(Message<?> message)
	 {
		 if (message.getPayload() instanceof EventMessage)
			 return ((EventMessage) message.getPayload()).getSourceCode()+"_"+((EventMessage) message.getPayload()).getStreamCode();
		 else 
			 return message.getHeaders().get("CORRELATION_ID");
	 }
	
	
     private Map<String, ?> aggregatedHeaders(List<Message<?>> messages) {
    	Map<String, Object> headers = new HashMap<String, Object>();

		List<MessageHeaders> allHeaders = new ArrayList<MessageHeaders>(messages.size());
		for (Message<?> message : messages) {
			allHeaders.add(message.getHeaders());
		}
		
		headers.put("messagesHeaders", allHeaders);
		
		return headers;
	}

}
