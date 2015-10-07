package com.o9paathshala.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.o9paathshala.test.dto.TestDTO;

@WebServlet("/StudentTests")
public class StudentTests extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentTests() {
		super();
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentTests.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		int startRow = Integer.parseInt(request.getParameter("iDisplayStart"));
		int noofRecords=Integer.parseInt(request.getParameter("iDisplayLength"));
		int echo = Integer.parseInt(request.getParameter("sEcho"));
		int totalRecords = 0;
		Timestamp start=null;
		Timestamp end=null;
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		PreparedStatementDTO psDTO = null;
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<PreparedStatementDTO> psList1=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		List<Integer> attemptTest=new ArrayList<Integer>();
		List<TestDTO> data=new ArrayList<TestDTO>();
		Gson gson = new Gson();
		String jsonString=null;
		try{
		StringBuilder sql2=new StringBuilder(SQLConstants.COUNT_STUDENT_TEST);
		StringBuilder sql1=new StringBuilder(SQLConstants.GET_STUDENT_TEST);
		StringBuilder sql3=new StringBuilder(SQLConstants.GET_ATTEMPTED_TEST);
		if(request.getParameter("sSearch") != null) {
			String search=request.getParameter("sSearch");
			sql1.append(" like '"+search+"%' order by test_upload_time desc limit ?,?");
			sql2.append(" like '"+search+"%'");
			sql3.append(" like '"+search+"%'");
		}
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(((SessionDTO)session.getAttribute("user")).getBatchid());
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.BOOLEAN);
		psDTO.setPosition(2);
		psDTO.setValue(true);
		psList.add(psDTO);
		
			resultList= dao.getAll(sql2.toString().replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);	
			if(null!=resultList){
				totalRecords= Integer.parseInt(resultList.get(0).get("count").toString());
			}
			if(totalRecords>0){
				psDTO=new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(1);
				psDTO.setValue(((SessionDTO)session.getAttribute("user")).getId());
				psList1.add(psDTO);
				resultList= dao.getAll(sql3.toString().replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList1);	
				if(null!=resultList){
					for(int i=0;i<resultList.size();i++){
						attemptTest.add(Integer.parseInt(resultList.get(i).get("test_id").toString()));
					}
				}
				
				psDTO=new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(3);
				psDTO.setValue(startRow);
				psList.add(psDTO);
				psDTO =new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(4);
				psDTO.setValue(noofRecords);
				psList.add(psDTO);
				resultList=dao.getAll((sql1.toString()).replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
				if(null!=resultList){
					java.util.Date date = new java.util.Date();
					Timestamp current=new Timestamp(date.getTime());
					for(int i=0;i<resultList.size();i++){
						TestDTO dto=new TestDTO();
						int id=Integer.parseInt(resultList.get(i).get("test_id").toString());
						dto.setId(id);
						dto.setTestName(resultList.get(i).get("test_name").toString());
						if(attemptTest.contains(id)){
							dto.setAttempted(true);
						}else{
							dto.setAttempted(false);
						}
						dto.setDuration(Integer.parseInt(resultList.get(i).get("test_duration").toString()));
						start=Timestamp.valueOf(resultList.get(i).get("test_start_date").toString());
						
						if(null!=resultList.get(i).get("test_end_date")){
							end=Timestamp.valueOf(resultList.get(i).get("test_end_date").toString());
							dto.setEnddate(end);
						}else{
							end=null;
							dto.setEnddate(null);
						}
						dto.setCreatedBy(resultList.get(i).get("test_created_by_name").toString());
						dto.setStartdate(start);
						dto.setNegativeMark(Float.parseFloat(resultList.get(i).get("test_negative_mark").toString()));
						dto.setPositiveMark(Float.parseFloat(resultList.get(i).get("test_positive_mark").toString()));
						if(start.after(current)||(end!=null&&end.before(current))){
							dto.setActivated(false);
						}else{
							dto.setActivated(true);
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

