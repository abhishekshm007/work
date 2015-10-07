package com.o9paathshala.student.servlets;

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
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.student.dto.StudentDTO;

/**
 * Servlet implementation class AutoComplete
 */
@WebServlet("/AutoCompleteStudent")
public class AutoCompleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AutoCompleteStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoCompleteStudent.class);
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
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		List<Map<String, Object>> resultList = null;
		List<StudentDTO> data=new ArrayList<StudentDTO>();
		Gson gson = new Gson();
		String jsonString=null;
		try{
		StringBuilder sql1 = new StringBuilder(SQLConstants.AUTO_COMPLETE_STUDENT);
		if(request.getParameter("tagName") != null) {
			String search=request.getParameter("tagName");
			sql1.append(" like '"+search+"%' order by student_name asc limit 5");
		}
			resultList=dao.getAll(sql1.toString().replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),null);
			if(null!=resultList){
				for(int i=0;i<resultList.size();i++){
					StudentDTO dto=new StudentDTO();
					dto.setName(resultList.get(i).get("student_name").toString());
					dto.setId(Integer.parseInt(resultList.get(i).get("student_id").toString()));
					dto.setEmail(resultList.get(i).get("student_email").toString());
					data.add(dto);
				}

			}
			jsonString = gson.toJson(data);
			
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}

}
