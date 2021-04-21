package com.es.fiapcyreloapi.dao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.es.fiapcyreloapi.enumerable.Status;
import com.es.fiapcyreloapi.enumerable.UserType;

@Entity
@Table(name = "\"user\"")
@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	private long userId;
	private String name;
	private String lastName;
	private String email;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Temporal(TemporalType.DATE)
	private Date createdIn;
	
	private String validationCode;

	public User() {}
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@PrePersist
	void onCreate() {
		this.createdIn = new Date();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreatedIn() {
		return createdIn;
	}

	public void setCreatedIn(Date createdIn) {
		this.createdIn = createdIn;
	}

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
