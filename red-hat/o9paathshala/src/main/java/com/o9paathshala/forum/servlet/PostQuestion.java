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
 * Servlet implementation class PostQuestion
 */
@WebServlet("/PostQuestion")
public class PostQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final  Logger LOGGER = LoggerFactory.getLogger(PostQuestion.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	PrintWriter out;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String title = request.getParameter("title").trim();
			String content = request.getParameter("content").trim();
			String tags[] = request.getParameter("tags").trim().split(",");

			out=response.getWriter();

			HttpSession session = request.getSession(false);

			SessionDTO user = (SessionDTO)session.getAttribute("user");
			if(user == null){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("There is some problem");
				return;
			}


			DAO dao = DAOFactory.getDAOObject();
			List<ConfiguredQuery> configuredQueries = new ArrayList<ConfiguredQuery>();
			List<PreparedStatementDTO> psList  = null;
			PreparedStatementDTO psDTO = null;
			ConfiguredQuery configuredQuery = null;
			response.setHeader("Content-Type", "text/plain");


			configuredQuery = new ConfiguredQuery();
			configuredQuery.setQuery(SQLConstants.POST_QUESTION.replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString()));
			psList = new ArrayList<PreparedStatementDTO>();
			psDTO = new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setValue(title);
			psDTO.setPosition(1);
			psList.add(psDTO);
			psDTO = new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setValue(content);
			psDTO.setPosition(2);
			psList.add(psDTO);
			psDTO = new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setValue(user.getId());
			psDTO.setPosition(3);
			psList.add(psDTO);

			configuredQuery.setPsList(psList);
			configuredQueries.add(configuredQuery);

			for(int i = 1;i< tags.length; i++){
				configuredQuery = new ConfiguredQuery();
				configuredQuery.setQuery(SQLConstants.QUESTION_TAG_MAP.replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString()));
				psList = new ArrayList<PreparedStatementDTO>();
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setValue(Integer.parseInt(tags[i]));
				psDTO.setPosition(1);
				psList.add(psDTO);
				configuredQuery.setPsList(psList);
				configuredQueries.add(configuredQuery);
			}
			for(int i = 1;i< tags.length; i++){
				configuredQuery = new ConfiguredQuery();
				configuredQuery.setQuery(SQLConstants.UPDATE_TAG_REPUTATION.replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString()));
				psList = new ArrayList<PreparedStatementDTO>();
				psDTO = new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setValue(Integer.parseInt(tags[i]));
				psDTO.setPosition(1);
				psList.add(psDTO);
				configuredQuery.setPsList(psList);
				configuredQueries.add(configuredQuery);
			}

			int rows = dao.cud(configuredQueries);
			if(rows >0){
				response.setStatus(HttpServletResponse.SC_OK);
				out.print("Question has been posted successfully");
				return;
			}
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("Your question does not meet our requirements");
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
