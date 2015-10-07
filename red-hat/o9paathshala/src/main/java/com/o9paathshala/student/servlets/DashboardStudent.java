package com.o9paathshala.student.servlets;

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
import com.o9paathshala.util.TotalRecords;

/**
 * Servlet implementation class DashboardStudent
 */
@WebServlet("/DashboardStudent")
public class DashboardStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardStudent.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		boolean message=false;
		String jsonString=null;
		int count=0;
		try{
			DAO dao=DAOFactory.getDAOObject();
			List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
			PreparedStatementDTO psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(1);
			psDTO.setValue(Integer.parseInt(((SessionDTO)session.getAttribute("user")).getBatchid().toString()));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(2);
			psDTO.setValue(Integer.parseInt(((SessionDTO)session.getAttribute("user")).getId().toString()));
			psList.add(psDTO);
			count=TotalRecords.totalRecords(dao.getAll(SQLConstants.TOTAL_UNATTEMPTED_TEST.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList));
			jsonString = gson.toJson(count);
			
		}catch(ClassNotFoundException | SQLException e){
			LOGGER.error(e.getMessage(), e);
			jsonString = gson.toJson(message);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}

}
