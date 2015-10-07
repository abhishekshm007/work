package com.o9paathshala.institute.dto;

public class InstituteDTO {

	private Integer id;
	private String instituteName;
	private String instituteType;
	private String email;
	private String state;
	private String cityName;
	private String password;
	private String country;
	private Boolean isActivated;
	private String contactNo;
	private String activationLink;
	private String confirmEmail;
	
	
	public String getConfirmEmail() {
		return confirmEmail;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getInstituteType() {
		return instituteType;
	}
	public void setInstituteType(String instituteType) {
		this.instituteType = instituteType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Boolean getIsActivated() {
		return isActivated;
	}
	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getActivationLink() {
		return activationLink;
	}
	public void setActivationLink(String activationLink) {
		this.activationLink = activationLink;
	}
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	@Override
	public String toString() {
		return "Institute [id=" + id + ", instituteName=" + instituteName
				+ ", instituteType=" + instituteType + ", email=" + email
				+ ", state=" + state + ", cityName=" + cityName + ", password="
				+ password + ", country=" + country + ", isActivated="
				+ isActivated + ", contactNo=" + contactNo
				+ ", activationLink=" + activationLink + ", confirmEmail="
				+ confirmEmail + "]";
	}

	

	
	
}
