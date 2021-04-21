package com.es.fiapcyreloapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.es.fiapcyreloapi.dao.User;
import com.es.fiapcyreloapi.dto.UserChangePasswordDto;
import com.es.fiapcyreloapi.dto.UserDto;
import com.es.fiapcyreloapi.enumerable.Status;
import com.es.fiapcyreloapi.enumerable.UserType;
import com.es.fiapcyreloapi.repository.UserRepository;
import com.es.fiapcyreloapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public User getByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public List<User> getAll() {
		ArrayList<User> result = new ArrayList<User>();
		userRepository.findAll().forEach(result::add);
		return result;
	}

	@Override
	public User get(long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public void create(UserDto userDto) {
		if (this.getByEmail(userDto.getEmail()) != null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered.");
		}
		if(userDto.getPassword() != userDto.getConfirmationPassword()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password and Confirmation password does not matches.");
		}
		User user = new User();
		user.setName(userDto.getName());
		user.setLastName(userDto.getLastName());
		user.setCompanyName(userDto.getCompanyName());
		user.setEmail(userDto.getEmail());
		user.setStatus(Status.PENDING_VERIFICATION);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setValidationCode(UUID.randomUUID().toString());
		
		if (userDto.getUserType() == UserType.TECH_SUPPORT_PROFESSIONAL) {
			if (!userDto.getEmail().contains("@cyrelo.com.br")) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only allowed people can register as professional.");
			}
		} else if (userDto.getUserType() == UserType.ADMIN) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create admin user by this channel. Contact system admin.");
		}
		user.setUserType(userDto.getUserType());
		
		userRepository.save(user);
	}
	
	@Override
	public void validate(String validationCode) {
		User user = userRepository.findByValidationCode(validationCode);
		
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Validation code not found.");
		}
		
		if (user.getStatus() == Status.VERIFIED) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already verified.");
		}
		
		user.setStatus(Status.VERIFIED);
		userRepository.save(user);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updatePassword(User user, UserChangePasswordDto userChangePasswordDto) {
		if (userChangePasswordDto.getNewPassword() != userChangePasswordDto.getNewPasswordConfirmation()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password and Confirmation password does not matches.");
		}

		user.setPassword(passwordEncoder.encode(userChangePasswordDto.getNewPassword()));
		userRepository.save(user);
	}
}
