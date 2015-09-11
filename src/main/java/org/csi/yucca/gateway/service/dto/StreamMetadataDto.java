package org.csi.yucca.gateway.service.dto;
import javax.persistence.Entity;

import org.dozer.Mapping;


public class StreamMetadataDto {

	
	@Mapping("TENANT_CODE")
	private String tenantCode;
	
	
	@Mapping("STREAM_CODE")
	private String streamCode;

	@Mapping("VIRTUALENTITY_CODE")
	private String virtualEntityCode;

	@Mapping("DEPLOYMENT_VERSION")
	private Long deploymentVersion;
	
	
	@Mapping("STREAM_NAME")
	private String streamName;

	
	@Mapping("VIRTUALENTITY_NAME")
	private String virtualEntityName;

	@Mapping("VIRTUALENTITY_DESCRIPTION")
	private String virtualEntityDescription;

	@Mapping("VIRTUALENTITY_TYPE")
	private String virtualEntityType;

	@Mapping("VIRTUALENTITY_CATEGORY")
	private String virtualEntityCategory;

	@Mapping("LASTUPDATE_TIMESTAMP")
	private Long lastUpdateTimestamp;

	@Mapping("METADATA_JSON")
	private String metadataJson;

	@Mapping("SCHEMA_JSON")	
	private String schemaJson;

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getStreamCode() {
		return streamCode;
	}

	public void setStreamCode(String streamCode) {
		this.streamCode = streamCode;
	}

	public String getVirtualEntityCode() {
		return virtualEntityCode;
	}

	public void setVirtualEntityCode(String virtualEntityCode) {
		this.virtualEntityCode = virtualEntityCode;
	}

	public Long getDeploymentVersion() {
		return deploymentVersion;
	}

	public void setDeploymentVersion(Long deploymentVersion) {
		this.deploymentVersion = deploymentVersion;
	}

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public String getVirtualEntityName() {
		return virtualEntityName;
	}

	public void setVirtualEntityName(String virtualEntityName) {
		this.virtualEntityName = virtualEntityName;
	}

	public String getVirtualEntityDescription() {
		return virtualEntityDescription;
	}

	public void setVirtualEntityDescription(String virtualEntityDescription) {
		this.virtualEntityDescription = virtualEntityDescription;
	}

	public String getVirtualEntityType() {
		return virtualEntityType;
	}

	public void setVirtualEntityType(String virtualEntityType) {
		this.virtualEntityType = virtualEntityType;
	}

	public String getVirtualEntityCategory() {
		return virtualEntityCategory;
	}

	public void setVirtualEntityCategory(String virtualEntityCategory) {
		this.virtualEntityCategory = virtualEntityCategory;
	}

	public Long getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}

	public void setLastUpdateTimestamp(Long lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}

	public String getMetadataJson() {
		return metadataJson;
	}

	public void setMetadataJson(String metadataJson) {
		this.metadataJson = metadataJson;
	}

	public String getSchemaJson() {
		return schemaJson;
	}

	public void setSchemaJson(String schemaJson) {
		this.schemaJson = schemaJson;
	}

}
