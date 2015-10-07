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

/**
 * Servlet implementation class GetAllServlets
 */
@WebServlet("/GetAllResults")
public class GetAllResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllResults() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAllResults.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
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
		PreparedStatementDTO psDTO = null;
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		List<ResultDTO> data=new ArrayList<ResultDTO>();
		Gson gson = new Gson();
		String jsonString=null;
		try{
		Integer testid=Integer.parseInt(request.getParameter("testid"));
		Integer batchid=Integer.parseInt(request.getParameter("batchid"));
		StringBuilder sql2 = new StringBuilder(SQLConstants.COUNT_RESULTS.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
		StringBuilder sql1 = new StringBuilder(SQLConstants.GET_RESULTS);
		int j=0;
		if(0==testid){
			sql1=new StringBuilder(sql1.toString().replace("test_id=?","test_id=test_id") );
			sql2=new StringBuilder(sql2.toString().replace("test_id=?","test_id=test_id") );
		}else{
			psDTO =new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setValue(testid);
			psDTO.setPosition(++j);
			psList.add(psDTO);
		}
		if(0==batchid){
			sql1=new StringBuilder(sql1.toString().replace("batch_id=?","batch_id=batch_id" ));
			sql2=new StringBuilder(sql2.toString().replace("batch_id=?","batch_id=batch_id" ));
		}else{
			psDTO =new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setValue(batchid);
			psDTO.setPosition(++j);
			psList.add(psDTO);
		}
			if(request.getParameter("sSearch") != null) {
				String search=request.getParameter("sSearch");
				sql2.append(" like '"+search+"%'");
			}
			resultList= dao.getAll(sql2.toString(),psList);	
			
			if(null!=resultList){
				totalRecords= Integer.parseInt(resultList.get(0).get("count").toString());
			}
			if(totalRecords>0){
			
			if(request.getParameter("sSearch") != null) {
				String search=request.getParameter("sSearch");
				sql1.append(" like '"+search+"%' order by attempt_date desc limit ?,?");
			}
			psDTO =new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(++j);
			psDTO.setValue(startRow);
			psList.add(psDTO);
			psDTO =new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(++j);
			psDTO.setValue(noofRecords);
			psList.add(psDTO);
			resultList=dao.getAll((sql1.toString()).replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList){
				for(int i=0;i<resultList.size();i++){
					ResultDTO dto=new ResultDTO();
					dto.setAttempt(Integer.parseInt(resultList.get(i).get("attempt").toString()));
					dto.setAttemptdate(Timestamp.valueOf(resultList.get(i).get("attempt_date").toString()));
					dto.setBatch(resultList.get(i).get("batch_name").toString());
					dto.setScore(Float.parseFloat(resultList.get(i).get("score").toString()));
					dto.setStudent(resultList.get(i).get("student_name").toString());
					dto.setTest(resultList.get(i).get("test_name").toString());
					dto.setStudentid(Integer.parseInt(resultList.get(i).get("student_id").toString()));
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
