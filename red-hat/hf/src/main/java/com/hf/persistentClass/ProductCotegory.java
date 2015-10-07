package com.hf.persistentClass;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.gson.annotations.Expose;

@Entity
@Table (name="product_cotegory")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="banner")
public class ProductCotegory {

	
	
	public ProductCotegory() {
		super();
	}

	public ProductCotegory(String productCotegoryName) {
		super();
		this.productCotegoryName = productCotegoryName;
	}

	@Expose
	@Id @GeneratedValue
	@Column (name="product_cotegory_id")
	private Integer productCotegoryId;
	
	@Expose
	@Column (name="product_cotegory_name")
	private String productCotegoryName;
	
	@ManyToOne
	@JoinColumn (name="stock_id")
	private StockCotegory stockCotegory;
	
	@OneToMany (mappedBy="productCotegory", fetch = FetchType.EAGER)
	private Collection<Product> productList = new ArrayList<Product>();

	public Integer getProductCotegoryId() {
		return productCotegoryId;
	}

	public void setProductCotegoryId(Integer productCotegoryId) {
		this.productCotegoryId = productCotegoryId;
	}

	public String getProductCotegoryName() {
		return productCotegoryName;
	}

	public void setProductCotegoryName(String productCotegoryName) {
		this.productCotegoryName = productCotegoryName;
	}

	public StockCotegory getStockCotegory() {
		return stockCotegory;
	}

	public void setStockCotegory(StockCotegory stockCotegory) {
		this.stockCotegory = stockCotegory;
	}

	public Collection<Product> getProductList() {
		return productList;
	}

	public void setProductList(Collection<Product> productList) {
		this.productList = productList;
	}
	
	
}
