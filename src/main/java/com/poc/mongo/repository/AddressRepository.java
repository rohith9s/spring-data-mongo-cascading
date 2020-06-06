package com.poc.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.poc.mongo.model.Address;

public interface AddressRepository extends MongoRepository<Address, String> {

}
