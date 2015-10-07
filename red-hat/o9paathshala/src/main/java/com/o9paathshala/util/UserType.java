package com.o9paathshala.util;

import com.o9paathshala.constants.ApplicationContants;

public class UserType {

	private UserType(){
		
	}
	
	
	public static Integer userType(int id){
		
		int type=0;
		int data=100000000;
		int div=(int)(id/data);
		switch(div){
		case 1:type=ApplicationContants.INSTITUTE;
		break;
		case 2:type=ApplicationContants.FACULTY;
		break;
		case 3:type=ApplicationContants.STUDENT;
		break;
		default: break;
		}
		return type;
	}
	
}
