package com.hf.persistentClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table (name="banner")
@NamedQuery (name="getBannerLimitFive", query=" from Banner b " , hints={
		@QueryHint(name="org.hibernate.cacheable",
		value="true")
		}    )
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="banner")
public class Banner {

	public Banner(){
		
	}
	

	public Banner(String productName, String productDesc, Integer productId) {
		super();
		this.productName = productName;
		this.productDesc = productDesc;
		this.productId = productId;
	}


	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name="banner_id")
	private Integer bannerId;
	
	@Column (name="product_name")
	private String productName;
	
	@Column (name="product_desc")
	@Lob
	private String productDesc;
	
	@Column (name="product_id")
	private Integer productId;

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	
	
}
