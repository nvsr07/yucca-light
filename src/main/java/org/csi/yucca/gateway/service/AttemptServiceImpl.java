package org.csi.yucca.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.csi.yucca.gateway.service.dto.AttemptDto;
import org.csi.yucca.gateway.service.dto.AttemptDto.AttemptKey;

@Service
public class AttemptServiceImpl implements AttemptService  {

	private static final Logger log = Logger.getLogger("org.csi.yucca.gateway.service.AttemptServiceImpl");

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public AttemptDto findOne(AttemptKey id) {
		log.debug("[AttemptServiceImpl::findOne] START");
		
		String sql =  "SELECT "
							+ " GW_ID, "
							+ " ATTEMPT_ID, "
							+ " ATTEMPT_SEND_TIMESTAMP, "
							+ " ATTEMPT_RECEIVE_TIMESTAMP, "
							+ " FROM_STATUS, "
							+ " TO_STATUS, "
							+ " RESPONSE, "
							+ " RESPONSE_DETAIL, "
							+ " ENDPOINT "
					+ " FROM    ATTEMPT_HISTORY "
					+ " WHERE  ATTEMPT_HISTORY.GW_ID = ? AND ATTEMPT_HISTORY.ATTEMPT_ID = ?"
					+ " ORDER BY LAST_ATTEMPT_SEND_TIMESTAMP DESC";

		log.debug("[AttemptServiceImpl::findOne] - SQL " + sql);
		System.out.println("=====> [AttemptServiceImpl::findOne] - SQL " + sql);

		try {
			AttemptDto attempt = jdbcTemplate.queryForObject(sql, new Object[] {id.getGW_ID(), id.getATTEMPT_ID()}, new RowMapper<AttemptDto>() {

				@Override
				public AttemptDto mapRow(ResultSet rs, int rowNum) throws SQLException {

					AttemptDto at = new AttemptDto();
					
					at.setAttemptGWId(rs.getString("GW_ID"));
					at.setAttemptId(rs.getLong("ATTEMPT_ID"));
					at.setAttemptSendTimestamp(rs.getLong("ATTEMPT_SEND_TIMESTAMP"));
					at.setAttemptReceiveTimestamp(rs.getLong("ATTEMPT_RECEIVE_TIMESTAMP"));
					at.setAttemptResponse(rs.getString("RESPONSE"));
					at.setAttemptFromStatus(rs.getString("FROM_STATUS"));
					at.setAttemptToStatus(rs.getString("TO_STATUS"));
					at.setAttemptResponse(rs.getString("RESPONSE"));
					at.setAttemptEndPoint(rs.getString("ENDPOINT"));
					
					return at;
				}
			});

			return attempt;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} finally {
			log.debug("[AttemptServiceImpl::findOne] END");
		}
	}

	@Override
	public List<AttemptDto> findAll(String gwId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttemptDto> findAll() {
		log.debug("[AttemptServiceImpl::findAll] START");
		
		String sql =  "SELECT "
							+ " GW_ID, "
							+ " GW_INSERT_TIMESTAMP, "
							+ " GW_STATUS, "
							+ " SOURCE_CODE, "
							+ " STREAM_CODE, "
							+ " IS_APPLICATION, "
							+ " VALUES_JSON, "
							+ " LAST_ATTEMPT_ID, "
							+ " LAST_ATTEMPT_SEND_TIMESTAMP, "
							+ " LAST_ATTEMPT_RECEIVE_TIMESTAMP, "
							+ " LAST_RESPONSE, "
							+ " LAST_ENDPOINT, "
							+ " NUM_ATTEMPT "
					+ "FROM     EVENTS "
					+ "ORDER BY LAST_ATTEMPT_SEND_TIMESTAMP DESC";

		log.debug("[AttemptServiceImpl::findAll] - SQL " + sql);
		System.out.println("=====> [AttemptServiceImpl::findAll] - SQL " + sql);

		try {
			List<AttemptDto> events = jdbcTemplate.query(sql, new Object[] { }, new RowMapper<AttemptDto>() {

				@Override
				public AttemptDto mapRow(ResultSet rs, int rowNum) throws SQLException {

					AttemptDto at = new AttemptDto();
					
					at.setAttemptGWId(rs.getString("GW_ID"));
					at.setAttemptId(rs.getLong("ATTEMPT_ID"));
					at.setAttemptSendTimestamp(rs.getLong("ATTEMPT_SEND_TIMESTAMP"));
					at.setAttemptReceiveTimestamp(rs.getLong("ATTEMPT_RECEIVE_TIMESTAMP"));
					at.setAttemptResponse(rs.getString("RESPONSE"));
					at.setAttemptFromStatus(rs.getString("FROM_STATUS"));
					at.setAttemptToStatus(rs.getString("TO_STATUS"));
					at.setAttemptResponse(rs.getString("RESPONSE"));
					at.setAttemptEndPoint(rs.getString("ENDPOINT"));
					
					return at;
				}
			});

			return events;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} finally {
			log.debug("[AttemptServiceImpl::findAll] END");
		}
	}

	@Override
	public void save(AttemptDto dto) {
		// TODO Auto-generated method stub
		
	}
	
	
}
