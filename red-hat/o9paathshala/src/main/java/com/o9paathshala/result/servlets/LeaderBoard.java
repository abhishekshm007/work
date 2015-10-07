package com.o9paathshala.result.servlets;

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
import com.o9paathshala.result.dto.LeaderBoardDTO;

@WebServlet("/LeaderBoard")
public class LeaderBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeaderBoard() {
		super();
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(LeaderBoard.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int echo = Integer.parseInt(request.getParameter("sEcho"));
		int totalRecords = 0;
		int limit=Integer.parseInt(request.getParameter("limit"));
		int id=((SessionDTO)session.getAttribute("user")).getId();
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		PreparedStatementDTO psDTO = new PreparedStatementDTO();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		List<LeaderBoardDTO> data1=new ArrayList<LeaderBoardDTO>();
		List<LeaderBoardDTO> data=null;
		Gson gson = new Gson();
		String jsonString =null;
		try{
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(Integer.parseInt(request.getParameter("id")));
		psList.add(psDTO);
	
			StringBuilder sql2 = new StringBuilder(SQLConstants.LEADERBOARD_COUNT.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
			resultList= dao.getAll(sql2.toString(),psList);	
			if(null!=resultList){
				totalRecords= Integer.parseInt(resultList.get(0).get("count").toString());
			}
			if(totalRecords>0){
				resultList=dao.getAll(SQLConstants.GET_STUDENTS_RANK.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
				if(null!=resultList){
					data=new ArrayList<LeaderBoardDTO>();
					for(int i=0;i<resultList.size();i++){
						LeaderBoardDTO dto=new LeaderBoardDTO();
						dto.setId(Integer.parseInt(resultList.get(i).get("student_id").toString()));
						dto.setName(resultList.get(i).get("student_name").toString());
						dto.setAttempt(Integer.parseInt(resultList.get(i).get("attempt").toString()));
						dto.setScore(Float.parseFloat(resultList.get(i).get("score").toString()));
						if(data.contains(dto)){
							data.remove(dto);
						}
						data.add(dto);
					}
					for(int j=0;j<data.size();j++){
						data.get(j).setRank(j+1);
					}
					if(data.size()>limit){
						data1=data.subList(0, limit);
					}else{
						data1=data;
					}
					LeaderBoardDTO dto=new LeaderBoardDTO();
					dto.setId(id);
					if(!data1.contains(dto)&&data.contains(dto)){
						data1.add(data.get(data.indexOf(dto)));
					}
				}
			}
			PaginationDTO paginationDTO = new PaginationDTO();
			paginationDTO.setsEcho(echo);
			paginationDTO.setAaData(data1);
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

