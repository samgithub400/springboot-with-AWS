package com.ios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ios.exception.UserNotFoundException;
import com.ios.model.User;
import com.ios.notes.GetValuesFromApplicationPropertiesFile;
import com.ios.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/userapp")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GetValuesFromApplicationPropertiesFile getData;
	
	
//print the values as a log from application.properties file
	@GetMapping("/printlog")
	public String printLog() {
		getData.printingAsLog();
		return "Please Check The Console..!";
	}
	
	// create user api
	@PostMapping("/saveuser")
	public User createUser(@RequestBody User user) {
		log.info("Create User Call Started...!");
		return userService.saveUser(user);
	}

	// get all users api
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		log.info("getAllUsers call Stared..!");
		return userService.getAllUsers();
	}

	// get user by id api
	@GetMapping("/getuserbyid/{userId}")
	public User getUserById(@PathVariable("userId") long userId) throws UserNotFoundException {
		log.info("get user call started with userId:{}",userId);
		return userService.getUserById(userId);
	}

	// update user api
	@PutMapping("/updateuser/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable("userId") long userId) throws UserNotFoundException {
		log.info("Update user call started..");
		return userService.updateUser(user, userId);
	}

	// delete user api
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable("userId") long userId) throws UserNotFoundException {
		log.info("Delete User call stared..");
		return userService.deleteUser(userId);
	}
}
