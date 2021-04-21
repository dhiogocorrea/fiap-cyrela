package com.es.fiapcyreloapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.es.fiapcyreloapi.dao.User;
import com.es.fiapcyreloapi.dto.UserChangePasswordDto;
import com.es.fiapcyreloapi.dto.UserDto;
import com.es.fiapcyreloapi.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userService;

	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/users/me", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public User getMe() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.getByEmail(auth.getName());
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody UserDto customerDto) {
		userService.create(customerDto);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/users/validate/{validationCode}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void validate(@PathVariable("validationCode") String validationCode) {
		userService.validate(validationCode);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/users/changepassword", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePassword(@RequestBody UserChangePasswordDto customerChangePasswordDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User c = userService.getByEmail(auth.getName());
		userService.updatePassword(c, customerChangePasswordDto);
	}
}
