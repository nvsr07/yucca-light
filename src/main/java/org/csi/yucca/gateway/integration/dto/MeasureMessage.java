package org.csi.yucca.gateway.integration.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Deprecated
public class MeasureMessage implements Serializable{
	private Date time;
	private Map<String, Object> components;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Map<String, Object> getComponents() {
		return components;
	}
	public void setComponents(Map<String, Object> components) {
		this.components = components;
	}
}
