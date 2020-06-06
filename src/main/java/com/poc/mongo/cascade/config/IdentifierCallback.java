package com.poc.mongo.cascade.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import java.lang.reflect.Field;

public class IdentifierCallback implements FieldCallback {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private boolean idFound;

	public @Override void doWith(final Field field) throws IllegalArgumentException {
		LOG.info("****** IdentifierCallback: doWith()****** ");
		ReflectionUtils.makeAccessible(field);

		if (field.isAnnotationPresent(Id.class)) {
			idFound = true;
		}
	}

	public boolean isIdFound() {
		return idFound;
	}
}
