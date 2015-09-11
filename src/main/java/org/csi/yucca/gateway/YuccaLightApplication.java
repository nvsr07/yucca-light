package org.csi.yucca.gateway;

import org.csi.yucca.gateway.configuration.ApplicationStartedEventHolderBean;
import org.csi.yucca.gateway.configuration.ApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class YuccaLightApplication {

    public static void main(String[] args) {
    	ConfigurableApplicationContext ctx=SpringApplication.run(YuccaLightApplication.class, args);
    	ApplicationStartedEventHolderBean eventHolderBean = ctx.getBean(ApplicationStartedEventHolderBean.class);
    	System.out.println("Event Processed?? - " + eventHolderBean.getEventFired());
    }
}
