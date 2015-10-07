package com.o9paathshala.student.servlets;

import java.io.IOException;
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

import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.security.EncoderDecoder;
import com.o9paathshala.util.UserType;

/**
 * Servlet implementation class StudentRegistrationSession
 */
@WebServlet("/StudentRegistrationSession")
public class StudentRegistrationSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentRegistrationSession.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentRegistrationSession() {
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
		try{
			
			SessionDTO sessionDTO = null;
			HttpSession session=request.getSession(false);
			String target = (String)session.getAttribute("studentRegistration");
			if(!("studentRegistration").equals(target)){
				LOGGER.error("tried to breach site");
				response.sendRedirect("error.jsp");
				return;
			}
			session.removeAttribute("studentRegistration");
			String email = EncoderDecoder.decode(request.getParameter("req").trim());

			boolean defaultInstituteFound = false;
			
			List<SessionDTO> sessionDTOList = new ArrayList<SessionDTO>();
			List<Map<String, Object>> batches=null;
			DAO dao = DAOFactory.getDAOObject();
			List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
			PreparedStatementDTO ps = null;
			List<PreparedStatementDTO> psList = new ArrayList<PreparedStatementDTO>();
			String sql = null;

			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.STRING);
			ps.setPosition(1);
			ps.setValue(email);
			psList.add(ps);
			sql = SQLConstants.GET_STUDENT_SESSION_DATA;

			rs = dao.getAll(sql, psList);

			if(null == rs || rs.isEmpty()){
				LOGGER.error("Login did not respond after StudentRgistration successfully worked");
				response.sendRedirect("error.jsp");
				return;
			}else{
				for(Map<String, Object> output : rs){
					sessionDTO = new SessionDTO();
					sessionDTO.setId((Integer) output.get("id"));
					sessionDTO.setName((String) output.get("name"));
					sessionDTO.setInstituteName((String) output.get("institute_name"));
					sessionDTO.setCurrentInstituteId((Integer) output.get("institute_id"));
					sessionDTO.setEmail((String) output.get("email"));
					sessionDTO.setDefaultInstituteId(Integer.parseInt(output.get("default_institute_id").toString()) == 0 ? false : true);
					sessionDTO.setType(UserType.userType(sessionDTO.getId()));
					if(3==sessionDTO.getType()){
						psList.clear();
						ps = new PreparedStatementDTO();
						ps.setDataType(DBConstants.INTEGER);
						ps.setPosition(1);
						ps.setValue(sessionDTO.getId());
						psList.add(ps);
						batches=dao.getAll(SQLConstants.GET_STUDENT_BATCH.replace("instituteid",sessionDTO.getCurrentInstituteId().toString()), psList);
						if(null!=batches){
							sessionDTO.setBatchid((Integer)batches.get(0).get("student_batch_id"));
						}
					}
					sessionDTOList.add(sessionDTO);
				}
				
				
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
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			response.sendRedirect("error.jsp");
			return;
		}

	}

}
