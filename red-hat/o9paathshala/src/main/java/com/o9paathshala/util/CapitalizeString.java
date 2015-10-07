package com.o9paathshala.util;

public class CapitalizeString {

	private CapitalizeString(){
		
	}
	public static String get(String... strings){
		String toReturn = "";
		for(String arg1 : strings){
			if(!"".equalsIgnoreCase(arg1) && null != arg1){
				char firstLetter = arg1.charAt(0);
				char upperChar = Character.toUpperCase(firstLetter);
				toReturn =  toReturn + " " + upperChar+ arg1.substring(1);
			}
		}
		return toReturn;
	}
}
