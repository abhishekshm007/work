package com.o9paathshala.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.o9paathshala.dto.SessionDTO;


public interface DAO {
	int cud(List<ConfiguredQuery> cqList)throws ClassNotFoundException, SQLException;
	int cud(String sql, List<PreparedStatementDTO> psList)
			throws ClassNotFoundException, SQLException;
	List<Map<String, Object>> getAll(String sql, List<PreparedStatementDTO> psList)
			throws ClassNotFoundException, SQLException;
	SessionDTO instituteConfirmation(List<ConfiguredQuery> data) throws ClassNotFoundException, SQLException;



}
