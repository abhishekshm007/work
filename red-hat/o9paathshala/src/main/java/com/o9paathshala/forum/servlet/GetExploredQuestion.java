package com.o9paathshala.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.forum.dto.AnswerDTO;
import com.o9paathshala.forum.dto.ExploredQuestionDTO;
import com.o9paathshala.forum.dto.QuestionDTO;
import com.o9paathshala.forum.dto.TagDTO;

/**
 * Servlet implementation class GetExploredQuestion
 */
@WebServlet("/GetExploredQuestion")
public class GetExploredQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final  Logger LOGGER = LoggerFactory.getLogger(GetExploredQuestion.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetExploredQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		PrintWriter out = response.getWriter();
		try{
			HttpSession session = request.getSession(false);
			SessionDTO user = (SessionDTO) session.getAttribute("user");
			
			// checks for session
			if(null == user){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Bad request occured!!");
				return;
			}
			
			// getting question id for pulling question data
			Integer questionId = Integer.parseInt(request.getParameter("question"));
			
			// initialization and declaring members 
			List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
			DAO dao = DAOFactory.getDAOObject();
			List<AnswerDTO> answers = new ArrayList<AnswerDTO>();
			QuestionDTO question = new QuestionDTO();
			AnswerDTO answer = null;
			List<PreparedStatementDTO> psListForQuestion = new ArrayList<PreparedStatementDTO>();
			List<PreparedStatementDTO> psListForAnswer = new ArrayList<PreparedStatementDTO>();
			PreparedStatementDTO ps = null;
			ExploredQuestionDTO exploredQuestion = new ExploredQuestionDTO();
			Gson gson = new Gson();
			// getting query for question
			String sqlForQuestion = SQLConstants.GET_EXPLORED_QUESTION.replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString());
			
			// getting query of answers
			String sqlForAnswers = SQLConstants.GET_POST_ANSWER.replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString());
			
			// setting prepared statement data for question 
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.INTEGER);
			ps.setPosition(1);
			ps.setValue(questionId);
			psListForQuestion.add(ps);
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.INTEGER);
			ps.setPosition(2);
			ps.setValue(user.getId());
			psListForQuestion.add(ps);
			
			// setting prepared statement data for answer 
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.INTEGER);
			ps.setPosition(1);
			ps.setValue(user.getId());
			psListForAnswer.add(ps);
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.INTEGER);
			ps.setPosition(2);
			ps.setValue(questionId);
			psListForAnswer.add(ps);
			
			output = dao.getAll(sqlForQuestion, psListForQuestion);
			if(null != output){
				question.setId((Integer) output.get(0).get("post_id"));
				question.setTitle((String) output.get(0).get("post_title"));
				question.setContent((String) output.get(0).get("post_content"));
				question.setTime((Timestamp) output.get(0).get("post_time"));
				question.setUserId((Integer) output.get(0).get("user_id"));
				question.setUserName((String) output.get(0).get("user_name"));
				question.setAnswers((Long) output.get(0).get("answers"));
				question.setReputation((Long) output.get(0).get("reputation"));
				question.setLiked((Long) output.get(0).get("liked") == 1 ? true : false);
				
				List<TagDTO> tags = new ArrayList<TagDTO>();
				TagDTO tag = new TagDTO();
				
				tag.setTagId((Integer) output.get(0).get("tag_id"));
				tag.setTagName((String) output.get(0).get("tag_name"));
				tag.setTagDesc((String) output.get(0).get("tag_desc"));
				tag.setTagReputation((Integer) output.get(0).get("tag_reputation"));
				question.setTags(tags);
			}
		
			output = dao.getAll(sqlForAnswers, psListForAnswer);
			if(null != output){
				for(Map<String, Object> ans : output){
					answer = new AnswerDTO();
					answer.setAnswerId((Integer)ans.get("answer_id"));
					answer.setUserId((Integer)ans.get("user_id"));
					answer.setAnswer((String)ans.get("answer_content"));
					answer.setUsername((String)ans.get("user_name"));
					answer.setReputation((Long)ans.get("reputation"));
					answer.setDate((Timestamp)ans.get("answer_time"));
					answer.setLiked((Long)ans.get("liked") == 1 ? true : false);
					answers.add(answer);
				}
			}
		
			exploredQuestion.setQuestion(question);
			exploredQuestion.setAnswers(answers);
			
			String data = gson.toJson(exploredQuestion);
			response.setStatus(HttpServletResponse.SC_OK);
			out.print(data);
			return;
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.print("Internal server error ocurred!!!");
			return;
		}finally{
			if(null != out){
				out.close();
			}
		}
		
	}

}
