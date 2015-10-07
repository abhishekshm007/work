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

@WebServlet("/GetAllTests")
public class GetAllTests extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllTests() {
		super();
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(GetAllTests.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		int startRow = Integer.parseInt(request.getParameter("iDisplayStart"));
		int noofRecords=Integer.parseInt(request.getParameter("iDisplayLength"));
		int echo = Integer.parseInt(request.getParameter("sEcho"));
		int totalRecords = 0;
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		PreparedStatementDTO psDTO = null;
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		List<TestDTO> data=new ArrayList<TestDTO>();
		StringBuilder sql2=null;
		StringBuilder sql1=null;
		Gson gson = new Gson();
		String jsonString=null;
		try{
		int type=((SessionDTO)session.getAttribute("user")).getType();
		int k=0;
		switch(type){
		case 1:sql1 = new StringBuilder(SQLConstants.GET_TESTS);
		sql2 = new StringBuilder(SQLConstants.COUNT_TEST);
		break;
		case 2:sql1 = new StringBuilder(SQLConstants.GET_FACULTY_TESTS);
		sql2 = new StringBuilder(SQLConstants.COUNT_FACULTY_TEST);
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
					sql1.append(" like '"+search+"%' order by test_upload_time desc limit ?,?");
				}

				psDTO=new PreparedStatementDTO();
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
						TestDTO dto=new TestDTO();
						dto.setId(Integer.parseInt(resultList.get(i).get("test_id").toString()));
						dto.setTestName(resultList.get(i).get("test_name").toString());
						dto.setActivated(Boolean.parseBoolean(resultList.get(i).get("test_activation").toString()));
						dto.setDuration(Integer.parseInt(resultList.get(i).get("test_duration").toString()));
						if(null!=resultList.get(i).get("test_end_date")){
							dto.setEnddate(Timestamp.valueOf(resultList.get(i).get("test_end_date").toString()));
						}else{
							dto.setEnddate(null);
						}
						dto.setType(Boolean.parseBoolean(resultList.get(i).get("test_type").toString()));
						
						dto.setCreatedBy(resultList.get(i).get("test_created_by_name").toString());
						dto.setStartdate(Timestamp.valueOf(resultList.get(i).get("test_start_date").toString()));
						dto.setNegativeMark(Float.parseFloat(resultList.get(i).get("test_negative_mark").toString()));
						dto.setPositiveMark(Float.parseFloat(resultList.get(i).get("test_positive_mark").toString()));
						dto.setUploaddate(Timestamp.valueOf(resultList.get(i).get("test_upload_time").toString()));
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

