package com.o9paathshala.student.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.o9paathshala.constants.ApplicationContants;
import com.o9paathshala.dao.ConfiguredQuery;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.security.EncoderDecoder;
import com.o9paathshala.security.Encryptor;
import com.o9paathshala.student.dto.StudentRegistrationDTO;
import com.o9paathshala.util.Validation;

/**
 * Servlet implementation class StudentRegistration
 */
@WebServlet("/StudentRegistration")
public class StudentRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRegistration.class);
    PrintWriter out;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentRegistration() {
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
			out = response.getWriter();
			StudentRegistrationDTO data = new StudentRegistrationDTO();
			data.setName(request.getParameter("name"));
			data.setEmail(request.getParameter("email"));
			data.setPassword(request.getParameter("password"));
			data.setConfirmPassword(request.getParameter("confirmPassword"));
			if(!Validation.validateEmail(data.getEmail())){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Your email is invalid, please try other email...");
				return;
			}
			if(!data.getPassword().equals(data.getConfirmPassword())){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Your password didn't match, please try again...");
				return;
			}
			data.setPassword(Encryptor.encryptSHA512(data.getPassword()));
			
			int count = 0;
			
			boolean checkEmail = false;
			DAO dao = DAOFactory.getDAOObject();
			List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
			PreparedStatementDTO ps = null;
			List<PreparedStatementDTO> psList = new ArrayList<PreparedStatementDTO>();
			String sql = null;
			ConfiguredQuery cq = null;
			List<ConfiguredQuery> cqList = new ArrayList<ConfiguredQuery>();
			
			
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.STRING);
			ps.setPosition(1);
			ps.setValue(data.getEmail());
			psList.add(ps);
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.INTEGER);
			ps.setPosition(2);
			ps.setValue(ApplicationContants.O9_INSTITUTE_ID);
			psList.add(ps);
			
			sql = SQLConstants.CHECK_STUDENT_REGISTRATION;
			
			rs = dao.getAll(sql, psList);
			if(null == rs ){
					checkEmail = true;
			}
			
			if(checkEmail){
				psList.clear();
				
				ps = new PreparedStatementDTO();
				ps.setDataType(DBConstants.STRING);
				ps.setPosition(1);
				ps.setValue(data.getName());
				psList.add(ps);
				ps = new PreparedStatementDTO();
				ps.setDataType(DBConstants.STRING);
				ps.setPosition(2);
				ps.setValue(data.getEmail());
				psList.add(ps);
				ps = new PreparedStatementDTO();
				ps.setDataType(DBConstants.STRING);
				ps.setPosition(3);
				ps.setValue(data.getPassword());
				psList.add(ps);
				sql = SQLConstants.REGISTER_STUDENT;

				cq = new ConfiguredQuery();
				cq.setPsList(psList);
				cq.setQuery(sql);
				
				cqList.add(cq);
				count = dao.cud(cqList);
				
				if(count == 0){
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					out.print("A problem occured...please try again...");
					return;
				}
				HttpSession session=request.getSession(true);
				session.setMaxInactiveInterval(ApplicationContants.SESSION_MAX_INACTIVE_INTERVAL);
				session.setAttribute("studentRegistration", "studentRegistration");
				response.setStatus(HttpServletResponse.SC_OK);
				out.print(EncoderDecoder.encode(data.getEmail()));
				return;
				
				
			}else{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Your email already exist, please try other email...");
				return;
			}
		}catch(Exception e){
			LOGGER.error("Error Occured", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("Some error occured, please try again !!!");
			return;
		}finally{
			out.close();
		}
		
		
	}

}
