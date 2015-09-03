package org.csi.yucca.gateway.service.dto;

//import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapping;

public class AttemptDto {

	@Mapping("GW_ID")
    private String attemptGWId;
	
	@Mapping("ATTEMPT_ID")
	private Long attemptId;

    @Mapping("ATTEMPT_SEND_TIMESTAMP")
    private Long attemptSendTimestamp;

    @Mapping("ATTEMPT_RECEIVE_TIMESTAMP")
    private Long attemptReceiveTimestamp;

    @Mapping("RESPONSE")
    private String attemptResponse;

    @Mapping("ENDPOINT")
    private String attemptEndPoint;

	public String getAttemptGWId() {
		return attemptGWId;
	}

	public void setAttemptGWId(String attemptGWId) {
		this.attemptGWId = attemptGWId;
	}

	public Long getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(Long attemptId) {
		this.attemptId = attemptId;
	}

	public Long getAttemptSendTimestamp() {
		return attemptSendTimestamp;
	}

	public void setAttemptSendTimestamp(Long attemptSendTimestamp) {
		this.attemptSendTimestamp = attemptSendTimestamp;
	}

	public Long getAttemptReceiveTimestamp() {
		return attemptReceiveTimestamp;
	}

	public void setAttemptReceiveTimestamp(Long attemptReceiveTimestamp) {
		this.attemptReceiveTimestamp = attemptReceiveTimestamp;
	}

	public String getAttemptResponse() {
		return attemptResponse;
	}

	public void setAttemptResponse(String attemptResponse) {
		this.attemptResponse = attemptResponse;
	}

	public String getAttemptEndPoint() {
		return attemptEndPoint;
	}

	public void setAttemptEndPoint(String attemptEndPoint) {
		this.attemptEndPoint = attemptEndPoint;
	}
}
