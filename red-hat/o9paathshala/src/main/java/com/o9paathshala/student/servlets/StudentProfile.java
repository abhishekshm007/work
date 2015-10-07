package com.o9paathshala.student.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.student.dto.StudentDTO;


@WebServlet("/StudentProfile")
public class StudentProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public StudentProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentProfile.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		PreparedStatementDTO psDTO = new PreparedStatementDTO();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		StudentDTO student=new StudentDTO();
		Gson gson = new Gson();
		String jsonString=null;
		try{
			Integer id=null;
			int type=((SessionDTO)session.getAttribute("user")).getType();
			switch(type){
			case 3:id=((SessionDTO)session.getAttribute("user")).getId();
			break;
			default:id=Integer.parseInt(request.getParameter("studentid"));
			break;
			}
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(1);
			psDTO.setValue(id);
			psList.add(psDTO);
			resultList=dao.getAll(SQLConstants.GET_STUDENT_ON_ID.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList){
				student.setBatch(resultList.get(0).get("batch_name").toString());
				student.setName(resultList.get(0).get("student_name").toString());
				student.setEmail(resultList.get(0).get("student_email").toString());
				student.setId(Integer.parseInt(resultList.get(0).get("student_id").toString()));
				if(null!=resultList.get(0).get("student_dob")&&resultList.get(0).get("student_dob").toString().length()>0){
					student.setDob(java.sql.Date.valueOf(resultList.get(0).get("student_dob").toString()));
				}
				if(null!=resultList.get(0).get("student_gender")&&resultList.get(0).get("student_gender").toString().length()>0){
					student.setGender(resultList.get(0).get("student_gender").toString().charAt(0));
				}
				if(null!=resultList.get(0).get("student_contact")){
					student.setContact(resultList.get(0).get("student_contact").toString());
				}
				if(null!=resultList.get(0).get("student_address")){
					student.setAddress(resultList.get(0).get("student_address").toString());
				}
			}
			jsonString = gson.toJson(student);
		} catch (ClassNotFoundException | SQLException e) {			
			LOGGER.error(e.getMessage(), e);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}
