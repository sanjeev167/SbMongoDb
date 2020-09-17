/**
 * 
 */
package com.pon.ctrl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pon.dal.UserDAL;
import com.pon.model.User;
import com.pon.repo.UserRepository;

/**
 * @author Sanjeev
 *
 */
@RestController
@RequestMapping(value = "/")
public class UserController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;

	private final UserDAL userDAL;

	public UserController(UserRepository userRepository, UserDAL userDAL) {
		this.userRepository = userRepository;
		this.userDAL = userDAL;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		LOG.info("Getting all users.");
		return userRepository.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID: {}.", userId);
		// return userRepository.findOne(userId); //Use when you are using Spring Data MongoDB – MongoRepository
		// MongoDB – MongoRepository
		return userDAL.getUserById(userId); // Use when you are using Spring Data MongoDB – MongoTemplate

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		LOG.info("Saving user.");
	
		 return userRepository.save(user); //Use when you are using Spring Data MongoDB – MongoRepository
		// MongoDB – MongoRepository
		//return userDAL.addNewUser(user);// Use when you are using Spring Data MongoDB – MongoTemplate
	}

	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {
		// User user = userRepository.findOne(userId);//Use when you are using Spring Data MongoDB – MongoRepository
		// Data MongoDB – MongoRepository
		User user = userDAL.getUserById(userId);// Use when you are using Spring Data MongoDB – MongoTemplate

		if (user != null) {
			return user.getUserSettings();
		} else {
			return "User not found.";
		}
	}

	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		// User user = userRepository.findOne(userId);//Use when you are using Spring Data MongoDB – MongoRepository
		
		User user = userDAL.getUserById(userId);// Use when you are using Spring Data MongoDB – MongoTemplate
		if (user != null) {
			return user.getUserSettings().get(key);
		} else {
			return "User not found.";
		}
	}

	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
	public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		// User user = userRepository.findOne(userId);//Use when you are using Spring Data MongoDB – MongoRepository
		
		User user = userDAL.getUserById(userId);// Use when you are using Spring Data MongoDB – MongoTemplate
		if (user != null) {
			user.getUserSettings().put(key, value);
			userRepository.save(user);
			return "Key added";
		} else {
			return "User not found.";
		}
	}

}