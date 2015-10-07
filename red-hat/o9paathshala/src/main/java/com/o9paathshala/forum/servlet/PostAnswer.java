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
 * Servlet implementation class PostAnswer
 */
@WebServlet("/PostAnswer")
public class PostAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final  Logger LOGGER = LoggerFactory.getLogger(PostAnswer.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostAnswer() {
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
			 out=response.getWriter();
			HttpSession session = request.getSession(false);
			SessionDTO user = (SessionDTO)session.getAttribute("user");
			if(user == null){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("There is some problem");
				return;
			}
			String question = request.getParameter("question").trim();
			String answer = request.getParameter("answer").trim();


			DAO dao = DAOFactory.getDAOObject();
			List<ConfiguredQuery> configuredQueries = new ArrayList<ConfiguredQuery>();
			List<PreparedStatementDTO> psList  = null;
			PreparedStatementDTO psDTO = null;
			ConfiguredQuery configuredQuery = null;
			response.setHeader("Content-Type", "text/plain");
			configuredQuery = new ConfiguredQuery();
			configuredQuery.setQuery(SQLConstants.POST_ANSWER.replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString()));
			psList = new ArrayList<PreparedStatementDTO>();
			psDTO = new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setValue(question);
			psDTO.setPosition(1);
			psList.add(psDTO);
			psDTO = new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setValue(user.getId());
			psDTO.setPosition(2);
			psList.add(psDTO);
			psDTO = new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setValue(answer);
			psDTO.setPosition(3);
			psList.add(psDTO);

			configuredQuery.setPsList(psList);
			configuredQueries.add(configuredQuery);


			int rows = dao.cud(configuredQueries);
			if(rows >0){
				response.setStatus(HttpServletResponse.SC_OK);
				out.print("Answer has been posted successfully");
				return;
			}
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("Your Answer does not our requirement");
			return;
		}catch(Exception e){
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			LOGGER.error("error occured ", e);
			out.print("There is some problem, please try again !!!");
			return;
		}finally{
			if(null != out){
				out.close();
			}
		}

	}

}
