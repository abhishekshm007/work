package com.o9paathshala.result.servlets;

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
import com.o9paathshala.result.dto.ResultDTO;

@WebServlet("/MyAllResult")
public class MyAllResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAllResult() {
        super();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAllResult.class);
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int startRow = Integer.parseInt(request.getParameter("iDisplayStart"));
		int noofRecords=Integer.parseInt(request.getParameter("iDisplayLength"));
		int echo = Integer.parseInt(request.getParameter("sEcho"));
		int totalRecords = 0;
		Gson gson = new Gson();
		String jsonString =null;
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		PreparedStatementDTO psDTO = new PreparedStatementDTO();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		List<ResultDTO> data=new ArrayList<ResultDTO>();
		try{
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(((SessionDTO)session.getAttribute("user")).getId());
		psList.add(psDTO);
		
			StringBuilder sql2 = new StringBuilder(SQLConstants.COUNT_MY_RESULTS.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
			if(request.getParameter("sSearch") != null) {
				String search=request.getParameter("sSearch");
				sql2.append(" like '"+search+"%'");
			}
			resultList= dao.getAll(sql2.toString(),psList);	
			
			if(null!=resultList){
				totalRecords= Integer.parseInt(resultList.get(0).get("count").toString());
			}
			if(totalRecords>0){
			StringBuilder sql1 = new StringBuilder(SQLConstants.GET_MY_RESULTS);
			if(request.getParameter("sSearch") != null) {
				String search=request.getParameter("sSearch");
				sql1.append(" like '"+search+"%' order by attempt_date desc limit ?,?");
			}
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(2);
			psDTO.setValue(startRow);
			psList.add(psDTO);
			psDTO =new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(3);
			psDTO.setValue(noofRecords);
			psList.add(psDTO);
			resultList=dao.getAll((sql1.toString()).replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList){
				for(int i=0;i<resultList.size();i++){
					ResultDTO dto=new ResultDTO();
					dto.setAttempt(Integer.parseInt(resultList.get(i).get("attempt").toString()));
					dto.setAttemptdate((Timestamp)resultList.get(i).get("attempt_date"));
					dto.setTest(resultList.get(i).get("test_name").toString());
					dto.setScore(Float.parseFloat(resultList.get(i).get("score").toString()));
					data.add(dto);
				}
			}
			}
			PaginationDTO paginationDTO = new PaginationDTO();
			paginationDTO.setsEcho(echo);
			paginationDTO.setAaData(data);
			paginationDTO.setiTotalDisplayRecords(totalRecords);
			paginationDTO.setiTotalRecords(totalRecords);
			jsonString= gson.toJson(paginationDTO);
		} catch (ClassNotFoundException | SQLException e) {			
			LOGGER.error(e.getMessage(), e);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}

