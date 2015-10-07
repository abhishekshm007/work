package com.hf.persistentClass;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;


@Entity 
@Table (name="products")
@NamedQuery (name="getProductForHome", query=" from Product",hints={
		@QueryHint(name="org.hibernate.cacheable",
		value="true")
		})
public class Product {

	
	public Product() {
		super();
	}



	public Product(String productName, String productDesc,
			Double productAmount, Boolean isPremium, Boolean isNew,
			Boolean isSale, Integer pointsOffered,
			ProductCotegory productCotegory) {
		super();
		this.productName = productName;
		this.productDesc = productDesc;
		this.productAmount = productAmount;
		this.isPremium = isPremium;
		this.isNew = isNew;
		this.isSale = isSale;
		this.pointsOffered = pointsOffered;
		this.productCotegory = productCotegory;
	}


@Expose
	@Id
	@Column (name="product_id",insertable = false, unique = true, updatable = false)
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer productId;

@Expose
	@Column (name="product_name", updatable = true, unique = true, length = 100, nullable = false)
	private String productName;
	
@Expose
	@Column (name="product_desc", updatable = true, nullable = false)
	@Lob
	private String productDesc;
	
@Expose
	@Column (name="product_amount",updatable = true, nullable = false)
	private Double productAmount;
	
@Expose
	@Column (name="is_premium",updatable = true, nullable = true)
	private Boolean isPremium;
	
@Expose
	@Column (name="is_new",updatable = true, nullable = true)
	private Boolean isNew;
	
@Expose
	@Column (name="is_sale",updatable = true,nullable = true)
	private Boolean isSale;
	
@Expose
	@Column (name="points_offered",updatable = true, nullable = true)
	private Integer pointsOffered;
	
@Expose
	@Column (name="reputation", columnDefinition = "int default 0")
	private Integer reputation;
	
	@ManyToOne
	@JoinColumn (name="product_cotegory_id")
	private ProductCotegory productCotegory; 

	@ManyToMany(mappedBy = "productList")
	private Collection<Order> orderList = new ArrayList<Order>();

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public Double getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
	}

	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Boolean getIsSale() {
		return isSale;
	}

	public void setIsSale(Boolean isSale) {
		this.isSale = isSale;
	}

	public Integer getPointsOffered() {
		return pointsOffered;
	}

	public void setPointsOffered(Integer pointsOffered) {
		this.pointsOffered = pointsOffered;
	}

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	public ProductCotegory getProductCotegory() {
		return productCotegory;
	}

	public void setProductCotegory(ProductCotegory productCotegory) {
		this.productCotegory = productCotegory;
	}

	public Collection<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(Collection<Order> orderList) {
		this.orderList = orderList;
	}



	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + ", productDesc=" + productDesc
				+ ", productAmount=" + productAmount + ", isPremium="
				+ isPremium + ", isNew=" + isNew + ", isSale=" + isSale
				+ ", pointsOffered=" + pointsOffered + ", reputation="
				+ reputation + "]";
	}



	
}
