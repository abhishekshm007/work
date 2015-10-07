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
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.PaginationDTO;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.student.dto.StudentDTO;

/**
 * Servlet implementation class GetAllStudents
 */
@WebServlet("/GetAllStudents")
public class GetAllStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllStudents() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(GetAllStudents.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int startRow = Integer.parseInt(request.getParameter("iDisplayStart"));
		int noofRecords=Integer.parseInt(request.getParameter("iDisplayLength"));
		int echo = Integer.parseInt(request.getParameter("sEcho"));
		int totalRecords = 0;
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		PreparedStatementDTO psDTO = null;
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		List<StudentDTO> data=new ArrayList<StudentDTO>();
		Gson gson = new Gson();
		String jsonString=null;
		try{
			StringBuilder sql2=null;
			StringBuilder sql1=null;
			int type=((SessionDTO)session.getAttribute("user")).getType();
			int k=0;
			switch(type){
			case 1:sql1 = new StringBuilder(SQLConstants.GET_STUDENTS);
			sql2 = new StringBuilder(SQLConstants.COUNT_STUDENT);
			break;
			case 2:sql1 = new StringBuilder(SQLConstants.GET_FACULTY_STUDENT);
			sql2 = new StringBuilder(SQLConstants.TOTAL_MY_STUDENT+"and student_name");
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(++k);
			psDTO.setValue(((SessionDTO)session.getAttribute("user")).getId());
			psList.add(psDTO);
			break;
			default: break;
			}
			if(request.getParameter("sSearch") != null) {
				String search=request.getParameter("sSearch");
				sql2.append(" like '"+search+"%'");
			}
			resultList= dao.getAll(sql2.toString().replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);	

			if(null!=resultList){
				totalRecords= Integer.parseInt(resultList.get(0).get("count").toString());
			}
			if(totalRecords>0){
				if(request.getParameter("sSearch") != null) {
					String search=request.getParameter("sSearch");
					sql1.append(" like '"+search+"%' order by student_name asc limit ?,?");
				}
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(++k);
				psDTO.setValue(startRow);
				psList.add(psDTO);
				psDTO =new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(++k);
				psDTO.setValue(noofRecords);
				psList.add(psDTO);
				resultList=dao.getAll((sql1.toString()).replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
				if(null!=resultList){
					for(int i=0;i<resultList.size();i++){
						StudentDTO dto=new StudentDTO();
						dto.setId(Integer.parseInt(resultList.get(i).get("student_id").toString()));
						dto.setEmail(resultList.get(i).get("student_email").toString());
						dto.setName(resultList.get(i).get("student_name").toString());
						dto.setBatch(resultList.get(i).get("batch_name").toString());
						if(null!=resultList.get(i).get("student_contact")){
							dto.setContact(resultList.get(i).get("student_contact").toString());
						}else{
							dto.setContact("-");
						}

						data.add(dto);
					}

				}
			}
			PaginationDTO paginationDTO = new PaginationDTO();
			paginationDTO.setsEcho(echo);
			paginationDTO.setAaData(data);
			paginationDTO.setiTotalDisplayRecords(totalRecords);
			paginationDTO.setiTotalRecords(totalRecords);
			jsonString = gson.toJson(paginationDTO);
		} catch (ClassNotFoundException | SQLException e) {			
			LOGGER.error(e.getMessage(), e);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}