package com.poc.mongo.model;

import org.springframework.data.annotation.Id;

public class AccessDetails {

	private @Id String _id;
	private String placeOfAccess;
	private String ipAddress;

	public AccessDetails() {
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getPlaceOfAccess() {
		return placeOfAccess;
	}

	public void setPlaceOfAccess(String placeOfAccess) {
		this.placeOfAccess = placeOfAccess;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "AccessDetails [_id=" + _id + ", placeOfAccess=" + placeOfAccess + ", ipAddress=" + ipAddress + "]";
	}

}
