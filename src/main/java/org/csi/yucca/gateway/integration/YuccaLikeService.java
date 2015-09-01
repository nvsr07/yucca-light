package org.csi.yucca.gateway.integration;

import org.csi.yucca.gateway.integration.dto.EventMessage;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(name="yuccaLikeGateway")
public interface YuccaLikeService {

	@Gateway(headers={@GatewayHeader(name="Content-Type", value="application/json"),
					  @GatewayHeader(name="errorChannel", value="outputFailureYuccaChannel")
					 },
			requestChannel="yuccaLikeRtChannel", replyTimeout=20, replyChannel="outputYuccaChannel")
	public void sendEventToYucca(EventMessage message);

	
}
