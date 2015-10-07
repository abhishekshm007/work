package com.o9paathshala.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Closing {
	private Closing(){
		
	}

	 private static final Logger LOGGER = LoggerFactory.getLogger(Closing.class);
			public static void closeEverything(ResultSet rs,PreparedStatement pstmt,
	        Connection con) {
	    if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException e) {
	        	LOGGER.error(e.getMessage(), e);
	        }
	    }
	    if (pstmt != null) {
	        try {
	            pstmt.close();
	        } catch (SQLException e) {
	        	LOGGER.error(e.getMessage(), e);
	        }
	    }
	    if (con != null) {
	        try {
	            con.close();
	        } catch (SQLException e) {
	        	LOGGER.error(e.getMessage(), e);
	        }
	    }
	}
	
}
