package com.detrasoft.framework.core.library;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.detrasoft.framework.core.notification.Message;
import com.detrasoft.framework.core.notification.MessageType;

public class GeneralFunctionsCore {

	private static final String GMT = "America/Sao_Paulo";
	
	public static boolean checkEmpty(Map<String, Object> map) {
        for (Object value : map.values()) {
            if (value != null) {
                return false;
            }
        }
        return true;
    }
	
	public static void clearMessagesSuccess(List<Message> listMessages) {
		Boolean temErro = false;
		for (Message msg : listMessages) {
			if (msg.getType() == MessageType.error) {
				temErro = true;
				break;
			}
		}
		if (temErro) {
			listMessages.removeIf(x -> x.getType() == MessageType.success);
		}
	}

	public static List<Message> clearMessagesDuplicate(List<Message> listMessages) {
		return listMessages.stream().distinct().collect(Collectors.toList());
	}

	public static boolean hasFatalError(List<Message> messages) {
		boolean fatalError = false;
		for (Message messageService : messages) {
			if (messageService.getType() == MessageType.error) {
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

	public static boolean isValidUUID(String uuidString) {
		try {
			// Tenta criar um objeto UUID com a string fornecida
			UUID uuid = UUID.fromString(uuidString);

			// Se o objeto UUID for criado com sucesso, a string é um UUID válido
			return true;
		} catch (IllegalArgumentException e) {
			// Se ocorrer uma exceção, a string não é um UUID válido
			return false;
		}
	}

	public static String generateRandomNumber(int length) {
		SecureRandom random = new SecureRandom();

        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero.");
        }

        StringBuilder randomNumber = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            randomNumber.append(digit);
        }

        return randomNumber.toString();
    }

	public static String formatCamelCase(String text) {
		if (text == null || text.isEmpty()) {
			return text;
		}
		// Deixa a primeira letra minúscula e o restante permanece como está.
		return text.substring(0, 1).toLowerCase() + text.substring(1);
	}

	public static String convertToTitle(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }

        StringBuilder resultado = new StringBuilder();
        boolean proximoDeveSerMaiusculo = true;

        for (char caractere : texto.toCharArray()) {
            if (Character.isLetterOrDigit(caractere)) {
                if (proximoDeveSerMaiusculo) {
                    resultado.append(Character.toUpperCase(caractere));
                } else {
                    resultado.append(Character.toLowerCase(caractere));
                }
                proximoDeveSerMaiusculo = false;
            } else {
                resultado.append(caractere);
                proximoDeveSerMaiusculo = true;
            }
        }

        return resultado.toString();
    }
	
	public static String removeLeadingCharacters(String texto, char caractere) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }

        int index = 0;
        while (index < texto.length() && texto.charAt(index) == caractere) {
            index++;
        }

        return texto.substring(index);
    }

	public static String removeTrailingCharacters(String texto, char caractere) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }

        int index = texto.length() - 1;
        while (index >= 0 && texto.charAt(index) == caractere) {
            index--;
        }

        return texto.substring(0, index + 1);
    }
	
	public static String formatWithRegex(String texto, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);

        StringBuffer resultado = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(resultado, matcher.group().replaceAll(regex, "$1"));
        }
        matcher.appendTail(resultado);

        return resultado.toString();
    }
	
	public static String removeSpaces(String texto) {
        if (texto == null) {
            return null;
        }

        return texto.replaceAll("\\s", "");
    }
	
	public static String copySubstring(String texto, String intervalo) {
        if (texto == null || intervalo == null || intervalo.length() < 5) {
            return null;
        }

        int inicio = Integer.parseInt(intervalo.substring(0, intervalo.indexOf(".")));
        int fim = Integer.parseInt(intervalo.substring(intervalo.lastIndexOf(".") + 1));
        if (fim > texto.length()) {
        	fim = texto.length();
        }

        if (inicio < 1 || fim < 1 || inicio > fim) {
            return null;
        }

        return texto.substring(inicio - 1, fim);
    }
	
	public static String removeFormatting(String textoFormatado) {
        if (textoFormatado == null || textoFormatado.isEmpty()) {
            return "";
        }
        return textoFormatado.replaceAll("\\D", "");
    }

}
