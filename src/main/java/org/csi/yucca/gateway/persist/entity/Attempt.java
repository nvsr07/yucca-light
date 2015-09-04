package org.csi.yucca.gateway.persist.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "ATTEMPT_HISTORY")
@IdClass(AttemptId.class)
public class Attempt  { 

	@Id
	@Column(name = "GW_ID", nullable = false)
	private String GW_ID;

	@Id
	@Column(name = "ATTEMPT_ID", nullable = false)
	private String ATTEMPT_ID;
	
	@Column(name = "ATTEMPT_SEND_TIMESTAMP", nullable = false)
	private Long ATTEMPT_SEND_TIMESTAMP;

	@Column(name = "ATTEMPT_RECEIVE_TIMESTAMP", nullable = false)
	private Long ATTEMPT_RECEIVE_TIMESTAMP;

	@Column(name = "RESPONSE", nullable = false)
	private String RESPONSE;

	@Column(name = "ENDPOINT", nullable = false)
	private Boolean ENDPOINT;

	public String getGW_ID() {
		return GW_ID;
	}

	public void setGW_ID(String gW_ID) {
		GW_ID = gW_ID;
	}

	public String getATTEMPT_ID() {
		return ATTEMPT_ID;
	}

	public void setATTEMPT_ID(String aTTEMPT_ID) {
		ATTEMPT_ID = aTTEMPT_ID;
	}

	public Long getATTEMPT_SEND_TIMESTAMP() {
		return ATTEMPT_SEND_TIMESTAMP;
	}

	public void setATTEMPT_SEND_TIMESTAMP(Long aTTEMPT_SEND_TIMESTAMP) {
		ATTEMPT_SEND_TIMESTAMP = aTTEMPT_SEND_TIMESTAMP;
	}

	public Long getATTEMPT_RECEIVE_TIMESTAMP() {
		return ATTEMPT_RECEIVE_TIMESTAMP;
	}

	public void setATTEMPT_RECEIVE_TIMESTAMP(Long aTTEMPT_RECEIVE_TIMESTAMP) {
		ATTEMPT_RECEIVE_TIMESTAMP = aTTEMPT_RECEIVE_TIMESTAMP;
	}

	public String getRESPONSE() {
		return RESPONSE;
	}

	public void setRESPONSE(String rESPONSE) {
		RESPONSE = rESPONSE;
	}

	public Boolean getENDPOINT() {
		return ENDPOINT;
	}

	public void setENDPOINT(Boolean eNDPOINT) {
		ENDPOINT = eNDPOINT;
	}
}
