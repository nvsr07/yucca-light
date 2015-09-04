package org.csi.yucca.gateway.integration;

import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.csi.yucca.gateway.YuccaLightApplication;
import org.csi.yucca.gateway.integration.dto.EventMessage;
import org.csi.yucca.gateway.integration.util.AbstractGatewayIntegrationTest;
import org.csi.yucca.gateway.integration.util.CountDownHandler;
import org.csi.yucca.gateway.integration.util.IntegrationTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.jms.PollableJmsChannel;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@ActiveProfiles("int")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = YuccaLightApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class GatewayIntegrationTest extends AbstractGatewayIntegrationTest{
	
	@Autowired
	private YuccaLikeService yuccaLikeService;
	
	@Autowired
    protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	protected JmsTemplate jmsTemplate;
	
		
	@Test
	 public void testSendValidMessage() throws URISyntaxException, InterruptedException {
		
		boolean received = false;
		
		EventMessage msg =  new EventMessage();
		msg.setApplication(false);
		msg.setSourceCode("550e8400-e29b-41d4-a716-446655440000");
		msg.setStreamCode("temperature");
		msg.setMeasures("[{\"time\": \"2014-05-13T17:08:52.00+02:00\", \"components\": {\"c0\": \"12.10\" }  } ]");

		int numeroMessaggiCoda =  IntegrationTestUtils.countQueueElement("yucca_light_sent_rt", jmsTemplate);
		int numeroMessaggi = JdbcTestUtils.countRowsInTable(jdbcTemplate, "EVENTS");
		
		yuccaLikeService.sendEventToYucca(msg);
		Thread.sleep(2000);

		int numeroMessaggiDopo = JdbcTestUtils.countRowsInTable(jdbcTemplate, "EVENTS");
		int numeroMessaggiCodaDopo =  IntegrationTestUtils.countQueueElement("yucca_light_sent_rt", jmsTemplate);

		Assert.assertEquals(numeroMessaggi+1, numeroMessaggiDopo);
		Assert.assertEquals(numeroMessaggiCoda+1, numeroMessaggiCodaDopo);
    }

	@Test
	 public void testSend503ValidMessage() throws URISyntaxException, InterruptedException {
		
		boolean received = false;
		
		EventMessage msg =  new EventMessage();
		msg.setApplication(false);
		msg.setSourceCode("550e8400-e29b-41d4-a716-446655440000");
		msg.setStreamCode("temperature");
		msg.setMeasures("[{\"time\": \"2014-05-13T17:08:52.00+02:00\", \"components\": {\"c0\": \"12.sss10\" }  } ]");

		int numeroMessaggiCoda =  IntegrationTestUtils.countQueueElement("yucca_light_sent_invalid", jmsTemplate);
		int numeroMessaggi = JdbcTestUtils.countRowsInTable(jdbcTemplate, "EVENTS");
		
		yuccaLikeService.sendEventToYucca(msg);
		Thread.sleep(2000);
		
		int numeroMessaggiDopo = JdbcTestUtils.countRowsInTable(jdbcTemplate, "EVENTS");
		int numeroMessaggiCodaDopo =  IntegrationTestUtils.countQueueElement("yucca_light_sent_invalid", jmsTemplate);

		Assert.assertEquals(numeroMessaggi+1, numeroMessaggiDopo);
		Assert.assertEquals(numeroMessaggiCoda+1, numeroMessaggiCodaDopo);
   }
	
}
