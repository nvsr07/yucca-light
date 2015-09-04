package org.csi.yucca.gateway.persist.entity;

import java.io.Serializable;

public class AttemptId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String GW_ID;
	private String ATTEMPT_ID;
		
	public AttemptId() {
		super();
	}
	public AttemptId(String gW_ID, String aTTEMPT_ID) {
		super();
		GW_ID = gW_ID;
		ATTEMPT_ID = aTTEMPT_ID;
	}
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

}
