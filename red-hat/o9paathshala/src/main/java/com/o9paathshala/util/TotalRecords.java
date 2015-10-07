package com.o9paathshala.util;

import java.util.List;
import java.util.Map;

public class TotalRecords {
private TotalRecords(){
	
}
	public static int totalRecords(List<Map<String, Object>> data){
		int totalRecords=0;
		if(null!=data){
			totalRecords= Integer.parseInt(data.get(0).get("count").toString());
		}
		return totalRecords;
	}
}
