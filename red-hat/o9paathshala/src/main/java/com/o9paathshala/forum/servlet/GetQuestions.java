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
import com.o9paathshala.forum.dto.QuestionDTO;
import com.o9paathshala.forum.dto.TagDTO;

/**
 * Servlet implementation class GetQuestions
 */
@WebServlet("/GetQuestions")
public class GetQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final  Logger LOGGER = LoggerFactory.getLogger(GetQuestions.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetQuestions() {
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
	PrintWriter out;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(false);
			SessionDTO user = (SessionDTO) session.getAttribute("user");
			out = response.getWriter();
			if(null == user){
				LOGGER.error("session expired during get questions for student");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Bad request occured !!!");
				return;
			}
			
			List<Map<String, Object>> output = new ArrayList<Map<String,Object>>();
			DAO dao = DAOFactory.getDAOObject();
			PreparedStatementDTO psDTO = null;
			TagDTO tag = null;
			List<TagDTO> tags = new ArrayList<TagDTO>();
			List<PreparedStatementDTO> psList = new ArrayList<PreparedStatementDTO>();
			List<QuestionDTO> datas = new ArrayList<QuestionDTO>();
			Gson gson = new Gson();
			QuestionDTO data = null;
			String sql = null;
			
			
			String sort = request.getParameter("sort").trim();
			Integer limit = Integer.parseInt(request.getParameter("limit").trim());
			Integer after = Integer.parseInt(request.getParameter("after").trim());
			boolean flag = true;
			switch(sort){
			case "newest":
				sql = SQLConstants.GET_QUESTION_FOR_NEWEST_STUDENT;
				break;
			case "answered":
				sql = SQLConstants.GET_QUESTION_FOR_ANSWERED_STUDENT;
				break;
			case "mine":
				sql = SQLConstants.GET_QUESTION_FOR_MINE_STUDENT;
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(1);
				psDTO.setValue(user.getId());
				psList.add(psDTO);
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(2);
				psDTO.setValue(after);
				psList.add(psDTO);
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(3);
				psDTO.setValue(limit);
				psList.add(psDTO);
				flag= false;
				break;
			case "unanswered":
				sql = SQLConstants.GET_QUESTION_FOR_UNANSWERED_STUDENT;
				break;
			case "reputed":
				sql = SQLConstants.GET_QUESTION_FOR_REPUTED_STUDENT;
				break;
			default :
				sql = SQLConstants.GET_QUESTION_FOR_NEWEST_STUDENT;
			}

			if(flag){
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(1);
				psDTO.setValue(after);
				psList.add(psDTO);
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(2);
				psDTO.setValue(limit);
				psList.add(psDTO);
			}
			
			
			output = dao.getAll(sql.replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString()),psList);
			if(null != output){
				
				int previousId = (int) output.get(0).get("post_id");
				tags = new ArrayList<TagDTO>();
				data = new QuestionDTO();
				
				for(int i = 0 ; i < output.size();){
					if(previousId == (Integer)output.get(i).get("post_id")){
						tag = new TagDTO();
						tag.setTagId((Integer) output.get(i).get("tag_id"));
						tag.setTagName((String) output.get(i).get("tag_name"));
						tag.setTagReputation((Integer) output.get(i).get("tag_reputation"));
						tag.setTagDesc((String) output.get(i).get("tag_desc"));
						tags.add(tag);
						if(i < output.size()-1){
							i++;
							continue;
						}else{
							i++;
						}
					}
						i--;
						data.setId((Integer)output.get(i).get("post_id"));
						data.setTitle((String)output.get(i).get("post_title"));
						data.setContent((String)output.get(i).get("post_content"));
						data.setLiked((Long)output.get(i).get("liked") > 0 ? true : false);
						data.setReputation((Long)output.get(i).get("reputation"));
						data.setTime((Timestamp)output.get(i).get("post_time"));
						data.setUserId((Integer)output.get(i).get("user_id"));
						data.setUserName((String)output.get(i).get("user_name"));
						data.setAnswers((Long)output.get(i).get("answers"));
						data.setTags(tags);
						tags = new ArrayList<TagDTO>();
						datas.add(data);
						data = new QuestionDTO();
						if(i < output.size()-1){
							i++;
						}else{
								break;
							}
						previousId = (Integer)output.get(i).get("post_id");
				}
				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType("application/json; charset=UTF-8");

				String jsonString = gson.toJson(datas);
				out.write(jsonString);
			}else{
				out.print("");
				return;
			}
		}catch(Exception e){
			LOGGER.error("session expired during get questions for student", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("Some internal error occured");
			return;
		}finally{
			if(null != out){
				out.close();
			}
		}
	}

}
