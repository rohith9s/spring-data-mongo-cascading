package com.poc.mongo.cascade.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.util.ReflectionUtils;

public class CascadeMongoEventListener extends AbstractMongoEventListener<Object> {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private @Autowired MongoOperations mongoOperations;

	public @Override void onBeforeConvert(final BeforeConvertEvent<Object> event) {
		LOG.info("****** CascadeMongoEventListener: onBeforeConvert()****** ");
		final Object source = event.getSource();
		ReflectionUtils.doWithFields(source.getClass(), new CascadeSaveCallback(source, mongoOperations));
	}

	public @Override void onAfterConvert(AfterConvertEvent<Object> event) {
		LOG.info("****** CascadeMongoEventListener: onAfterConvert()****** ");
		final Object source = event.getSource();
		ReflectionUtils.doWithFields(source.getClass(), new CascadeDeleteCallback(source, mongoOperations));
	}
}
