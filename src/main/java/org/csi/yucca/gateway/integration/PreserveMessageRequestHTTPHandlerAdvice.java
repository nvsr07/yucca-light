package org.csi.yucca.gateway.integration;

import org.crsh.console.jline.internal.Log;
import org.csi.yucca.gateway.util.EventStateEnum;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.expression.ExpressionUtils;
import org.springframework.integration.handler.advice.AbstractRequestHandlerAdvice;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.Assert;

public class PreserveMessageRequestHTTPHandlerAdvice extends AbstractRequestHandlerAdvice {

//	private volatile Expression  onSuccessExpression;
//	private volatile Expression onFailureExpression;

	private volatile MessageChannel successChannel;

	private volatile MessageChannel failureChannel;

	private final MessagingTemplate messagingTemplate = new MessagingTemplate();

	private volatile EvaluationContext evaluationContext;

	public void setSuccessChannel(MessageChannel successChannel) {
		Assert.notNull(successChannel,"'successChannel' must not be null");
		this.successChannel = successChannel;
	}

	public void setFailureChannel(MessageChannel failureChannel) {
		Assert.notNull(failureChannel,"'failureChannel' must not be null");
		this.failureChannel = failureChannel;
	}


	@Override
	protected void onInit() throws Exception {
		super.onInit();
		if (this.getBeanFactory() != null) {
			this.messagingTemplate.setBeanFactory(this.getBeanFactory());
		}
	}

	@Override
	protected Object doInvoke(ExecutionCallback callback, Object target, Message<?> message) throws Exception {
		try {
			Object result = callback.execute();
			
			if (this.successChannel != null) {
				this.evaluateHTTPStatusCodeSuccessExpression(message,result);
			}
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			Exception actualException = this.unwrapExceptionIfNecessary(e);

			if (this.failureChannel != null) {
				this.evaluateFailureExpression(message, actualException);
			}
			return null;
		}
	}

	private void evaluateHTTPStatusCodeSuccessExpression(Message<?> message, Object result) throws Exception {
			Log.info("response:"+result);
			String response = null;
			String responseDetail = null;
			GenericMessage msg = (GenericMessage) result;
			HttpStatus status = (HttpStatus) msg.getHeaders().get("http_statusCode");
			String gwStatus = status.is2xxSuccessful()?EventStateEnum.SENT_RT.name():EventStateEnum.SENT_INVALID.name();
			response = status.toString();
			if (msg.getPayload() instanceof String)
			{
				responseDetail = msg.getPayload().toString();
			}
			else if (msg.getPayload() instanceof ResponseEntity) 
			{
				if (((ResponseEntity)msg.getPayload()).hasBody())  {
					responseDetail = ((ResponseEntity)msg.getPayload()).getBody().toString();
				}
			}

			this.messagingTemplate.send(this.successChannel, getMessageBuilderFactory().fromMessage(message).
					setHeader("responseDetail", responseDetail).setHeader("response", response).
					setHeader("gwStatus", gwStatus).build());
	}

	private void evaluateFailureExpression(Message<?> message, Exception exception) throws Exception {
		Log.info("exception:"+exception.getMessage());
		this.messagingTemplate.send(this.failureChannel, getMessageBuilderFactory().fromMessage(message).
				setHeader("responseDetail", exception.getMessage()).setHeader("response", 999).
				setHeader("gwStatus", EventStateEnum.SENDING_FAILED.name()).build());
	}

	protected StandardEvaluationContext createEvaluationContext(){
		return ExpressionUtils.createStandardEvaluationContext(this.getBeanFactory());
	}


}