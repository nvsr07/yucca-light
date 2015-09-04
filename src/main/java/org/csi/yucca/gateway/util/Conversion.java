package org.csi.yucca.gateway.util;

import java.sql.ResultSet;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.SimpleTimeZone;

import javax.xml.bind.DatatypeConverter;

import org.csi.yucca.gateway.api.ParseValidationUtil;
import org.csi.yucca.gateway.api.dto.Measure;
import org.csi.yucca.gateway.api.dto.StreamSensorEvent;
import org.csi.yucca.gateway.integration.dto.EventMessage;
import org.csi.yucca.gateway.integration.dto.MeasureMessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Conversion {

	public static EventMessage fromStreamSensorEvent2EventMessage(StreamSensorEvent eventDto) throws JsonProcessingException {
		if (eventDto!=null)
		{
			EventMessage eventMessage = new EventMessage();
			eventMessage.setSourceCode(eventDto.getSource());
			eventMessage.setStreamCode(eventDto.getStream());
			eventMessage.setMeasures(fromMeasureArray2String(eventDto.getValues()));
			eventMessage.setApplication(eventDto.getApplication()!=null && eventDto.getApplication().length()>0);
			return eventMessage;
		}
		return null;
	}

	public static String fromMeasureArray2String(Measure[] values) throws JsonProcessingException {
		if (values != null)
		{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(values);
		
		}
		return null;
	}

	public static Date fromStringToDate(String time) {
		Date o = null;
		SimpleDateFormat format = new SimpleDateFormat(ParseValidationUtil._msDateFormat);
		format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
		o = format.parse(time, new ParsePosition(0));
		if (o == null) {
			// try older format with no ms
			format = new SimpleDateFormat(ParseValidationUtil._secDateFormat);
			format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
			o = format.parse(time, new ParsePosition(0));
			if (o == null) {
				// try timezone
				format = new SimpleDateFormat(ParseValidationUtil._msDateFormat_TZ);
				format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
				o = format.parse(time, new ParsePosition(0));
				if (o == null) {
					// try older format timezone
					format = new SimpleDateFormat(ParseValidationUtil._secDateFormat_TZ);
					format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
					o = format.parse(time, new ParsePosition(0));
					 if (o == null) {
		                	// try isoDate with JAXB
		                	Calendar cal = DatatypeConverter.parseDateTime(time);
		                	o = cal.getTime();
		                }
				}
				
			}
		}
		return o;
	}



}
