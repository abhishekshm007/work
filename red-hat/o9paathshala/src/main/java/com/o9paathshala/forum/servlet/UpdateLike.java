package com.o9paathshala.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.o9paathshala.dao.ConfiguredQuery;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;

/**
 * Servlet implementation class UpdateLike
 */
@WebServlet("/UpdateLike")
public class UpdateLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final  Logger LOGGER = LoggerFactory.getLogger(UpdateLike.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateLike() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
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
				return;
			}
			DAO dao = DAOFactory.getDAOObject();
			PreparedStatementDTO psDTO = null;
			List<PreparedStatementDTO> psList = new ArrayList<PreparedStatementDTO>();
			List<ConfiguredQuery> configuredQueries = new ArrayList<ConfiguredQuery>();
			ConfiguredQuery configuredQuery = null;
			String sql = "";
			Boolean liked = Boolean.parseBoolean(request.getParameter("liked").trim());
			Integer questionId = Integer.parseInt(request.getParameter("questionId").trim());
			String context = request.getParameter("context");

			if(("answer").equals(context)){
				if(liked){
					sql = SQLConstants.UPDATE_ANSWER_LIKE;
				}else{
					sql = SQLConstants.UPDATE_ANSWER_UNLIKE;
				}
			}else if(("question").equals(context)){
				if(liked){
					sql = SQLConstants.UPDATE_QUESTION_LIKE;
				}else{
					sql = SQLConstants.UPDATE_QUESTION_UNLIKE;
				}
			}else{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Bad request occured!!!");
				return;
			}
			if(sql != null) {
				sql = sql.replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString());
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(1);
				psDTO.setValue(questionId);
				psList.add(psDTO);
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(2);
				psDTO.setValue(user.getId());
				psList.add(psDTO);
				configuredQuery = new ConfiguredQuery();
				configuredQuery.setQuery(sql);
				configuredQuery.setPsList(psList);
				configuredQueries.add(configuredQuery);
			}				
			dao.cud(configuredQueries);
			response.setStatus(HttpServletResponse.SC_OK);
			return;

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
