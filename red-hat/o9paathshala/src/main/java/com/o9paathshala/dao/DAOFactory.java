package com.o9paathshala.dao;

import java.util.ResourceBundle;

public class DAOFactory {
private DAOFactory(){
	
}
	public static DAO getDAOObject(){
		DAO dao = null;
		ResourceBundle rb = ResourceBundle.getBundle("db");
		String dataBaseType = rb.getString("dbtype");
		if(("mysql").equalsIgnoreCase(dataBaseType)){
			dao = new MySQLDAO();
		}else
			if(("oracle").equalsIgnoreCase(dataBaseType)){	
		}
		return dao;
	}
}
