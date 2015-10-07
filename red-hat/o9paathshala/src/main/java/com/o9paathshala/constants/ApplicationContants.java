package com.o9paathshala.constants;

public interface ApplicationContants {

	String APPNAME = "o9Paathshala";
	// used in confirmation mail
	String HOST = "http://www.o9paathshala.com";
	// used for test data encoding
	String SECURITYKEY="jaimatadi"; 

	// used in confirmation mail
	String LOGIN_HOST="http://www.o9paathshala.com/login.jsp";
	Integer SESSION_MAX_INACTIVE_INTERVAL = 30*60*1000;
	
	String COOKIE_NAME = "o9_r_fl";
	
	Integer COOKIE_MAX_AGE = 10*24*60*60;
	
	String MAIL_SUBJECT = "Welcome to o9Paathshala";
	
	String DEFAULT_ENCRYPTED_LINK = "expire";
	

	Integer STUDENT = 3;
	Integer FACULTY = 2;
	Integer INSTITUTE =1;
	
	
	Integer O9_INSTITUTE_ID = 100000001;
	Integer O9_BATCH_ID = 1;
}
