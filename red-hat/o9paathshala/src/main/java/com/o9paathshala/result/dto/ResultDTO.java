package com.o9paathshala.result.dto;

import java.sql.Timestamp;

public class ResultDTO {

	private String batch;
	private String student;
	private String test;
	private Float score;
	private Integer attempt;
	private Timestamp attemptdate;
	private Integer studentid;
	public Integer getStudentid() {
		return studentid;
	}
	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Integer getAttempt() {
		return attempt;
	}
	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}
	public Timestamp getAttemptdate() {
		return attemptdate;
	}
	public void setAttemptdate(Timestamp attemptdate) {
		this.attemptdate = attemptdate;
	}
	
	

}
