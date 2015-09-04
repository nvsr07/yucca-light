package org.csi.yucca.gateway.integration.util;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;

public class IntegrationTestUtils {

	public static Integer countQueueElement(String queueName, JmsTemplate jmsTemplate) {
		Integer num = jmsTemplate.browse(queueName,new BrowserCallback<Integer>() {
			@Override
			public Integer doInJms(Session arg0, QueueBrowser queueBrowser) throws JMSException {
				    Enumeration e = (Enumeration) queueBrowser.getEnumeration();
	
				    int numMsgs = 0;
				    while(e.hasMoreElements()) 
				    {
				         e.nextElement();
				         numMsgs++;
				    }
				return numMsgs;
			}
			
		} );
		return num;
	}

}
