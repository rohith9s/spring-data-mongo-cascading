package com.poc.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.poc.mongo.model.AccessDetails;
import com.poc.mongo.model.Address;
import com.poc.mongo.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findDistinctFirstByAddress(Address address);

	List<AccessDetails> findByAccessDetails(AccessDetails accessDetails);

	User findByUserName(String userName);
}
