package org.csi.yucca.gateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
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
}
