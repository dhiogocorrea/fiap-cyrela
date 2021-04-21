package com.es.fiapcyreloapi.dto;

public class UserChangePasswordDto {

	String password;
	String newPassword;
	String newPasswordConfirmation;
	
	public UserChangePasswordDto() {}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordConfirmation() {
		return newPasswordConfirmation;
	}
	public void setNewPasswordConfirmation(String newPasswordConfirmation) {
		this.newPasswordConfirmation = newPasswordConfirmation;
	}
	
	
}
