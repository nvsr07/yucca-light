package org.csi.yucca.gateway.configuration;

import org.springframework.stereotype.Component;

@Component
public class ApplicationStartedEventHolderBean {
	private Boolean eventFired = false;
	 
    public Boolean getEventFired() {
        return eventFired;
    }
 
    public void setEventFired(Boolean eventFired) {
        this.eventFired = eventFired;
    }
}
