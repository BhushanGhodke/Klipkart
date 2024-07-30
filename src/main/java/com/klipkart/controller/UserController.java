package com.klipkart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.klipkart.binding.LoginForm;
import com.klipkart.binding.RegistrationFrom;
import com.klipkart.exception.InvalidUsernameAndPassWordEx;
import com.klipkart.service.UserService;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Boolean> registerUser(@RequestBody RegistrationFrom registrationFrom){
		
		boolean status = userService.registerUser(registrationFrom);
		
		return new ResponseEntity<Boolean>(status, HttpStatus.CREATED);

	}

	
	@PostMapping("/login")
	public ResponseEntity<Boolean> LoginUser(@RequestBody LoginForm loginForm){
		
		boolean status = userService.loginCheck(loginForm);
		
		
			return new ResponseEntity<Boolean>(status, HttpStatus.OK);

	}
	
	
	
}
