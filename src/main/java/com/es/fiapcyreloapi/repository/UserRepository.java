package com.es.fiapcyreloapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.es.fiapcyreloapi.dao.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
	User findByValidationCode(String validationCode);
}
