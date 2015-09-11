package org.csi.yucca.gateway.persist.entity;


import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.csi.yucca.gateway.persist.entity.StreamMetadataEntity.MetadataKey;

@Entity
@Table(name = "STREAM_METADATA_CONFIGURATION")
@IdClass(MetadataKey.class)
public class StreamMetadataEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	@Id
	private String TENANT_CODE;
	
	@Id
	private String STREAM_CODE;

	@Id
	private String VIRTUALENTITY_CODE;

	@Id
	private Long DEPLOYMENT_VERSION;




	public String getTENANT_CODE() {
		return TENANT_CODE;
	}

	public void setTENANT_CODE(String tENANT_CODE) {
		TENANT_CODE = tENANT_CODE;
	}

	public String getSTREAM_CODE() {
		return STREAM_CODE;
	}

	public void setSTREAM_CODE(String sTREAM_CODE) {
		STREAM_CODE = sTREAM_CODE;
	}

	public String getVIRTUALENTITY_CODE() {
		return VIRTUALENTITY_CODE;
	}

	public void setVIRTUALENTITY_CODE(String vIRTUALENTITY_CODE) {
		VIRTUALENTITY_CODE = vIRTUALENTITY_CODE;
	}

	public Long getDEPLOYMENT_VERSION() {
		return DEPLOYMENT_VERSION;
	}

	public void setDEPLOYMENT_VERSION(Long dEPLOYMENT_VERSION) {
		DEPLOYMENT_VERSION = dEPLOYMENT_VERSION;
	}




	@Column(name = "STREAM_NAME", nullable = false)
	private String STREAM_NAME;

	
	@Column(name = "VIRTUALENTITY_NAME", nullable = false)
	private String VIRTUALENTITY_NAME;

	@Column(name = "VIRTUALENTITY_DESCRIPTION", nullable = false)
	private String VIRTUALENTITY_DESCRIPTION;

	@Column(name = "VIRTUALENTITY_TYPE", nullable = false)
	private String VIRTUALENTITY_TYPE;

	@Column(name = "VIRTUALENTITY_CATEGORY", nullable = false)
	private String VIRTUALENTITY_CATEGORY;

	@Column(name = "LASTUPDATE_TIMESTAMP", nullable = false)
	private Long LASTUPDATE_TIMESTAMP;

	@Column(name = "METADATA_JSON", nullable = false)
	private String METADATA_JSON;

	@Column(name = "SCHEMA_JSON", nullable = false)	
	private String SCHEMA_JSON;
	
	
	
	

	public String getSTREAM_NAME() {
		return STREAM_NAME;
	}

	public void setSTREAM_NAME(String sTREAM_NAME) {
		STREAM_NAME = sTREAM_NAME;
	}

	public String getVIRTUALENTITY_NAME() {
		return VIRTUALENTITY_NAME;
	}

	public void setVIRTUALENTITY_NAME(String vIRTUALENTITY_NAME) {
		VIRTUALENTITY_NAME = vIRTUALENTITY_NAME;
	}

	public String getVIRTUALENTITY_DESCRIPTION() {
		return VIRTUALENTITY_DESCRIPTION;
	}

	public void setVIRTUALENTITY_DESCRIPTION(String vIRTUALENTITY_DESCRIPTION) {
		VIRTUALENTITY_DESCRIPTION = vIRTUALENTITY_DESCRIPTION;
	}

	public String getVIRTUALENTITY_TYPE() {
		return VIRTUALENTITY_TYPE;
	}

	public void setVIRTUALENTITY_TYPE(String vIRTUALENTITY_TYPE) {
		VIRTUALENTITY_TYPE = vIRTUALENTITY_TYPE;
	}

	public String getVIRTUALENTITY_CATEGORY() {
		return VIRTUALENTITY_CATEGORY;
	}

	public void setVIRTUALENTITY_CATEGORY(String vIRTUALENTITY_CATEGORY) {
		VIRTUALENTITY_CATEGORY = vIRTUALENTITY_CATEGORY;
	}

	public Long getLASTUPDATE_TIMESTAMP() {
		return LASTUPDATE_TIMESTAMP;
	}

	public void setLASTUPDATE_TIMESTAMP(Long lASTUPDATE_TIMESTAMP) {
		LASTUPDATE_TIMESTAMP = lASTUPDATE_TIMESTAMP;
	}

	public String getMETADATA_JSON() {
		return METADATA_JSON;
	}

	public void setMETADATA_JSON(String mETADATA_JSON) {
		METADATA_JSON = mETADATA_JSON;
	}

	public String getSCHEMA_JSON() {
		return SCHEMA_JSON;
	}

	public void setSCHEMA_JSON(String sCHEMA_JSON) {
		SCHEMA_JSON = sCHEMA_JSON;
	}




	@Access (AccessType.FIELD)
	public static class MetadataKey implements Serializable{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 2L;

		protected MetadataKey () {
			
		}
		protected MetadataKey (String TENANT_CODE,String STREAM_CODE,String VIRTUALENTITY_CODE,Long DEPLOYMENT_VERSION) {
			this.TENANT_CODE=TENANT_CODE;
			this.STREAM_CODE=STREAM_CODE;
			this.VIRTUALENTITY_CODE=VIRTUALENTITY_CODE;
			this.DEPLOYMENT_VERSION=DEPLOYMENT_VERSION;
			
		}
		
		@Column(name = "TENANT_CODE", nullable = false)
		private String TENANT_CODE;
		
		@Column(name = "STREAM_CODE", nullable = false)
		private String STREAM_CODE;

		@Column(name = "VIRTUALENTITY_CODE", nullable = false)
		private String VIRTUALENTITY_CODE;

		@Column(name = "DEPLOYMENT_VERSION", nullable = false)
		private Long DEPLOYMENT_VERSION;

		public String getTENANT_CODE() {
			return TENANT_CODE;
		}

		public void setTENANT_CODE(String tENANT_CODE) {
			TENANT_CODE = tENANT_CODE;
		}

		public String getSTREAM_CODE() {
			return STREAM_CODE;
		}

		public void setSTREAM_CODE(String sTREAM_CODE) {
			STREAM_CODE = sTREAM_CODE;
		}

		public String getVIRTUALENTITY_CODE() {
			return VIRTUALENTITY_CODE;
		}

		public void setVIRTUALENTITY_CODE(String vIRTUALENTITY_CODE) {
			VIRTUALENTITY_CODE = vIRTUALENTITY_CODE;
		}

		public Long getDEPLOYMENT_VERSION() {
			return DEPLOYMENT_VERSION;
		}

		public void setDEPLOYMENT_VERSION(Long dEPLOYMENT_VERSION) {
			DEPLOYMENT_VERSION = dEPLOYMENT_VERSION;
		}	
		
	}

}
