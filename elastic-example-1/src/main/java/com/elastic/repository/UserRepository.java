package com.elastic.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.elastic.entity.User;

@Repository
public interface UserRepository {

	List<User> findAllUser();

	List<User> findAllUserByName(String userName);

	List<User> findAllUserByNameAddress(String userName, String address);

	
}