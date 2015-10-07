package com.o9paathshala.test.servlets;

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
import com.o9paathshala.constants.ErrorMessageConstants;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;

/**
 * Servlet implementation class CreateTest
 */
@WebServlet("/EditTest")
public class EditTest extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public EditTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(EditTest.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		DAO dao=DAOFactory.getDAOObject();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=null;
		Gson gson=new Gson();
		Boolean message=false;
		List<Map<String, Object>> resultList = null;
		String jsonString =null;
		try {
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(1);
			psDTO.setValue(request.getParameter("testname"));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(2);
			psDTO.setValue(Integer.parseInt(request.getParameter("testid")));
			psList.add(psDTO);
			resultList=dao.getAll(SQLConstants.CHECK_EDIT_TEST_NAME.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList&&1==Integer.parseInt(resultList.get(0).get("count").toString())){
				 jsonString = gson.toJson(ErrorMessageConstants.ALREADY_EXIST_NAME_ERROR);
				
				return;
			}
			psList.clear();
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(1);
			psDTO.setValue(request.getParameter("testname"));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(2);
			psDTO.setValue(Integer.parseInt(request.getParameter("duration")));
			psList.add(psDTO);


			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.TIMESTAMP);
			psDTO.setPosition(3);
			psDTO.setValue(java.sql.Timestamp.valueOf(request.getParameter("startdate").replace("T", " ")+":00"));
			psList.add(psDTO);

			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.TIMESTAMP);
			psDTO.setPosition(4);
			if(null!=request.getParameter("enddate")&&request.getParameter("enddate").length()>0){
				psDTO.setValue(java.sql.Timestamp.valueOf(request.getParameter("enddate").replace("T", " ")+":00"));
			}else{
				psDTO.setValue(null);
			}
			psList.add(psDTO);

			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.FLOAT);
			psDTO.setPosition(5);
			psDTO.setValue(Float.parseFloat(request.getParameter("positivemark")));
			psList.add(psDTO);

			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.FLOAT);
			psDTO.setPosition(6);
			psDTO.setValue(Float.parseFloat(request.getParameter("negativemark")));
			psList.add(psDTO);

			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(7);
			psDTO.setValue(Integer.parseInt(request.getParameter("testid")));
			psList.add(psDTO);
			int result=0;
			result=dao.cud(SQLConstants.UPDATE_TEST.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(result>0){
				message=true;
			}
			jsonString= gson.toJson(message);
		} catch (ClassNotFoundException | SQLException  e) {
			LOGGER.error(e.getMessage(), e);
			 jsonString = gson.toJson(message);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}

}
