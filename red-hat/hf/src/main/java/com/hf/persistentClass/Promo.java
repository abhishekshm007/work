package com.hf.persistentClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="promo")
public class Promo {

	
	public Promo() {
		super();
	}

	public Promo(String promoCode, Double promoDiscount, Date promoExpiry,
			String tnc) {
		super();
		this.promoCode = promoCode;
		this.promoDiscount = promoDiscount;
		this.promoExpiry = promoExpiry;
		this.tnc = tnc;
	}



	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	private Integer promoId;
	
	@Column (name="promo_code", length= 50,unique = true,updatable = true,nullable=false)
	private String promoCode;
	
	@Column (name="promo_discount",unique = false,updatable = true,nullable=true)
	private Double promoDiscount;
	
	@Column (name="promo_expiry",unique = false,updatable = true,nullable=true)
	private Date promoExpiry;
	
	@Column (name="promo_tnc",updatable = true,nullable=true)
	@Lob
	private String tnc;

	@OneToMany (mappedBy="promo")
	private Collection<Order> orderList = new ArrayList<Order>();

	public Integer getPromoId() {
		return promoId;
	}

	public void setPromoId(Integer promoId) {
		this.promoId = promoId;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public Double getPromoDiscount() {
		return promoDiscount;
	}

	public void setPromoDiscount(Double promoDiscount) {
		this.promoDiscount = promoDiscount;
	}

	public Date getPromoExpiry() {
		return promoExpiry;
	}

	public void setPromoExpiry(Date promoExpiry) {
		this.promoExpiry = promoExpiry;
	}

	public String getTnc() {
		return tnc;
	}

	public void setTnc(String tnc) {
		this.tnc = tnc;
	}

	public Collection<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(Collection<Order> orderList) {
		this.orderList = orderList;
	}


}
