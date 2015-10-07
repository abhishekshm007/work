package com.o9paathshala.test.dto;

import java.sql.Timestamp;

public class UnAttemptedTestDTO {

	private Timestamp sDate;
	private Timestamp eDate;
	private String name;
	public Timestamp getsDate() {
		return sDate;
	}
	public void setsDate(Timestamp sDate) {
		this.sDate = sDate;
	}
	public Timestamp geteDate() {
		return eDate;
	}
	public void seteDate(Timestamp eDate) {
		this.eDate = eDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
