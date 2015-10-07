package com.o9paathshala.questions.servlets;

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

/**
 * Servlet implementation class AddSingleStudent
 */
@WebServlet("/AddSingleQuestion")
public class AddSingleQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSingleQuestion() {
		super();
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(AddSingleQuestion.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String jsonString=null;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		PrintWriter out=response.getWriter();
		DAO dao=DAOFactory.getDAOObject();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=new PreparedStatementDTO();
		Gson gson=new Gson();
		Boolean message=false;
		try {
			psDTO.setDataType(DBConstants.BLOB);
			psDTO.setPosition(1);
			psDTO.setValue(request.getParameter("question").getBytes());
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();

			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(2);
			if(null!=request.getParameter("option1")){
				psDTO.setValue(request.getParameter("option1"));
			}else{
				psDTO.setValue(" ");
			}
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();

			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(3);
			if(null!=request.getParameter("option2")){
				psDTO.setValue(request.getParameter("option2"));
			}else{
				psDTO.setValue(" ");
			}
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();

			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(4);
			if(null!=request.getParameter("option3")){
				psDTO.setValue(request.getParameter("option3"));
			}else{
				psDTO.setValue(" ");
			}
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();


			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(5);
			if(null!=request.getParameter("option4")){
				psDTO.setValue(request.getParameter("option4"));
			}else{
				psDTO.setValue(" ");
			}
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();

			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(6);
			if(null!=request.getParameter("option5")){
				psDTO.setValue(request.getParameter("option5"));
			}else{
				psDTO.setValue(" ");
			}
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();

			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(7);
			if(null!=request.getParameter("option6")){
				psDTO.setValue(request.getParameter("option6"));
			}else{
				psDTO.setValue(" ");
			}
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(8);
			psDTO.setValue(request.getParameter("answer"));
			psList.add(psDTO);

			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(9);
			psDTO.setValue(Integer.parseInt(request.getParameter("subject")));
			psList.add(psDTO);

			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(10);
			psDTO.setValue(request.getParameter("topic"));
			psList.add(psDTO);
			int result=0;
			result=dao.cud(SQLConstants.ADD_SINGLE_QUESTION.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(result>0){
				message=true;

			}
			jsonString = gson.toJson(message);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
			jsonString = gson.toJson(message);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}

}
