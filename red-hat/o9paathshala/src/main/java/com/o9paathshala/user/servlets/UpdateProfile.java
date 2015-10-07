package com.o9paathshala.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.o9paathshala.util.Validation;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProfile.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		PrintWriter out=response.getWriter();
		DAO dao=DAOFactory.getDAOObject();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=null;
		Gson gson=new Gson();
		boolean result=false;

		SessionDTO s=(SessionDTO)session.getAttribute("user");
		int type=s.getType();
		String sql=null;
		String jsonString=null;
		try{
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		
		if(!Validation.validateEmail(email)){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("Your email is incorrect, please try other email...");
			return;
		}
		if(!Validation.validateInstituteName(name)){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("Your institute name is incorrect, please try other name...");
			return;
		}
		
			switch(type){
			case 2:
				sql=SQLConstants.UPDATE_FACULTY_PROFILE;
				break;
			case 3:
				sql=SQLConstants.UPDATE_STUDENT_PROFILE;
				break;
			default: break;
			}
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(1);
			psDTO.setValue(request.getParameter("name"));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(2);
			psDTO.setValue(request.getParameter("email"));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.DATE);
			psDTO.setPosition(3);
			String dob=request.getParameter("dob");
			if(dob.isEmpty()){
				psDTO.setValue(null);
			}else{
			psDTO.setValue( java.sql.Date.valueOf(dob));
			}
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(4);
			psDTO.setValue(request.getParameter("gender"));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(5);
			psDTO.setValue(request.getParameter("contact"));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(6);
			psDTO.setValue(request.getParameter("address"));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(7);
			psDTO.setValue(s.getId());
			psList.add(psDTO);
			int data=0;

			data=dao.cud(sql.replace("instituteid",s.getCurrentInstituteId().toString()),psList);
			if(data>0){
				s.setEmail(email);
				s.setName(name);
				result=true;
			}
			jsonString = gson.toJson(result);
		} catch (ClassNotFoundException | SQLException  e) {
			LOGGER.error(e.getMessage(), e);
		}finally{
		out.println(jsonString);
		out.close();
	}
	}

}
