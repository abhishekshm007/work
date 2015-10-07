package com.hf.persistentClass;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	
	public Address() {
		super();
	}
	public Address(String firstLine, String city, String state, String pincode) {
		super();
		this.firstLine = firstLine;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	public Address(String firstLine, String secondLine, String city,
			String state, String pincode) {
		super();
		this.firstLine = firstLine;
		this.secondLine = secondLine;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	@Column (name="f_line",length=200,updatable=true)
	private String firstLine;
	@Column (name="s_line",length=200,updatable=true)
	private String secondLine;
	@Column (name="city",length=30,updatable=true)
	private String city;
	@Column (name="state",length=30,updatable=true)
	private String state;
	@Column (name="pincode",length=15,updatable=true)
	private String pincode;
	public String getFirstLine() {
		return firstLine;
	}
	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}
	public String getSecondLine() {
		return secondLine;
	}
	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
}
