package com.klipkart.service;

import com.klipkart.binding.LoginForm;
import com.klipkart.binding.RegistrationFrom;

public interface UserService {

	public boolean registerUser(RegistrationFrom registrationFrom);
	
	public boolean loginCheck(LoginForm loginForm);
}
