package com.o9paathshala.buy.dto;

public class BuyDTO {

	private String[] sets;
	private Float amount;
	private Integer pack;
	private Float tax;
	private Float totalAmount;
	public Float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Float getTax() {
		return tax;
	}
	public void setTax(Float tax) {
		this.tax = tax;
	}
	public String[] getSets() {
		return sets;
	}
	public void setSets(String[] sets) {
		this.sets = sets;
	}
	
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Integer getPack() {
		return pack;
	}
	public void setPack(Integer pack) {
		this.pack = pack;
	}
	
	

}
