package com.detrasoft.framework.core.resource;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

public class ResourceFunctions {
	public static String getText(Class<?> classe, String keyResource) {
		String textResource = null;

		if (classe.isAnnotationPresent(Resource.class)) {
			Resource Resource = classe.getAnnotation(Resource.class);
			String resourceDirectory = Resource.directory();
			if (resourceDirectory != null) {
				Locale locale = LocaleContextHolder.getLocale();
				ResourceBundle bundle = ResourceBundle.getBundle(resourceDirectory, locale);
				textResource = bundle.getString(keyResource);
			}
		}
		return textResource;
	}

	public static String getText(String resourcePath, String keyResource) {
		String textResource = null;
		Locale locale = LocaleContextHolder.getLocale();
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(resourcePath, locale);
			textResource = bundle.getString(keyResource);

		} catch (Exception e) {

		}
		return textResource;
	}
}