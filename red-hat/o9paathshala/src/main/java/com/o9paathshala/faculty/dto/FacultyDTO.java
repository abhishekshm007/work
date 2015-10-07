package com.o9paathshala.faculty.dto;

import java.sql.Date;
import java.util.List;

public class FacultyDTO {

	private String name;
	private String email;
	private List<String> batches;
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
	public List<String> getBatches() {
		return batches;
	}
	public void setBatches(List<String> batches) {
		this.batches = batches;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Date dob;
	private Character gender;
	private String contact;
	private String address;
	private Integer id;

}
