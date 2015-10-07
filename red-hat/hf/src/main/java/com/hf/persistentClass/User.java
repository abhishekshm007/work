package com.hf.persistentClass;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity (name="user")
public class User {
	
	
	public User() {
		super();
	}

	public User(String username, String email, String password){
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public User(String firstName, String lastName, String username,
			String email, String password, String mobileNo,
			Collection<Address> addressList) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
		this.addressList = addressList;
	}

	public User(String firstName, String lastName, String username,
			String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id",updatable=false,insertable=false)
	private Integer userId;
	
	@Column (name="f_name",length=30,updatable=true)
	private String firstName;
	
	@Column (name="l_name",length=30,updatable=true)
	private String lastName;
	
	@Column (name="username",length=100,updatable=false)
	private String username;
	
	@Column (name="email",length=150,updatable=true)
	private String email;
	
	@Column (name="password",length=500,updatable=true)
	private String password;
	
	@Column (name="mob_no",length=15,updatable=true)
	private String mobileNo;
	
	@Column (name="points", updatable = true)
	private Integer points;
	
	@OneToMany (mappedBy="user",fetch=FetchType.LAZY)
	private Collection<Order> orderList = new ArrayList<Order>();
	
	@ElementCollection(fetch=FetchType.LAZY)
	@JoinTable (name="user_address",
				joinColumns=@JoinColumn(name="user_id")
	)
	@GenericGenerator (name="hilo-gen", strategy="hilo")
	@CollectionId(columns = { @Column(name="address_id")}, generator = "hilo-gen", type = @Type(type="integer"))
	private Collection<Address> addressList = new ArrayList<Address>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Collection<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(Collection<Order> orderList) {
		this.orderList = orderList;
	}

	public Collection<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(Collection<Address> addressList) {
		this.addressList = addressList;
	}



	
}
