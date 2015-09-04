package org.csi.yucca.gateway.persist.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVENTS")
public class Event { 

	@Id
	@Column(name = "GW_ID", nullable = false)
	private String GW_ID;

	@Column(name = "GW_INSERT_TIMESTAMP", nullable = false)
	private String GW_INSERT_TIMESTAMP;
	
	@Column(name = "GW_STATUS", nullable = false)
	private String GW_STATUS;

	@Column(name = "SOURCE_CODE", nullable = false)
	private String SOURCE_CODE;

	@Column(name = "STREAM_CODE", nullable = false)
	private String STREAM_CODE;

	@Column(name = "IS_APPLICATION", nullable = false)
	private Boolean IS_APPLICATION;

	@Column(name = "VALUES_JSON", nullable = false)
	private String VALUES_JSON;

	@Column(name = "LAST_ATTEMPT_ID", nullable = false)
	private String LAST_ATTEMPT_ID;
	
	@Column(name = "LAST_ATTEMPT_SEND_TIMESTAMP", nullable = false)
	private Long LAST_ATTEMPT_SEND_TIMESTAMP;

	@Column(name = "LAST_ATTEMPT_RECEIVE_TIMESTAMP", nullable = false)
	private Long LAST_ATTEMPT_RECEIVE_TIMESTAMP;

	@Column(name = "LAST_RESPONSE", nullable = false)
	private String LAST_RESPONSE;

	@Column(name = "LAST_ENDPOINT", nullable = false)
	private Date LAST_ENDPOINT;

	@Column(name = "NUM_ATTEMPT", nullable = false)
	private Boolean NUM_ATTEMPT;

	public String getGW_ID() {
		return GW_ID;
	}

	public void setGW_ID(String gW_ID) {
		GW_ID = gW_ID;
	}

	public String getGW_INSERT_TIMESTAMP() {
		return GW_INSERT_TIMESTAMP;
	}

	public void setGW_INSERT_TIMESTAMP(String gW_INSERT_TIMESTAMP) {
		GW_INSERT_TIMESTAMP = gW_INSERT_TIMESTAMP;
	}

	public String getGW_STATUS() {
		return GW_STATUS;
	}

	public void setGW_STATUS(String gW_STATUS) {
		GW_STATUS = gW_STATUS;
	}

	public String getSOURCE_CODE() {
		return SOURCE_CODE;
	}

	public void setSOURCE_CODE(String sOURCE_CODE) {
		SOURCE_CODE = sOURCE_CODE;
	}

	public String getSTREAM_CODE() {
		return STREAM_CODE;
	}

	public void setSTREAM_CODE(String sTREAM_CODE) {
		STREAM_CODE = sTREAM_CODE;
	}

	public Boolean getIS_APPLICATION() {
		return IS_APPLICATION;
	}

	public void setIS_APPLICATION(Boolean iS_APPLICATION) {
		IS_APPLICATION = iS_APPLICATION;
	}

	public String getVALUES_JSON() {
		return VALUES_JSON;
	}

	public void setVALUES_JSON(String vALUES_JSON) {
		VALUES_JSON = vALUES_JSON;
	}

	public String getLAST_ATTEMPT_ID() {
		return LAST_ATTEMPT_ID;
	}

	public void setLAST_ATTEMPT_ID(String lAST_ATTEMPT_ID) {
		LAST_ATTEMPT_ID = lAST_ATTEMPT_ID;
	}

	public Long getLAST_ATTEMPT_SEND_TIMESTAMP() {
		return LAST_ATTEMPT_SEND_TIMESTAMP;
	}

	public void setLAST_ATTEMPT_SEND_TIMESTAMP(Long lAST_ATTEMPT_SEND_TIMESTAMP) {
		LAST_ATTEMPT_SEND_TIMESTAMP = lAST_ATTEMPT_SEND_TIMESTAMP;
	}

	public Long getLAST_ATTEMPT_RECEIVE_TIMESTAMP() {
		return LAST_ATTEMPT_RECEIVE_TIMESTAMP;
	}

	public void setLAST_ATTEMPT_RECEIVE_TIMESTAMP(Long lAST_ATTEMPT_RECEIVE_TIMESTAMP) {
		LAST_ATTEMPT_RECEIVE_TIMESTAMP = lAST_ATTEMPT_RECEIVE_TIMESTAMP;
	}

	public String getLAST_RESPONSE() {
		return LAST_RESPONSE;
	}

	public void setLAST_RESPONSE(String lAST_RESPONSE) {
		LAST_RESPONSE = lAST_RESPONSE;
	}

	public Date getLAST_ENDPOINT() {
		return LAST_ENDPOINT;
	}

	public void setLAST_ENDPOINT(Date lAST_ENDPOINT) {
		LAST_ENDPOINT = lAST_ENDPOINT;
	}

	public Boolean getNUM_ATTEMPT() {
		return NUM_ATTEMPT;
	}

	public void setNUM_ATTEMPT(Boolean nUM_ATTEMPT) {
		NUM_ATTEMPT = nUM_ATTEMPT;
	}
}
