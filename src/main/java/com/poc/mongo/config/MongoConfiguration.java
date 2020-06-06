package com.poc.mongo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.poc.mongo.cascade.config.CascadeMongoEventListener;

@EnableMongoRepositories("com.poc.mongo.repository")
public @Configuration class MongoConfiguration {
	private final List<Converter<?, ?>> converters = new ArrayList<>();

	public @Bean CascadeMongoEventListener cascadeMongoEventListener() {
		return new CascadeMongoEventListener();
	}

	public @Bean MongoCustomConversions customConversions() {
		// add if any converters 
		return new MongoCustomConversions(converters);
	}

}