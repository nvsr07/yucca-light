package org.csi.yucca.gateway.configuration;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartedEventHolderBean {
	private Boolean eventFired = false;
	 
	@Autowired
	private StreamConfigurationManager streamConfigurationManager;
	
	
    public Boolean getEventFired() {
        return eventFired;
    }
 
    public void setEventFired(Boolean eventFired) {
		streamConfigurationManager.refreshConfiguration();
    	
        this.eventFired = eventFired;
    }
    
    
    @Scheduled(cron = "${yucca.metadata.refresh.cron}" )
    public void demoServiceMethod()
    {
    	
        //System.out.println("$$$$$$$$$$$$$$$$$$$$$Method executed at every 5 seconds. Current time is :: "+ new Date() );
        
        streamConfigurationManager.refreshConfiguration();
    }
}
