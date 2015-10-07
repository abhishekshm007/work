package com.hf.persistentClass;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.gson.annotations.Expose;

@Entity
@Table (name="stock_cotegory")
@NamedQuery (name="getStockList", query=" from StockCotegory b " , hints={
		@QueryHint(name="org.hibernate.cacheable",
		value="true")
		}    )
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="banner")
public class StockCotegory {

	
	
	public StockCotegory() {
		super();
	}

	public StockCotegory(String stockCotegoryName) {
		super();
		this.stockCotegoryName = stockCotegoryName;
	}

	@Expose
	@Id @GeneratedValue
	@Column (name="stock_cotegory_id")
	private Integer stockCotegoryId;
	
	@Expose
	@Column (name="stock_cotegory_name")
	private String stockCotegoryName;
	
	@Expose
	@OneToMany (mappedBy="stockCotegory", fetch = FetchType.EAGER)
	private Collection<ProductCotegory> productCotegoryList = new ArrayList<ProductCotegory>();

	public Integer getStockCotegoryId() {
		return stockCotegoryId;
	}

	public void setStockCotegoryId(Integer stockCotegoryId) {
		this.stockCotegoryId = stockCotegoryId;
	}

	public String getStockCotegoryName() {
		return stockCotegoryName;
	}

	public void setStockCotegoryName(String stockCotegoryName) {
		this.stockCotegoryName = stockCotegoryName;
	}

	public Collection<ProductCotegory> getProductCotegoryList() {
		return productCotegoryList;
	}

	public void setProductCotegoryList(
			Collection<ProductCotegory> productCotegoryList) {
		this.productCotegoryList = productCotegoryList;
	}

	
	
}
