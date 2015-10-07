package com.o9paathshala.forum.dto;

import java.util.List;

public class ExploredQuestionDTO {

	private QuestionDTO question;
	private List<AnswerDTO> answers;
	public QuestionDTO getQuestion() {
		return question;
	}
	public void setQuestion(QuestionDTO qestion) {
		this.question = qestion;
	}
	public List<AnswerDTO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
	}
	
	
}
