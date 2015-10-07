package com.o9paathshala.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateDirectories {
private CreateDirectories(){
	
}
	
	public static void createDirectories(Integer id,String path){
		
		List<String> list=new ArrayList<String>();
		list.add(path+File.separator+"users"+File.separator+id+File.separator+"student"+File.separator+"tempfiles");
		list.add(path+File.separator+"users"+File.separator+id+File.separator+"faculty"+File.separator+"tempfiles");
		list.add(path+File.separator+"users"+File.separator+id+File.separator+"questionbank"+File.separator+"tempfiles");
		list.add(path+File.separator+"users"+File.separator+id+File.separator+"student"+File.separator+"savedfiles");
		list.add(path+File.separator+"users"+File.separator+id+File.separator+"faculty"+File.separator+"savedfiles");
		list.add(path+File.separator+"users"+File.separator+id+File.separator+"questionbank"+File.separator+"savedfiles");
		list.add(path+File.separator+"users"+File.separator+id+File.separator+"images"+File.separator+"tempfiles");
		list.add(path+File.separator+"users"+File.separator+id+File.separator+"images"+File.separator+"savedfiles");
		for(String p:list){
		File file = new File(p);
        if(!file.exists()){
        	
        	file.mkdirs(); 
        }
        }
	}
}
