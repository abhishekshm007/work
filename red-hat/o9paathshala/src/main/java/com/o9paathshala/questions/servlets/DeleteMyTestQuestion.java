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
import com.o9paathshala.constants.ErrorMessageConstants;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;

/**
 * Servlet implementation class DeleteMyTestQuestion
 */
@WebServlet("/DeleteMyTestQuestion")
public class DeleteMyTestQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteMyTestQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteMyTestQuestion.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		DAO dao=DAOFactory.getDAOObject();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(Integer.parseInt(request.getParameter("qid")));
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(2);
		psDTO.setValue(Integer.parseInt(request.getParameter("sid")));
		psList.add(psDTO);
		Gson gson = new Gson();
		String jsonString=null;
		int data=0;
		boolean result=false;
		try {
			data=dao.cud(SQLConstants.DELETE_MY_TEST_QUESTION.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()), psList);
			if(data>0){
				result=true;
			}
			jsonString = gson.toJson(result);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
			jsonString = gson.toJson( ErrorMessageConstants.EXCEPTION_MESSAGE);
		}finally{
			out.println(jsonString);
			out.close();
		}

		
		
	}

}
