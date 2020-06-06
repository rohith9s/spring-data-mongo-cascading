package com.poc.mongo.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.poc.mongo.cascade.config.Cascade;

public class User {

	private @Id String _id;
	private String userName;
	private @DBRef @Cascade Address address;
	private @DBRef @Cascade List<AccessDetails> accessDetails = new ArrayList<>();
	private ZonedDateTime created;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ZonedDateTime getCreated() {
		return created;
	}

	public void setCreated(ZonedDateTime created) {
		this.created = created;
	}

	public List<AccessDetails> getAccessDetails() {
		return accessDetails;
	}

	public void setAccessDetails(List<AccessDetails> accessDetails) {
		this.accessDetails = accessDetails;
	}

}
