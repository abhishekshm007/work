package com.o9paathshala.test.dto;

import java.sql.Timestamp;
import java.util.List;

public class TestDTO {

	private Integer id;
	private String testName;
	private Float negativeMark;
	private Float positiveMark;
	private Integer duration;
	private Timestamp startdate;
	private Timestamp enddate;
	private Timestamp uploaddate;
	private List<SectionDTO> sections;
	private Boolean activated;
	private String createdBy;
	private Boolean attempted;
	
	
	public Boolean getAttempted() {
		return attempted;
	}
	public void setAttempted(Boolean attempted) {
		this.attempted = attempted;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Boolean getActivated() {
		return activated;
	}
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}
	public Timestamp getStartdate() {
		return startdate;
	}
	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}
	public Timestamp getEnddate() {
		return enddate;
	}
	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}
	public Timestamp getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(Timestamp uploaddate) {
		this.uploaddate = uploaddate;
	}
	public Float getPositiveMark() {
		return positiveMark;
	}
	public void setPositiveMark(Float positiveMark) {
		this.positiveMark = positiveMark;
	}
	public Float getNegativeMark() {
		return negativeMark;
	}
	public void setNegativeMark(Float negativeMark) {
		this.negativeMark = negativeMark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public List<SectionDTO> getSections() {
		return sections;
	}
	public void setSections(List<SectionDTO> sections) {
		this.sections = sections;
	}
	@Override
	public String toString() {
		return "TestDTO [id=" + id + ", testName=" + testName
				+ ", negativeMark=" + negativeMark + ", positiveMark="
				+ positiveMark + ", duration=" + duration + ", startdate="
				+ startdate + ", enddate=" + enddate + ", uploaddate="
				+ uploaddate + ", sections=" + sections + ", activated="
				+ activated + ", createdBy=" + createdBy + ", attempted="
				+ attempted + "]";
	}
	
	
}
