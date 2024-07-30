package com.klipkart.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klipkart.binding.LoginForm;
import com.klipkart.binding.RegistrationFrom;
import com.klipkart.exception.InvalidUsernameAndPassWordEx;
import com.klipkart.model.User;
import com.klipkart.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean registerUser(RegistrationFrom registrationFrom) {
		User email = null;
		User savedUser = null;
		try {
			email = userRepository.findByEmail(registrationFrom.getEmail());
			if (email == null) {

				User user = new User();

				BeanUtils.copyProperties(registrationFrom, user);

				savedUser = userRepository.save(user);
			} else {
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return savedUser.getId() != null;
	}

	
	@Override
	public boolean loginCheck(LoginForm loginForm) {

		User user = userRepository.findByUsername(loginForm.getUsername());

		boolean status = user.getPassword().equals(loginForm.getPassword());

		if (status) {
			return status;
		}

		throw new InvalidUsernameAndPassWordEx("Invalid Username or Password");

	}
}
