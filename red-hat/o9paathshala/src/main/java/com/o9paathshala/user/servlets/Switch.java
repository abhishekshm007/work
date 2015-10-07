package com.o9paathshala.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
import com.o9paathshala.security.Encryptor;
import com.o9paathshala.util.UserType;

/**
 * Servlet implementation class Switch
 */
@WebServlet("/Switch")
public class Switch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Switch() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProfile.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		PrintWriter out=response.getWriter();
		Gson gson = new Gson();
		String jsonString=null;
		try{
			
		String email=((SessionDTO)session.getAttribute("user")).getEmail();
		String password=Encryptor.encryptSHA512(request.getParameter("password"));
		Integer id=Integer.parseInt(request.getParameter("institute"));
		List<Map<String, Object>> batches=null;
		List<Map<String, Object>> outputList=null;
		DAO dao=DAOFactory.getDAOObject();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=new PreparedStatementDTO();
		SessionDTO sessionDTO=null;
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(1);
		psDTO.setValue(email);
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(2);
		psDTO.setValue(password);
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(3);
		psDTO.setValue(id);
		psList.add(psDTO);
		outputList = dao.getAll(SQLConstants.SWITCH_USER, psList);
		if(null!=outputList&&!outputList.isEmpty()){
			sessionDTO = new SessionDTO();
			sessionDTO.setId((Integer) outputList.get(0).get("id"));
			sessionDTO.setName((String) outputList.get(0).get("name"));
			sessionDTO.setInstituteName((String) outputList.get(0).get("institute_name"));
			sessionDTO.setCurrentInstituteId((Integer) outputList.get(0).get("institute_id"));
			sessionDTO.setEmail((String) outputList.get(0).get("email"));
			sessionDTO.setDefaultInstituteId((int)outputList.get(0).get("default_institute_id") == 0 ? false : true);
			sessionDTO.setType(UserType.userType(sessionDTO.getId()));
			if(3==sessionDTO.getType()){
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
			String user=null;
			session.setAttribute("user", sessionDTO);
			switch(sessionDTO.getType()){
			case 1:user="institute";
			break;
			case 2:user="faculty";
			break;
			case 3:user="student";
			break;
			default:break;
			}
			jsonString = gson.toJson(""+user.toLowerCase()+"/dashboard");
			out.println(jsonString);
			out.close();

		}else{
			jsonString = gson.toJson(false);
			out.println(jsonString);
			out.close();
		}
		}catch(ClassNotFoundException | SQLException | NoSuchAlgorithmException e){
			LOGGER.error(e.getMessage(),e);
		}
	}

}
