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
import com.o9paathshala.test.dto.UnAttemptedTestDTO;


@WebServlet("/UnAttemptedTest")
public class UnAttemptedTest extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public UnAttemptedTest() {
		super();
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(UnAttemptedTest.class);


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		int echo = Integer.parseInt(request.getParameter("sEcho"));
		int totalRecords = 0;
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		PreparedStatementDTO psDTO = new PreparedStatementDTO();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		List<UnAttemptedTestDTO> data=new ArrayList<UnAttemptedTestDTO>();
		Gson gson = new Gson();
		String jsonString=null;
		try {
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(Integer.parseInt(((SessionDTO)session.getAttribute("user")).getBatchid().toString()));
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(2);
		psDTO.setValue(Integer.parseInt(((SessionDTO)session.getAttribute("user")).getId().toString()));
		psList.add(psDTO);
		
			resultList= dao.getAll(SQLConstants.TOTAL_UNATTEMPTED_TEST.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);	
			if(null!=resultList){
				totalRecords= Integer.parseInt(resultList.get(0).get("count").toString());
			}
			if(totalRecords>0){

			resultList=dao.getAll(SQLConstants.UNATTEMPTED_TEST.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList){
				for(int i=0;i<resultList.size();i++){
					UnAttemptedTestDTO dto=new UnAttemptedTestDTO();
					dto.setName(resultList.get(i).get("test_name").toString());
					if(null!=resultList.get(i).get("test_end_date")){
						dto.seteDate(Timestamp.valueOf(resultList.get(i).get("test_end_date").toString()));
					}else{
						dto.seteDate(null);
					}
					dto.setsDate(Timestamp.valueOf(resultList.get(i).get("test_start_date").toString()));
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

