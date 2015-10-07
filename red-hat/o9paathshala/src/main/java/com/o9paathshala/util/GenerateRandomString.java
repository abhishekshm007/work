package com.o9paathshala.util;

public class GenerateRandomString {

	private GenerateRandomString(){

	}
	public static String randomString(){
		String array[]={"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"};
		String a1=array[(int)(Math.random()*array.length)];
		String a2=array[(int)(Math.random()*array.length)];
		String a3=array[(int)(Math.random()*array.length)];
		String a4=array[(int)(Math.random()*array.length)];
		String a5=array[(int)(Math.random()*array.length)];
		StringBuilder password=new StringBuilder("");
		password.append(a1);
		password.append(a2);
		password.append(Integer.parseInt("999999999")%(int)(Math.random()*10000));
		password.append(a3);
		password.append(a4);
		password.append(a5);
		return password.toString();

	}

}
