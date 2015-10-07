package com.o9paathshala.dao;

import java.util.List;

public class ConfiguredQuery {

	private String query;
	private List<PreparedStatementDTO> psList;
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public List<PreparedStatementDTO> getPsList() {
		return psList;
	}
	public void setPsList(List<PreparedStatementDTO> psList) {
		this.psList = psList;
	}
	
}
