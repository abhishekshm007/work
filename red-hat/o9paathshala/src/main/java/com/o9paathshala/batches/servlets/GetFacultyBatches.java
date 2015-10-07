package com.o9paathshala.batches.servlets;

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
import com.o9paathshala.batches.dto.BatchDTO;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.PaginationDTO;
import com.o9paathshala.dto.SessionDTO;


@WebServlet("/GetFacultyBatches")
public class GetFacultyBatches extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetFacultyBatches() {
		super();
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(GetFacultyBatches.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int startRow = Integer.parseInt(request.getParameter("iDisplayStart"));
		int noofRecords=Integer.parseInt(request.getParameter("iDisplayLength"));
		int echo = Integer.parseInt(request.getParameter("sEcho"));
		int totalRecords = 0;
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		PreparedStatementDTO psDTO = new PreparedStatementDTO();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		List<BatchDTO> data=new ArrayList<BatchDTO>();
		Gson gson = new Gson();
		String jsonString=null;
		try{
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(1);
			psDTO.setValue(Integer.parseInt(request.getParameter("id")));
			psList.add(psDTO);
			resultList= dao.getAll(SQLConstants.COUNT_BATCHES_ON_FACULTY.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);	

			if(null!=resultList){
				totalRecords= Integer.parseInt(resultList.get(0).get("count").toString());
			}
			if(totalRecords>0){
				StringBuilder sql1 = new StringBuilder(SQLConstants.GET_BATCHES_ON_FACULTY);
				if(request.getParameter("sSearch") != null) {
					String search=request.getParameter("sSearch");
					sql1.append("and a.batch_name like '"+search+"%'  order by a.batch_name asc limit ?,?");
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
						BatchDTO dto=new BatchDTO();
						dto.setId(Integer.parseInt(resultList.get(i).get("batch_id").toString()));
						dto.setName((String)resultList.get(i).get("batch_name"));
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