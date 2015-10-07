package com.o9paathshala.institute.servlets;

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
import com.o9paathshala.institute.dto.InstituteProfileDTO;

/**
 * Servlet implementation class GetInstituteProfile
 */
@WebServlet("/GetInstituteProfile")
public class GetInstituteProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetInstituteProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(GetInstituteProfile.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		boolean message=false;
		String jsonString=null;
		try{
			HttpSession session=request.getSession(false);
			if(null==session.getAttribute("user")){
				response.sendRedirect("index.jsp");
			}
			DAO dao=DAOFactory.getDAOObject();
			List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
			PreparedStatementDTO psDTO=null;
			InstituteProfileDTO dto=new InstituteProfileDTO();
			List<Map<String, Object>> resultList = null;
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(1);
			psDTO.setValue(((SessionDTO)session.getAttribute("user")).getId());
			psList.add(psDTO);
			resultList=dao.getAll(SQLConstants.INSTITUTE_PROFILE,psList);
			if(null!=resultList){
				if(null!=resultList.get(0).get("institute_address")){
					dto.setAddress(resultList.get(0).get("institute_address").toString());
				}
				if(null!=resultList.get(0).get("institute_contact")){
					dto.setContact(resultList.get(0).get("institute_contact").toString());
				}
				dto.setEmail(resultList.get(0).get("institute_email").toString());
				dto.setName(resultList.get(0).get("institute_name").toString());
			}
			jsonString = gson.toJson(dto);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
			jsonString = gson.toJson(message);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}