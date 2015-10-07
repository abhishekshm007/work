package com.o9paathshala.institute.servlets;


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
 * Servlet implementation class UpdateInstituteProfile
 */
@WebServlet("/UpdateInstituteProfile")
public class UpdateInstituteProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInstituteProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateInstituteProfile.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(null==session.getAttribute("user")){
			response.sendRedirect("index.jsp");
		}
		PrintWriter out=response.getWriter();
		DAO dao=DAOFactory.getDAOObject();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=null;
		Gson gson=new Gson();
		boolean message=false;
		String jsonString=null;
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
		SessionDTO s=(SessionDTO)session.getAttribute("user");
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(1);
		psDTO.setValue(name);
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(2);
		psDTO.setValue(email);
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(3);
		psDTO.setValue(request.getParameter("contact"));
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(4);
		psDTO.setValue(request.getParameter("address"));
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(5);
		psDTO.setValue(s.getId());
		psList.add(psDTO);
		int result=0;
		try {
			result=dao.cud(SQLConstants.UPDATE_INSTITUTE_PROFILE,psList);
			if(result>0){
				s.setEmail(email);
				s.setInstituteName(name);
				message=true;
			}
			jsonString = gson.toJson(message);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
			jsonString = gson.toJson( message);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}

}
