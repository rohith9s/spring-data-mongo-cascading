package com.poc.mongo.cascade.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

public class CascadeSaveCallback implements FieldCallback {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final Object source;
	private final MongoOperations mongoOperations;

	public CascadeSaveCallback(Object source, MongoOperations mongoOperations) {
		LOG.info("****** CascadeSaveCallback: CascadeSaveCallback()****** ");
		this.source = source;
		this.mongoOperations = mongoOperations;
	}

	public @Override void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
		LOG.info("****** CascadeSaveCallback: doWith()****** ");
		ReflectionUtils.makeAccessible(field);

		if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(Cascade.class)) {
			final Object fieldValue = field.get(source);

			if (Objects.nonNull(fieldValue)) {
				IdentifierCallback callback = new IdentifierCallback();
				final CascadeType cascadeType = field.getAnnotation(Cascade.class).value();

				if (cascadeType.equals(CascadeType.SAVE) || cascadeType.equals(CascadeType.ALL)) {
					if (fieldValue instanceof Collection<?>) {
						((Collection<?>) fieldValue).forEach(mongoOperations::save);
					} else {
						ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
						mongoOperations.save(fieldValue);
					}
				}
			}
		}
	}
}
