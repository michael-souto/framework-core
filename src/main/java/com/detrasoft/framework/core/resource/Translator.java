package com.detrasoft.framework.core.resource;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.detrasoft.framework.enums.CodeMessages;

@Component
public class Translator {

	private static MessageSource messageSource;
	Translator(MessageSource messageSource) {
		Translator.messageSource = messageSource;
	}

	public static String getTranslatedText(CodeMessages msgCode) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(msgCode.toString().toLowerCase(), null, locale);
	}

	public static String getTranslatedText(CodeMessages msgCode, Object[] ...args) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(msgCode.toString().toLowerCase(), args, locale);
	}

	public static String getTranslatedText(String msgCode) {
		return getTranslatedText(msgCode, false);
	}
	
	public static String getTranslatedText(String msgCode, boolean codeToLowerCase) {
		Locale locale = LocaleContextHolder.getLocale();
		String codeToUse = codeToLowerCase ? msgCode.toLowerCase() : msgCode;
		return messageSource.getMessage(codeToUse, null, locale);
	}
	
	public static String getTranslatedText(String msgCode, Object ...args) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(msgCode.toLowerCase(), args, locale);
	}
}
