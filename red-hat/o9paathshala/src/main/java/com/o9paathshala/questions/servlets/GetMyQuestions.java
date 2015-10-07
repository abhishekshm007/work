package com.o9paathshala.questions.servlets;

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
import com.o9paathshala.questions.dto.QuestionDTO;


@WebServlet("/GetMyQuestions")
public class GetMyQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetMyQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(GetMyQuestions.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     	HttpSession session=request.getSession();
		int startRow = Integer.parseInt(request.getParameter("iDisplayStart"));
		int noofRecords=Integer.parseInt(request.getParameter("iDisplayLength"));
		int echo = Integer.parseInt(request.getParameter("sEcho"));
		int id=Integer.parseInt(request.getParameter("subjectid"));
		int totalRecords = 0;
		Gson gson = new Gson();
		String jsonString=null;
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		PreparedStatementDTO psDTO = new PreparedStatementDTO();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		List<QuestionDTO> data=new ArrayList<QuestionDTO>();
		try{
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(id);
		psList.add(psDTO);
			StringBuilder sql2 = new StringBuilder(SQLConstants.COUNT_MY_QUESTION.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
			if(request.getParameter("sSearch") != null) {
				String search=request.getParameter("sSearch");
				sql2.append(" like '"+search+"%'");
			}
			resultList= dao.getAll(sql2.toString(),psList);	
			
			if(null!=resultList){
				totalRecords= Integer.parseInt(resultList.get(0).get("count").toString());
			}
			if(totalRecords>0){
			StringBuilder sql1 = new StringBuilder(SQLConstants.GET_MY_QUESTIONS);
			if(request.getParameter("sSearch") != null) {
				String search=request.getParameter("sSearch");
				sql1.append(" like '"+search+"%' order by topic asc limit ?,?");
			}
			psDTO = new PreparedStatementDTO();
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
				String question=null;
				for(int i=0;i<resultList.size();i++){
					QuestionDTO dto=new QuestionDTO();
					dto.setId(Integer.parseInt(resultList.get(i).get("question_id").toString()));
					question=new String((byte[])resultList.get(i).get("question_content"));
					if(question.length()>100){
					dto.setContent(question.substring(0,100 )+"...");
					}else{
						dto.setContent(question);	
					}
					dto.setTopic(new String((byte[]) resultList.get(i).get("topic")));
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
