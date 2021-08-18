package com.elastic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elastic.entity.User;
import com.elastic.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUserDetails() {
		log.info("inside getAllUserDetails of UserService");
		return userRepository.findAllUser();
	}

	public List<User> getUserByName(String userName) {

		log.info("inside getUserByName of UserService");
		return userRepository.findAllUserByName(userName);
	}

	public List<User> getUserByNameAddress(String userName, String address) {

		log.info("inside getUserByNameAddress of UserService");
		return userRepository.findAllUserByNameAddress(userName,address);
	}


}
