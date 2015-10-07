package com.o9paathshala.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.o9paathshala.dto.UserDTO;
import com.o9paathshala.security.Encryptor;

public class ProcessExcel {

	private  ProcessExcel(){

	}
	
	public static List<UserDTO> convertStudentXLSXToCSV(File file) throws IOException, NoSuchAlgorithmException {
		File newPath=new File(file.getAbsolutePath().replace("xlsx", "csv"));
		StringBuilder data = new StringBuilder();
		FileOutputStream fos = new FileOutputStream(newPath);
		FileInputStream myInput = new FileInputStream(file);
		XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		Iterator<Row> rowIter = mySheet.rowIterator();
		List<UserDTO> users=new ArrayList<UserDTO>();
		UserDTO user=null;
		rowIter.next();
		XSSFCell myCell=null;
		String password=null;
		String random=null;
		while(rowIter.hasNext()){
			user=new UserDTO();
			XSSFRow myRow = (XSSFRow) rowIter.next();
			Iterator<Cell> cellIter = myRow.cellIterator();
			myCell = (XSSFCell) cellIter.next();
			if(XSSFCell.CELL_TYPE_STRING==myCell.getCellType()){
				if(myCell.getStringCellValue().length()<=1){
					continue;
				}
				data.append(myCell.getStringCellValue() + "/");
				user.setName(myCell.getStringCellValue());
				myCell = (XSSFCell) cellIter.next();
				data.append(myCell.getStringCellValue() + "/");
				user.setEmail(myCell.getStringCellValue());
				random=GenerateRandomString.randomString();
				password=Encryptor.encryptSHA512(random);
				data.append(password+";");
				user.setPassword(random);
				
				users.add(user);
			}
		}
		fos.write(data.toString().getBytes());
		fos.close();
		return users;
	}
	public static List<UserDTO> convertStudentXLSToCSV(File file) throws IOException, NoSuchAlgorithmException {
		File newPath=new File(file.getAbsolutePath().replace("xls", "csv"));
		StringBuilder data = new StringBuilder();
		FileOutputStream fos = new FileOutputStream(newPath);
		FileInputStream myInput = new FileInputStream(file);
		HSSFWorkbook myWorkBook = new HSSFWorkbook(myInput);
		HSSFSheet mySheet = myWorkBook.getSheetAt(0);
		Iterator<Row> rowIter = mySheet.rowIterator();
		List<UserDTO> users=new ArrayList<UserDTO>();
		UserDTO user=null;
		rowIter.next();
		HSSFCell myCell=null;
		String password=null;
		String random=null;
		while(rowIter.hasNext()){
			user=new UserDTO();
			HSSFRow myRow = (HSSFRow) rowIter.next();
			Iterator<Cell> cellIter = myRow.cellIterator();
			myCell = (HSSFCell) cellIter.next();
			if(HSSFCell.CELL_TYPE_STRING==myCell.getCellType()){
				if(myCell.getStringCellValue().length()<=1){
					continue;
				}
				data.append(myCell.getStringCellValue() + "/");
				user.setName(myCell.getStringCellValue());
				myCell = (HSSFCell) cellIter.next();
				data.append(myCell.getStringCellValue() + "/");
				user.setEmail(myCell.getStringCellValue());
				random=GenerateRandomString.randomString();
				password=Encryptor.encryptSHA512(random);
				data.append(password+";");
				user.setPassword(random);
				users.add(user);
			}
		}
		fos.write(data.toString().getBytes());
		fos.close();
		return users;
	}
	public static String convertQuestionXLSXToCSV(File file) throws IOException {
		File newPath=new File(file.getAbsolutePath().replace("xlsx", "csv"));
		StringBuilder data = new StringBuilder();
		FileOutputStream fos = new FileOutputStream(newPath);

		FileInputStream myInput = new FileInputStream(file);
		XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		int maxNumOfCells = mySheet.getRow(0).getLastCellNum();
		Iterator<Row> rowIter = mySheet.rowIterator();
		rowIter.next();
		
		while(rowIter.hasNext()){
			boolean con=false;
			XSSFRow myRow = (XSSFRow) rowIter.next();
			 for( int cellCounter = 0; cellCounter < maxNumOfCells; cellCounter ++){
				 String myCell=null;
				 
				 if( myRow.getCell(cellCounter ) == null ){
					 myCell = myRow.createCell(cellCounter).toString();
                 } else {
                	 myCell = myRow.getCell(cellCounter).toString();
                	 if(cellCounter==0&&myCell.length()<=1){
                		 con=true;
                		 break;
                	 }
                	 if(cellCounter==7&&CheckNumber.isNumber(myCell)){
                        	myCell=myCell.substring(0,1);
                	 }
                	
                 }
				 
				 data.append(myCell+"#@");
			 }
			 if(con){
				 continue;
			 }
			data.append("::;");
		}
		fos.write(data.toString().getBytes());
		fos.close();
		return newPath.toString().replace("\\", "//");
	}

	public static String convertQuestionXLSToCSV(File file) throws IOException {
		File newPath=new File(file.getAbsolutePath().replace("xls", "csv"));
		StringBuilder data = new StringBuilder();
		FileOutputStream fos = new FileOutputStream(newPath);

		FileInputStream myInput = new FileInputStream(file);
		HSSFWorkbook myWorkBook = new HSSFWorkbook(myInput);
		HSSFSheet mySheet = myWorkBook.getSheetAt(0);
		int maxNumOfCells = mySheet.getRow(0).getLastCellNum();
		Iterator<Row> rowIter = mySheet.rowIterator();
		rowIter.next();
		while(rowIter.hasNext()){
			boolean con=false;
			HSSFRow myRow = (HSSFRow) rowIter.next();
			 for( int cellCounter = 0; cellCounter < maxNumOfCells; cellCounter ++){
				 String myCell;
				 
				 if( myRow.getCell(cellCounter ) == null ){
					 myCell = myRow.createCell(cellCounter).toString();
                 } else {
                	 myCell = myRow.getCell(cellCounter).toString();
                	 if(cellCounter==0&&myCell.length()<=1){
                		 con=true;
                		 break;
                	 }
                	 if(cellCounter==7&&CheckNumber.isNumber(myCell)){
                        	myCell=myCell.substring(0,1);
                	 }
                	
                 }
				 data.append(myCell+"#@");
			 }
			 if(con){
				 continue;
			 }
			data.append("::;");
		}
		fos.write(data.toString().getBytes());
		fos.close();
		return newPath.toString().replace("\\", "//");
	}

}


