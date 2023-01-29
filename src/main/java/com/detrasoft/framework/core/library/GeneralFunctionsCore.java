package com.detrasoft.framework.core.library;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.detrasoft.framework.core.notification.Message;
import com.detrasoft.framework.core.notification.TypeMessage;

public class GeneralFunctionsCore {

	private static final String GMT = "America/Sao_Paulo";
	
	public static void clearMessagesSuccess(List<Message> listMessages) {
		Boolean temErro = false;
		for (Message msg : listMessages) {
			if (msg.getType() == TypeMessage.error) {
				temErro = true;
				break;
			}
		}
		if (temErro) {
			listMessages.removeIf(x -> x.getType() == TypeMessage.success);
		}
	}

	public static List<Message> clearMessagesDuplicate(List<Message> listMessages) {
		return listMessages.stream().distinct().collect(Collectors.toList());
	}

	public static boolean hasFatalError(List<Message> messages) {
		boolean fatalError = false;
		for (Message messageService : messages) {
			if (messageService.getType() == TypeMessage.error) {
				fatalError = true;
				break;
			} else
				fatalError = false;
		}
		return fatalError;
	}

	public static Date ObjectToDate(Object dateValue) {
		Date resultDate = null;
		if (dateValue != null) {
			if (dateValue.getClass().equals(Timestamp.class)) {
				resultDate = new Date(((Timestamp) dateValue).getTime());
			}
			if (dateValue.getClass().equals(java.sql.Date.class)) {
				resultDate = new Date(((java.sql.Date) dateValue).getTime());
			}
		}
		return resultDate;
	}

	public static LocalDate ObjectToLocalDate(Object dateValue) {
		LocalDate resultDate = null;
		if (dateValue != null) {
			if (dateValue.getClass().equals(Timestamp.class)) {
				resultDate = ((Timestamp) dateValue).toLocalDateTime().toLocalDate();
			}
			if (dateValue.getClass().equals(java.sql.Date.class)) {
				resultDate = ((java.sql.Date) dateValue).toLocalDate();
			}
		}
		return resultDate;
	}
	
	public static LocalDateTime ObjectToLocalDateTime(Object dateValue) {
		LocalDateTime resultDate = null;
		if (dateValue != null) {
			if (dateValue.getClass().equals(Timestamp.class)) {
				resultDate = ((Timestamp) dateValue).toLocalDateTime();
			}
			if (dateValue.getClass().equals(java.sql.Date.class)) {
				resultDate = ((java.sql.Date) dateValue).toLocalDate().atTime(0,0,0);
			}
		}
		return resultDate;
	}
	
	public static ZonedDateTime ObjectToZonedDateTime(Object dateValue) {
		ZonedDateTime resultDate = null;
		if (dateValue != null) {
			if (dateValue.getClass().equals(Timestamp.class)) {
				var d = ((Timestamp) dateValue).toInstant() ;
				ZoneId z = ZoneId.of("UTC");
				resultDate = d.atZone(z);
			}
		}
		return resultDate;
	}

	public static Long ObjectToLong(Object longValue) {
		Long result = null;
		if (longValue != null) {
			if (longValue.getClass().equals(String.class)) {
				result = Long.parseLong(longValue.toString());
			}
			if (longValue.getClass().equals(BigInteger.class)) {
				result = ((BigInteger) longValue).longValue();
			}
		}
		return result;
	}

	public static String ObjectToString(Object stringValue) {
		String result = null;
		if (stringValue != null) {
			result = stringValue.toString();
		}
		return result;
	}

	public static BigDecimal ObjectToBigDecimal(Object bigDecimalValue) {
		BigDecimal result = null;
		if (bigDecimalValue != null) {
			result = new BigDecimal(bigDecimalValue.toString());
		}
		return result;
	}

	public static Boolean ObjectToBoolean(Object booleanValue) {
		Boolean result = null;
		if (booleanValue != null) {
			result = Boolean.parseBoolean(booleanValue.toString());
		}
		return result;
	}
	
	public static LocalDateTime LocalDateTimeNow() {
		ZonedDateTime zdtAtET = ZonedDateTime.now(ZoneId.of(GMT));
		return zdtAtET.toLocalDateTime();
	}
	
	public static LocalDate LocalDateNow() {
		ZonedDateTime zdtAtET = ZonedDateTime.now(ZoneId.of(GMT));
		return zdtAtET.toLocalDate();
	}
	
	public static LocalTime LocalTimeNow() {
		ZonedDateTime zdtAtET = ZonedDateTime.now(ZoneId.of(GMT));
		return zdtAtET.toLocalTime();
	}

}
