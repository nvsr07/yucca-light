package org.csi.yucca.gateway.api.dto;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class StreamSensorEvent  {
	
	private String stream;	
	
	private String application;
	private String sensor;
	
	private Measure[] values;
	
	
	protected Response error(String input, Exception e) {
	    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                   .entity(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
	                   .build();
	}
	
	
	
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getSensor() {
		return sensor;
	}
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	public Measure[] getValues() {
		return values;
	}
	public void setValues(Measure[] values) {
		this.values = values;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	
	@JsonIgnore
	public String getSource()
	{
		return  ((getSensor()!=null)?getSensor():getApplication());
	}

	@JsonIgnore
	public String getSchema()
	{
		String source = getSource(); 
		return ""+source==null?"":source+"_"+getStream()+"_schema.json";
	}
	
}
