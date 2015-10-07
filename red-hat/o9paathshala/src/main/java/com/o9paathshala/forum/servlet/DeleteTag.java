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

import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;

/**
 * Servlet implementation class DeleteTag
 */
@WebServlet("/DeleteTag")
public class DeleteTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final  Logger LOGGER = LoggerFactory.getLogger(DeleteTag.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTag() {
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
		out = response.getWriter();
		HttpSession session = request.getSession(false);
		SessionDTO user = (SessionDTO) session.getAttribute("user");
		if(null == user){
			LOGGER.error("session expired during get questions for student");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("Bad request occured !!!");
			return;
		}
		Integer tagId = Integer.parseInt(request.getParameter("id"));
		DAO dao = DAOFactory.getDAOObject();
		PreparedStatementDTO psDTO = null;
		List<PreparedStatementDTO> psList = new ArrayList<PreparedStatementDTO>();
		
		String sql = SQLConstants.DELETE_TAG;
		
		psDTO = new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(tagId);
		psList.add(psDTO);
		dao.cud(sql.replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString()), psList);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print("Tag Deleteed succesfully");
		return;
	}catch(Exception e){
		LOGGER.error("update like error", e);
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		out.print("There is some Problem, please try again !!!");
		return;
	}finally{
		if(null != out){
			out.close();
		}
	}
	}

}
