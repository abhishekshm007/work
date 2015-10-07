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

/**
 * Servlet implementation class GetAnswers
 */
@WebServlet("/GetAnswers")
public class GetAnswers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final  Logger LOGGER = LoggerFactory.getLogger(GetAnswers.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAnswers() {
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
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Bad request occured !!!");
				LOGGER.error("error occured get answers");
				return;
			}
			List<Map<String, Object>> outputList = new ArrayList<Map<String,Object>>();
			DAO dao = DAOFactory.getDAOObject();
			PreparedStatementDTO psDTO = null;
			List<PreparedStatementDTO> psList = new ArrayList<PreparedStatementDTO>();
			List<AnswerDTO> datas = new ArrayList<AnswerDTO>();
			AnswerDTO data = null;

			Integer question = Integer.parseInt(request.getParameter("question").trim());

			String sql = SQLConstants.GET_POST_ANSWER;
			if(sql != null && question != null) {
				sql = sql.replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString());
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(1);
				psDTO.setValue(user.getId());
				psList.add(psDTO);
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(2);
				psDTO.setValue(question);
				psList.add(psDTO);
			}	
			outputList = dao.getAll(sql,psList);
			if(null != outputList){
				for(Map<String, Object> output: outputList){
					data = new AnswerDTO();
					data.setAnswerId((Integer)output.get("answer_id"));
					data.setUserId((Integer)output.get("user_id"));
					data.setAnswer((String)output.get("answer_content"));
					data.setUsername((String)output.get("user_name"));
					data.setReputation((Long)output.get("reputation"));
					data.setDate((Timestamp)output.get("answer_time"));
					data.setLiked((Long)output.get("liked") == 1 ? true : false);
					data.toString();
					datas.add(data);
				}
				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType("application/json; charset=UTF-8");
				Gson gson = new Gson();
				String jsonString = gson.toJson(datas);
				out.print(jsonString);
			}else{
				out.print("");
				return;
			}
		}catch(Exception e){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("Some internal error occured");
			LOGGER.error("error occured get answers", e);
			return;
		}finally{
			if(null != out){
				out.close();
			}
		}
	}

}
