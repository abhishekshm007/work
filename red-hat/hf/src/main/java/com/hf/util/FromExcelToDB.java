package com.hf.util;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;

import com.hf.persistentClass.Pincode;


public class FromExcelToDB {

	 public static void main(String[] args) {
	        try{
	           /* Class.forName("com.mysql.jdbc.Driver");
	            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hf_pincode","root","ainaa");
	            con.setAutoCommit(false);
	            PreparedStatement pstm = null ;*/
	            FileInputStream input = new FileInputStream("/home/abhishek/git/HimachalFresh/src/pincode.xls");
	            POIFSFileSystem fs = new POIFSFileSystem( input );
	            HSSFWorkbook wb = new HSSFWorkbook(fs);
	            HSSFSheet sheet = wb.getSheetAt(0);
	            Row row;
	            Session session = HibernateUtil.getSessionFactory().openSession();
	            session.beginTransaction();
	            Pincode pincode;
	            for(int i=1; i<=sheet.getLastRowNum(); i++){
	                row = sheet.getRow(i);
	                String location =  row.getCell(0).getStringCellValue();
	                String code = ((int)row.getCell(1).getNumericCellValue())+"";
	                String state = row.getCell(2).getStringCellValue();
	                String district = row.getCell(3).
	                		getStringCellValue();
	                pincode = new Pincode();
	                pincode.setDistrict(district);
	                pincode.setLocation(location);
	                pincode.setPincode(code);
	                pincode.setState(state);
	                session.save(pincode);
	                /*String sql = "INSERT INTO pincode VALUES('"+location+"','"+pincode+"','"+state+"','"+district+"')";
	                pstm = (PreparedStatement) con.prepareStatement(sql);
	                pstm.execute();*/
	                System.out.println("Import rows "+i);
	            }
	            session.getTransaction().commit();
	            session.close();
	            /*con.commit();
	            pstm.close();
	            con.close();*/
	            input.close();
	            System.out.println("Success import excel to mysql table");
	        }catch(IOException ioe){
	            System.out.println(ioe);
	        }
	 }
}
