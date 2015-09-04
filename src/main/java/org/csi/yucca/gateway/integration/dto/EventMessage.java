package org.csi.yucca.gateway.integration.dto;

import java.io.Serializable;

public class EventMessage implements Serializable{

	private String sourceCode;	
	private String streamCode;
	private boolean application;
	private String measures;
	public boolean isApplication() {
		return application;
	}


	public void setApplication(boolean application) {
		this.application = application;
	}




	public EventMessage() {
	}
	
	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getStreamCode() {
		return streamCode;
	}

	public void setStreamCode(String streamCode) {
		this.streamCode = streamCode;
	}


	public String getMeasures() {
		return measures;
	}

	public void setMeasures(String measures) {
		this.measures = measures;
	}


	public String toString() {
		StringBuilder str = new StringBuilder("{ \"stream\":\"").append(getStreamCode()).append("\",");
		str.append("\"").append(isApplication()?"application\":\"":"sensor\":\"").append(getSourceCode()).append("\",");
		str.append("\"values\":").append(getMeasures()).append("}");
		return str.toString();
	}



}
