package org.csi.yucca.gateway.integration.util;

import java.util.concurrent.CountDownLatch;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public abstract class  CountDownHandler implements MessageHandler {
	CountDownLatch latch;
	
	public CountDownHandler(Object expectedMessage) {
		this.expectedMessage = expectedMessage;
	}
	
	private Object expectedMessage;

	public final void setLatch(CountDownLatch latch){
		this.latch = latch;
	}

	protected abstract void verifyMessage(Object expectedMessage, Message<?> message);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.integration.core.MessageHandler#handleMessage
	 * (org.springframework.integration.Message)
	 */
	public void handleMessage(Message<?> message) throws MessagingException {
		verifyMessage(expectedMessage,message);
		latch.countDown();
	}

}
