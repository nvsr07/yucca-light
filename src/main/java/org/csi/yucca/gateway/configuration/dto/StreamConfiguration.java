package org.csi.yucca.gateway.configuration.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StreamConfiguration {
	private String streamCode;
	private String streamName;
	private ConfigData configData;
	private Streams streams;
	@JsonIgnore
	private String jsonMetadata;

	public StreamConfiguration() {
		super();
	}

	public String getStreamCode() {
		return streamCode;
	}

	public void setStreamCode(String streamCode) {
		this.streamCode = streamCode;
	}

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public ConfigData getConfigData() {
		return configData;
	}

	public void setConfigData(ConfigData configData) {
		this.configData = configData;
	}

	public Streams getStreams() {
		return streams;
	}

	public void setStreams(Streams streams) {
		this.streams = streams;
	}

	@JsonIgnore
	public String getJSonSchema() {
		if (getStreams() == null)
			setStreams(new Streams());
		if (getStreams().getStream() == null)
			getStreams().setStream(new Stream());

		String jsonSchema = "{";
		jsonSchema += "\"title\": \"" + getStreamCode() + " sensor schema\",";
		jsonSchema += "\"type\": \"object\",";
		jsonSchema += "\"properties\": {";
		jsonSchema += "\"stream\": {";
		jsonSchema += "\"type\": \"string\"";
		jsonSchema += "},";
		jsonSchema += "\"" + getStreams().getStream().getVirtualEntityType() + "\": {";
		jsonSchema += "\"type\": \"string\"";
		jsonSchema += "},";
		jsonSchema += "\"values\": {";
		jsonSchema += "\"type\": \"array\",";
		jsonSchema += "\"minItems\" : 1,";
		jsonSchema += "\"items\": { ";
		jsonSchema += "\"type\": \"object\",";
		jsonSchema += "\"properties\": {";
		jsonSchema += "\"time\": {";
		jsonSchema += "\"type\": \"string\"";
		jsonSchema += "},";
		jsonSchema += "\"components\" : {";
		jsonSchema += "\"type\": \"object\",";
		jsonSchema += "\"properties\": {";

		String requredComponentName = "";
		if (getStreams().getStream().getComponents() != null && getStreams().getStream().getComponents().getElement() != null) {
			int counter = 0;
			int size = getStreams().getStream().getComponents().getElement().size();
			for (Element component : getStreams().getStream().getComponents().getElement()) {
				counter++;
				String separator = counter < size ? "," : "";
				jsonSchema += "\"" + component.getComponentName() + "\" : {";
				jsonSchema += "\"type\" : \"" + component.getDataType() + "\"";
				jsonSchema += "}" + separator;
				requredComponentName += component.getComponentName() + separator;
			}
		}
		jsonSchema += "},";
		jsonSchema += "\"required\": [\"" + requredComponentName + "\"]";
		jsonSchema += "}";
		jsonSchema += "}";
		jsonSchema += "}";
		jsonSchema += "}";
		jsonSchema += "},";
		jsonSchema += "\"required\": [\"stream\", \"" + getStreams().getStream().getVirtualEntityType() + "\", \"values\"]";
		jsonSchema += "}";
		// https://github.com/csipiemonte/yucca-realtime/blob/master/stream_template_cepartifacts/cepconfig/src/main/esbconfig/api__deploy__tenant__sensor__stream__addTextResource.xml
		return jsonSchema;

	}

	public String getJsonMetadata() {
		return jsonMetadata;
	}

	public void setJsonMetadata(String jsonMetadata) {
		this.jsonMetadata = jsonMetadata;
	}

}
