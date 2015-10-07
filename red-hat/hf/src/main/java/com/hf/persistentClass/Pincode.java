package com.hf.persistentClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="pincode")
@NamedQuery(name="verifyPincodeByPincode",query="from Pincode p where p.pincode = :pincode")
public class Pincode {
	
	
	
	public Pincode() {
		super();
	}
	public Pincode(String location, String pincode, String state,
			String district) {
		super();
		this.location = location;
		this.pincode = pincode;
		this.state = state;
		this.district = district;
	}
	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name="id")
	private Integer id;
	@Column (name="location", length = 30)
	private String location;
	@Column (name="pincode", length = 15)
	private String pincode;
	@Column (name="state", length = 30)
	private String state;
	@Column (name="district", length = 30)
	private String district;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	
}
