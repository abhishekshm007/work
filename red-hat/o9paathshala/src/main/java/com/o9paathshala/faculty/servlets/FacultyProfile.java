package com.o9paathshala.faculty.servlets;

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
import com.o9paathshala.faculty.dto.FacultyDTO;

/**
 * Servlet implementation class FacultyProfile
 */
@WebServlet("/FacultyProfile")
public class FacultyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacultyProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(FacultyProfile.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		PreparedStatementDTO psDTO = new PreparedStatementDTO();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		FacultyDTO faculty=new FacultyDTO();
		Integer id=null;
		Gson gson = new Gson();
		String jsonString=null;
		try{
		int type=((SessionDTO)session.getAttribute("user")).getType();
		switch(type){
		case 2:id=((SessionDTO)session.getAttribute("user")).getId();
		break;
		default:id=Integer.parseInt(request.getParameter("facultyid"));
		break;
		}
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(1);
			psDTO.setValue(id);
			psList.add(psDTO);
			resultList=dao.getAll(SQLConstants.GET_FACULTY_ON_ID.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList){
				faculty.setName(resultList.get(0).get("faculty_name").toString());
				faculty.setEmail(resultList.get(0).get("faculty_email").toString());
				faculty.setId(Integer.parseInt(resultList.get(0).get("faculty_id").toString()));
				if(null!=resultList.get(0).get("faculty_dob")&&resultList.get(0).get("faculty_dob").toString().length()>0){
					faculty.setDob(java.sql.Date.valueOf(resultList.get(0).get("faculty_dob").toString()));
				}
				if(null!=resultList.get(0).get("faculty_gender")&&resultList.get(0).get("faculty_gender").toString().length()>0){
					faculty.setGender( resultList.get(0).get("faculty_gender").toString().charAt(0));
				}
				if(null!=resultList.get(0).get("faculty_contact")){
					faculty.setContact(resultList.get(0).get("faculty_contact").toString());
				}
				if(null!=resultList.get(0).get("faculty_address")){
					faculty.setAddress(resultList.get(0).get("faculty_address").toString());
				}
			}
			
			jsonString = gson.toJson(faculty);
			
		} catch (ClassNotFoundException | SQLException  e) {			
			LOGGER.error(e.getMessage(), e);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}
