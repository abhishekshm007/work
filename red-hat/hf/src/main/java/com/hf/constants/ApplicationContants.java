package com.hf.constants;

import java.util.ResourceBundle;

public class ApplicationContants {

	public static String APPLICATION_NAME;
	public static String TAG_LINE;
	public static String MOBILE_NO;
	public static String SUPPORT_EMAIL_ID; 
	
	static{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("applicationConstant");
		APPLICATION_NAME = resourceBundle.getString("applicationName");
		TAG_LINE = resourceBundle.getString("tagLine");
		MOBILE_NO = resourceBundle.getString("mobileNo");
		SUPPORT_EMAIL_ID = resourceBundle.getString("supportEmailId");
	}
}
