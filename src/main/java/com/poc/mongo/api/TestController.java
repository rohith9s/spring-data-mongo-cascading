package com.poc.mongo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.mongo.model.AccessDetails;
import com.poc.mongo.model.Address;
import com.poc.mongo.model.User;
import com.poc.mongo.repository.AccessDetailsRepository;
import com.poc.mongo.repository.AddressRepository;
import com.poc.mongo.repository.UserRepository;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccessDetailsRepository accessDetailsRepository;
	@Autowired
	private AddressRepository addressRepository;

	/**
	 * pass requestBody to enhance, am going with hard coded data, as our intention
	 * is implement cascading operations on mongodb
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saveUser", method = RequestMethod.GET)
	@ResponseBody
	public String saveUser() {
		String accountName = "hari@gmail" + UUID.randomUUID().toString() + ".com";
		try {
			Address address = new Address();
			address.set_id(UUID.randomUUID().toString());
			address.setStreetName("Gandhi Nagar");
			address.setCity("Hyderabad");
			address.setCountry("India");

			AccessDetails accessDetail = new AccessDetails();
			accessDetail.set_id(UUID.randomUUID().toString());
			accessDetail.setPlaceOfAccess("Hyderabad");
			accessDetail.setIpAddress("localhost");

			User user = new User();
			user.set_id(UUID.randomUUID().toString());
			user.setUserName(accountName);
			user.setAddress(address);
			List<AccessDetails> accessDetails = new ArrayList<>();
			accessDetails.add(accessDetail);
			user.setAccessDetails(accessDetails);

			User savedUser = userRepository.save(user);
			LOG.info("saved user details:", savedUser);
			return accountName;
		} catch (Exception e) {
			LOG.error("failed to save:", e);
			return "failed to create user";
		}

	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	@ResponseBody
	public boolean deleteUser(@RequestParam("userName") String userName) {
		try {
			User findByUserName = userRepository.findByUserName(userName);
			List<AccessDetails> accessDetails = findByUserName.getAccessDetails();
			Address address = findByUserName.getAddress();
			userRepository.deleteById(findByUserName.get_id());
			LOG.info("user is deleted for userName.." + userName);
			for (AccessDetails accessDetail : accessDetails) {
				AccessDetails accessDetailsById = accessDetailsRepository.findById(accessDetail.get_id()).orElse(null);
				LOG.info("accessDetails associated with user is deleted :" + accessDetailsById);
			}

			User userFindById = userRepository.findById(address.get_id()).orElse(null);
			LOG.info("address associated with user is deleted:::" + userFindById);
			return true;
		} catch (Exception e) {
			LOG.error("failed to delete:", e);
			return false;
		}

	}
}
