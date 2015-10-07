package com.o9paathshala.faculty.servlets;

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
import com.o9paathshala.faculty.dto.FacultyDashboardDTO;
import com.o9paathshala.util.TotalRecords;


@WebServlet("/DashboardFaculty")
public class DashboardFaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DashboardFaculty() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardFaculty.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		boolean message=false;
		String jsonString=null;
		try{
			HttpSession session=request.getSession(false);
			DAO dao=DAOFactory.getDAOObject();
			List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
			PreparedStatementDTO psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(1);
			psDTO.setValue(Integer.parseInt(((SessionDTO)session.getAttribute("user")).getId().toString()));
			psList.add(psDTO);
			FacultyDashboardDTO dto=new FacultyDashboardDTO();
			dto.setTest(TotalRecords.totalRecords(dao.getAll(SQLConstants.TOTAL_MY_TEST.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList)));
			dto.setStudent(TotalRecords.totalRecords(dao.getAll(SQLConstants.TOTAL_MY_STUDENT.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList)));
			jsonString = gson.toJson(dto);
		}catch(ClassNotFoundException | SQLException e){
			LOGGER.error(e.getMessage(), e);
			jsonString = gson.toJson(message);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}

}
