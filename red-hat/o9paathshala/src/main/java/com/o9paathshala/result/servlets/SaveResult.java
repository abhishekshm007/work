package com.o9paathshala.result.servlets;

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

@WebServlet("/SaveResult")
public class SaveResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SaveResult() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveResult.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Gson gson = new Gson();
		String jsonString=null;
		HttpSession session=request.getSession();
		Integer testid=Integer.parseInt(request.getParameter("testid"));
		Integer userid=((SessionDTO)session.getAttribute("user")).getId();
		DAO dao=DAOFactory.getDAOObject();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(testid);
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(2);
		psDTO.setValue(request.getParameter("testname"));
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.FLOAT);
		psDTO.setPosition(3);
		psDTO.setValue(Float.parseFloat(request.getParameter("mac")));
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(4);
		psDTO.setValue(((SessionDTO)session.getAttribute("user")).getBatchid());
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(5);
		psDTO.setValue(userid);
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(6);
		psDTO.setValue(testid);
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(7);
		psDTO.setValue(userid);
		psList.add(psDTO);
		int data=0;
		boolean result=false;
		try {
			data=dao.cud(SQLConstants.SAVE_RESULT.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()), psList);
			session.removeAttribute("test");
			if(data>0){
				result=true;
			}
			jsonString = gson.toJson(result);
		}catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
			jsonString = gson.toJson(result);
		}finally{
		out.println(jsonString);
		out.close();
		}
	}

}
