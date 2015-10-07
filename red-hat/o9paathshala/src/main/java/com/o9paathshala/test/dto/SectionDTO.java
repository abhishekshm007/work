package com.o9paathshala.test.dto;

import java.util.List;

import com.o9paathshala.questions.dto.QuestionDTO;

public class SectionDTO {

	private Integer sectionID;
    private String sectionName;
    private List<QuestionDTO> questions;
	public Integer getSectionID() {
		return sectionID;
	}
	public void setSectionID(Integer sectionID) {
		this.sectionID = sectionID;
	}
	
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public List<QuestionDTO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "SectionDTO [sectionID=" + sectionID + ", sectionName="
				+ sectionName + ", questions=" + questions + "]";
	}
    
}
