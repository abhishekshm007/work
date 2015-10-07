package com.o9paathshala.test.dto;

import com.o9paathshala.questions.dto.QuestionDTO;

public class QuestionSectionDTO {

	private QuestionDTO question;
	private SectionDTO section;
	public QuestionDTO getQuestion() {
		return question;
	}
	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}
	public SectionDTO getSection() {
		return section;
	}
	public void setSection(SectionDTO section) {
		this.section = section;
	}
}
