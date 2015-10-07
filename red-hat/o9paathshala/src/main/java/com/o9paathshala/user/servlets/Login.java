package com.o9paathshala.user.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.o9paathshala.constants.ApplicationContants;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.security.EncoderDecoder;
import com.o9paathshala.util.UserType;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);

	public Login() {
		super();

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionDTO sessionDTO=null;
		try {
			List<Map<String, Object>> outputList = new ArrayList<Map<String, Object>>();
			boolean defaultInstituteFound = false;
			List<SessionDTO> sessionDTOList = new ArrayList<SessionDTO>();
			String email=EncoderDecoder.decode(request.getParameter("req").trim());
			boolean remember=("true").equalsIgnoreCase(request.getParameter("remember"));
			List<Map<String, Object>> batches=null;
			DAO dao=DAOFactory.getDAOObject();
			List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
			PreparedStatementDTO psDTO=new PreparedStatementDTO();

			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(1);
			psDTO.setValue(email);
			psList.add(psDTO);


			outputList = dao.getAll(SQLConstants.GET_USER, psList);
			if(null == outputList || outputList.isEmpty()){
				LOGGER.error("Login did not respond after checkLogin successfully worked");
				response.sendRedirect("error.jsp");
				return;
			}else{
				for(Map<String, Object> output : outputList){
					sessionDTO = new SessionDTO();
					sessionDTO.setId((Integer) output.get("id"));
					sessionDTO.setName((String) output.get("name"));
					sessionDTO.setInstituteName((String) output.get("institute_name"));
					sessionDTO.setCurrentInstituteId((Integer) output.get("institute_id"));
					sessionDTO.setEmail((String) output.get("email"));
					sessionDTO.setDefaultInstituteId((int)output.get("default_institute_id") == 0 ? false : true);
					sessionDTO.setType(UserType.userType(sessionDTO.getId()));
					if(ApplicationContants.STUDENT==sessionDTO.getType()){
						psList.clear();
						psDTO = new PreparedStatementDTO();
						psDTO.setDataType(DBConstants.INTEGER);
						psDTO.setPosition(1);
						psDTO.setValue(sessionDTO.getId());
						psList.add(psDTO);
						batches=dao.getAll(SQLConstants.GET_STUDENT_BATCH.replace("instituteid",sessionDTO.getCurrentInstituteId().toString()), psList);
						if(null!=batches){
							sessionDTO.setBatchid((Integer)batches.get(0).get("student_batch_id"));
						}
					}
					sessionDTOList.add(sessionDTO);
				}
			}
			HttpSession session=request.getSession(true);
			session.setMaxInactiveInterval(ApplicationContants.SESSION_MAX_INACTIVE_INTERVAL);
			if(sessionDTOList.size() == 1){
				sessionDTO = sessionDTOList.get(0);
			}else{
				for(SessionDTO data : sessionDTOList){
					if(data.getDefaultInstituteId()){
						sessionDTO = new SessionDTO();
						sessionDTO = data;
						defaultInstituteFound = true;
						break;
					}
				}
				if(!defaultInstituteFound){
					session.setAttribute("chooseInstituteData", sessionDTOList);
					request.getRequestDispatcher("/chooseInstitute.jsp").forward(request, response);
					return;
				}

			}
			session.setAttribute("user", sessionDTO);
			if(remember){
				Cookie userCookie=new Cookie(ApplicationContants.COOKIE_NAME, EncoderDecoder.encode(sessionDTO.getEmail()));
				userCookie.setMaxAge(ApplicationContants.COOKIE_MAX_AGE);
				response.addCookie(userCookie);
			}
			String user=null;
			switch(sessionDTO.getType()){
			case 1:user="institute";
			break;
			case 2:user="faculty";
			break;
			case 3:user="student";
			break;
			default:break;
			}
			response.sendRedirect(""+user.toLowerCase()+"/dashboard");
			return;
		}catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(),e);
			response.sendRedirect("error.jsp");
			return;
		}

	}

}
