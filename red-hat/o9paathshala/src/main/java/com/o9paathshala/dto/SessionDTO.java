package com.o9paathshala.dto;

import java.io.Serializable;


public class SessionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private Integer id;
	private Boolean defaultInstituteId;
	private Integer currentInstituteId;
	private Integer type;
	private String instituteName;
	private Integer batchid;
	
	
	public Integer getBatchid() {
		return batchid;
	}
	public void setBatchid(Integer batchid) {
		this.batchid = batchid;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getDefaultInstituteId() {
		return defaultInstituteId;
	}
	public void setDefaultInstituteId(Boolean defaultInstituteId) {
		this.defaultInstituteId = defaultInstituteId;
	}
	
	public Integer getCurrentInstituteId() {
		return currentInstituteId;
	}
	public void setCurrentInstituteId(Integer currentInstituteId) {
		this.currentInstituteId = currentInstituteId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	
	
}
