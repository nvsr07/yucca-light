package org.csi.yucca.gateway.configuration.dto;

import java.util.List;

public class StreamTags {

	private List<Tags> tags;

	public StreamTags() {
		super();
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

}
