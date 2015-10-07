package com.o9paathshala.institute.dto;

public class InstituteRegistrationDTO {

	private String name;
	private String email;
	private String confirmEmail;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfirmEmail() {
		return confirmEmail;
	}
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "InstituteRegistrationDTO [name=" + name + ", email=" + email
				+ ", confirmEmail=" + confirmEmail + ", type=" + type + "]";
	}
	
	
}
