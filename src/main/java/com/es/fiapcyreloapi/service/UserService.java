package com.es.fiapcyreloapi.service;

import com.es.fiapcyreloapi.dao.User;
import com.es.fiapcyreloapi.dto.UserChangePasswordDto;
import com.es.fiapcyreloapi.dto.UserDto;

public interface UserService extends BaseService<User, UserDto> {
	User getByEmail(String email);
	void validate(String validationCode);
	void updatePassword(User customer, UserChangePasswordDto customerChangePasswordDto);
}
