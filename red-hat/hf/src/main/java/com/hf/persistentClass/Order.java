package com.hf.persistentClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="orders")
public class Order {

	public Order() {
		super();
	}

	public Order(Integer pointsUsed, Double tax, Double discount, Double total,
			User user, Promo promo, Collection<Product> productList) {
		super();
		this.pointsUsed = pointsUsed;
		this.tax = tax;
		this.discount = discount;
		this.total = total;
		this.user = user;
		this.promo = promo;
		this.productList = productList;
	}



	@Id @GeneratedValue
	@Column (name="order_id")
	private Integer orderId;
	
	@Column (name="points_used")
	private Integer pointsUsed;
	
	@Column (name="tax")
	private Double tax;
	
	@Column (name="discount")
	private Double discount;
	
	@Column (name="total")
	private Double total;
	
	@Column (name="time", columnDefinition = " default now() ")
	private Date time;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="user_id")
	private User user;

	@ManyToOne
	@JoinColumn (name="promo_id")
	private Promo promo;

	@ManyToMany
	private Collection<Product> productList = new ArrayList<Product>();

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getPointsUsed() {
		return pointsUsed;
	}

	public void setPointsUsed(Integer pointsUsed) {
		this.pointsUsed = pointsUsed;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Promo getPromo() {
		return promo;
	}

	public void setPromo(Promo promo) {
		this.promo = promo;
	}
	public Collection<Product> getProductList() {
		return productList;
	}

	public void setProductList(Collection<Product> productList) {
		this.productList = productList;
	}

	
	
}
