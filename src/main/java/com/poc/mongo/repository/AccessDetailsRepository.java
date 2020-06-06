package com.poc.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.poc.mongo.model.AccessDetails;

public interface AccessDetailsRepository extends MongoRepository<AccessDetails, String> {

}
