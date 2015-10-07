package com.o9paathshala.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	private Validation(){
		
	}
	private static Pattern pattern;
	private static Matcher matcher;
	private static final String INSTITUTE_PATTERN = "^[a-zA-Z0-9_ ]*$";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static boolean validateInstituteName(String instituteName){
		pattern = Pattern.compile(INSTITUTE_PATTERN);
		matcher = pattern.matcher(instituteName);
		return matcher.matches();
	}
	 public static boolean validateEmail(String email){
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
