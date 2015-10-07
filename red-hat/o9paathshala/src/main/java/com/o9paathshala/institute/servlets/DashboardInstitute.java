package com.o9paathshala.institute.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.institute.dto.InstituteDashboardDTO;
import com.o9paathshala.util.TotalRecords;


@WebServlet("/DashboardInstitute")
public class DashboardInstitute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DashboardInstitute() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardInstitute.class);
	
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
			DAO dao=DAOFactory.getDAOObject();
			InstituteDashboardDTO dto=new InstituteDashboardDTO();
			dto.setTest(TotalRecords.totalRecords(dao.getAll(SQLConstants.TOTAL_TEST.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),null)));
			dto.setFaculty(TotalRecords.totalRecords(dao.getAll(SQLConstants.TOTAL_FACULTY.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),null)));
			dto.setPost(TotalRecords.totalRecords(dao.getAll(SQLConstants.TOTAL_POST.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),null)));
			dto.setQuestion(TotalRecords.totalRecords(dao.getAll(SQLConstants.TOTAL_QUESTION.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),null)));
			dto.setSet(TotalRecords.totalRecords(dao.getAll(SQLConstants.TOTAL_SET,null)));
			dto.setStudent(TotalRecords.totalRecords(dao.getAll(SQLConstants.TOTAL_STUDENT.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),null)));
			dto.setSubject(TotalRecords.totalRecords(dao.getAll(SQLConstants.TOTAL_SUBJECT.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),null)));
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
