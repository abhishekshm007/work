package com.o9paathshala.constants;

import java.util.ResourceBundle;

public class ErrorMessageConstants {

	private ErrorMessageConstants(){
	}
	public static final String SUCCESS;
	public static final String FAILED;
	public static final String EMAIL_MATCH_FAILED;
	public static final String INSTITUTE_TYPE_FAILED;
	public static final String EMAIL_FAILED;
	public static final String INSTITUTE_NAME_INCORRECT;
	public static final String WRONG_INPUT;
	public static final String EXCEPTION_MESSAGE;
	public static final String INTERNAL_SERVER_ERROR;
	public static final String LOGIN_ERROR;
	public static final String ALREADY_EXIST_NAME_ERROR;
	public static final String ALREADY_EXIST_EMAIL_ERROR;
	public static final String ALREADY_EXIST_SUBJECT_ERROR;
	public static final String INVALID_EMAIL;
	public static final String NO_QUESTION;
	public static final String REQUIRED_ERROR;



	static{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ErrorMessageConstants");
		INTERNAL_SERVER_ERROR = resourceBundle.getString("INTERNAL_SERVER_ERROR");
		LOGIN_ERROR = resourceBundle.getString("LOGIN_ERROR");
		SUCCESS = resourceBundle.getString("SUCCESS");
		EMAIL_MATCH_FAILED = resourceBundle.getString("EMAIL_MATCH_FAILED");
		INSTITUTE_TYPE_FAILED = resourceBundle.getString("INSTITUTE_TYPE_FAILED");
		EMAIL_FAILED = resourceBundle.getString("EMAIL_FAILED");
		INSTITUTE_NAME_INCORRECT = resourceBundle.getString("INSTITUTE_NAME_INCORRECT");
		EXCEPTION_MESSAGE=resourceBundle.getString("EXCEPTION_MESSAGE");
		WRONG_INPUT = resourceBundle.getString("WRONG_INPUT");
		ALREADY_EXIST_NAME_ERROR=resourceBundle.getString("ALREADY_EXIST_NAME_ERROR");
		ALREADY_EXIST_EMAIL_ERROR=resourceBundle.getString("ALREADY_EXIST_EMAIL_ERROR");
		ALREADY_EXIST_SUBJECT_ERROR=resourceBundle.getString("ALREADY_EXIST_SUBJECT_ERROR");
		INVALID_EMAIL=resourceBundle.getString("INVALID_EMAIL");
		FAILED = resourceBundle.getString("FAILED");
		NO_QUESTION=resourceBundle.getString("NO_QUESTION");
		REQUIRED_ERROR=resourceBundle.getString("REQUIRED_ERROR");
	}
}
