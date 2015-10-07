package com.o9paathshala.result.dto;

public class LeaderBoardDTO {

	private Integer id;
	private String name;
	private Float score;
	private Integer rank;
	private Integer attempt;
	
	public Integer getAttempt() {
		return attempt;
	}
	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "LeaderBoardDTO [id=" + id + ", name=" + name + ", score="
				+ score + "]";
	}
	@Override
	public boolean equals(Object obj) {
		boolean result=false;
		if(((LeaderBoardDTO)obj).getId().equals(this.id)){
		result=true;	
		}
		return result;
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
