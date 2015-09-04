package org.csi.yucca.gateway.integration.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.integration.support.converter.SimpleMessageConverter;
import org.springframework.messaging.SubscribableChannel;

public class AbstractGatewayIntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(AbstractGatewayIntegrationTest.class);
	
	protected boolean verifyJmsMessageReceivedOnOutputChannel(Object obj, SubscribableChannel expectedOutputChannel, CountDownHandler handler) throws JMSException, InterruptedException{
		return verifyMessageOnOutputChannel(obj, expectedOutputChannel, handler, 7000);
	}



	protected boolean verifyMessageOnOutputChannel(Object obj, SubscribableChannel expectedOutputChannel, CountDownHandler handler,int timeoutMillisec) throws JMSException,
			InterruptedException {


		String text = (String) obj;

		CountDownLatch latch = new CountDownLatch(1);
		handler.setLatch(latch);

		expectedOutputChannel.subscribe(handler);

		boolean latchCountedToZero = latch.await(timeoutMillisec, TimeUnit.MILLISECONDS);

		if (!latchCountedToZero) {
			LOGGER.warn(String.format("The specified waiting time of the latch (%s ms) elapsed.", timeoutMillisec));
		}

		return latchCountedToZero;

	}
}
