package com.o9paathshala.batches.servlets;

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
 * Servlet implementation class TransferBatch
 */
@WebServlet("/ChangeBatch")
public class ChangeBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeBatch() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(ChangeBatch.class);

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
		DAO dao=DAOFactory.getDAOObject();
		Gson gson = new Gson();
		String jsonString=null;
		int data=0;
		boolean result=false;
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=new PreparedStatementDTO();
		try {
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(1);
			psDTO.setValue(Integer.parseInt(request.getParameter("batch")));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(2);
			psDTO.setValue(Integer.parseInt(request.getParameter("studentid")));
			psList.add(psDTO);
			data=dao.cud(SQLConstants.CHANGE_BATCH.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()), psList);
			if(data>0){
				result=true;
			}
			jsonString = gson.toJson(result);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
			jsonString = gson.toJson( result);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}
