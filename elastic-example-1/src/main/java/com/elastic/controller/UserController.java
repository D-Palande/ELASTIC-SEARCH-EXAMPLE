package com.elastic.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elastic.entity.User;
import com.elastic.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value ="/alluser", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUserDetails(){
		log.info("inside getAllUserDetails of UserController");
		return userService.getAllUserDetails();
	}
	
	@GetMapping(value ="/alluser/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserByName(@PathVariable String userName){
		log.info("inside getUserByName of UserController");
		return userService.getUserByName(userName);
	}
	
	@GetMapping(value ="/alluser/{userName}/{address}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserByNameAddress(@PathVariable String userName, @PathVariable String address){
		log.info("inside getUserByNameAddress of UserController");
		return userService.getUserByNameAddress(userName, address);
	}
	
}
