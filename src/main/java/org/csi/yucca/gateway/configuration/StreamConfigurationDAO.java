package org.csi.yucca.gateway.configuration;

import org.csi.yucca.gateway.configuration.dto.StreamConfiguration;
import org.csi.yucca.gateway.configuration.dto.StreamMetadata;
import org.csi.yucca.gateway.configuration.dto.StreamMetadataRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class StreamConfigurationDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void saveStreamMetadataConfiguration(StreamConfiguration streamConfiguration, String streamJson) {

		String tenantCode = streamConfiguration.getConfigData().getTenantCode();
		String streamCode = streamConfiguration.getStreamCode();
		String virtualEntityCode = streamConfiguration.getStreams().getStream().getVirtualEntityCode();
		Long deploymentVersion = new Long(streamConfiguration.getStreams().getStream().getDeploymentVersion());

		StreamMetadata existingStreamMetadata = findStreamMetadataConfigurationByPk(tenantCode, streamCode, virtualEntityCode, deploymentVersion);

		if (existingStreamMetadata == null) {

			String sql = "INSERT INTO STREAM_METADATA_CONFIGURATION (TENANT_CODE, STREAM_CODE, VIRTUALENTITY_CODE, DEPLOYMENT_VERSION, STREAM_NAME, VIRTUALENTITY_NAME, "
					+ "VIRTUALENTITY_DESCRIPTION, VIRTUALENTITY_TYPE, VIRTUALENTITY_CATEGORY, LASTUPDATE_TIMESTAMP, METADATA_JSON, SCHEMA_JSON) VALUES (?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?)";
			jdbcTemplate.update(sql,
					new Object[] { tenantCode, streamCode, virtualEntityCode, deploymentVersion, streamConfiguration.getStreamName(),
							streamConfiguration.getStreams().getStream().getVirtualEntityName(), streamConfiguration.getStreams().getStream().getVirtualEntityDescription(),
							streamConfiguration.getStreams().getStream().getVirtualEntityType(), streamConfiguration.getStreams().getStream().getVirtualEntityCategory(), System.currentTimeMillis(),
						 streamConfiguration.getJsonMetadata(), streamConfiguration.getJSonSchema() });
		} else {
			String sql = "UPDATE STREAM_METADATA_CONFIGURATION  SET STREAM_NAME=?, VIRTUALENTITY_NAME=?, VIRTUALENTITY_DESCRIPTION=?, VIRTUALENTITY_TYPE=?, VIRTUALENTITY_CATEGORY=?, LASTUPDATE_TIMESTAMP=?, "
					+ "METADATA_JSON=?, SCHEMA_JSON=? WHERE TENANT_CODE=? AND STREAM_CODE=? AND VIRTUALENTITY_CODE=? AND DEPLOYMENT_VERSION=?";
			jdbcTemplate.update(sql,
					new Object[] { streamConfiguration.getStreamName(), streamConfiguration.getStreams().getStream().getVirtualEntityName(),
							streamConfiguration.getStreams().getStream().getVirtualEntityDescription(), streamConfiguration.getStreams().getStream().getVirtualEntityType(),
							streamConfiguration.getStreams().getStream().getVirtualEntityCategory(), System.currentTimeMillis(), streamConfiguration.getJsonMetadata(),
							streamConfiguration.getJSonSchema(), tenantCode, streamCode, virtualEntityCode, deploymentVersion });
		}

	}

	public StreamMetadata findStreamMetadataConfigurationByPk(String tenantCode, String streamCode, String virtualentityCode, Long deploymentVersion) {

		String sql = "SELECT TENANT_CODE, STREAM_CODE, VIRTUALENTITY_CODE, DEPLOYMENT_VERSION, STREAM_NAME, VIRTUALENTITY_NAME, VIRTUALENTITY_DESCRIPTION, VIRTUALENTITY_TYPE, "
				+ "VIRTUALENTITY_CATEGORY, LASTUPDATE_TIMESTAMP, METADATA_JSON, SCHEMA_JSON FROM STREAM_METADATA_CONFIGURATION " 
				+ "WHERE TENANT_CODE=? AND STREAM_CODE=? AND  VIRTUALENTITY_CODE=? AND DEPLOYMENT_VERSION =?";

		try {
			StreamMetadata streamMetadata = (StreamMetadata) jdbcTemplate.queryForObject(sql, new Object[] { tenantCode, streamCode, virtualentityCode, deploymentVersion },
					new StreamMetadataRowMapper());

			return streamMetadata;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
