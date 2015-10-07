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
import com.o9paathshala.test.dto.QuestionSectionDTO;
import com.o9paathshala.test.dto.SectionDTO;

@WebServlet("/GetMyTestQuestion")
public class GetMyTestQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GetMyTestQuestion() {
        super();
       
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(GetMyTestQuestion.class);
	
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
			PreparedStatementDTO psDTO = new PreparedStatementDTO();
			List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
			List<Map<String, Object>> resultList = null;
			List<QuestionSectionDTO> data=new ArrayList<QuestionSectionDTO>();
			Gson gson = new Gson();
			String jsonString=null;
			try{
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(1);
			psDTO.setValue(Integer.parseInt(request.getParameter("id")));
			psList.add(psDTO);
			
				StringBuilder sql2 = new StringBuilder(SQLConstants.COUNT_MY_TEST_QUESTION.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
				if(request.getParameter("sSearch") != null) {
					String search=request.getParameter("sSearch");
					sql2.append(" like '"+search+"%'");
				}
				resultList= dao.getAll(sql2.toString(),psList);	
				
				if(null!=resultList){
					totalRecords= Integer.parseInt(resultList.get(0).get("count").toString());
				}
				if(totalRecords>0){
				StringBuilder sql1 = new StringBuilder(SQLConstants.GET_MY_TEST_QUESTIONS);
				if(request.getParameter("sSearch") != null) {
					String search=request.getParameter("sSearch");
					sql1.append(" like '"+search+"%' order by section_name asc limit ?,?");
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
						if(null!=resultList.get(i).get("question_id")){
						QuestionDTO dto=new QuestionDTO();
						SectionDTO dto1=new SectionDTO();
						QuestionSectionDTO dto2=new QuestionSectionDTO();
						dto.setId(Integer.parseInt(resultList.get(i).get("question_id").toString()));
						byte[] blob=(byte[]) resultList.get(i).get("question_content");
						question=new String(blob);
						if(question.length()>100){
						dto.setContent(question.substring(0,100 )+"...");
						}else{
							dto.setContent(question);	
						}
						dto1.setSectionName(resultList.get(i).get("section_name").toString());
						dto1.setSectionID(Integer.parseInt(resultList.get(i).get("section_id").toString()));
						dto2.setQuestion(dto);
						dto2.setSection(dto1);
						data.add(dto2);
					}
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