package com.klipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klipkart.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	
	public User findByUsername(String username);
}
