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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.LoginDTO;
import com.o9paathshala.security.EncoderDecoder;
import com.o9paathshala.security.Encryptor;
import com.o9paathshala.util.Validation;

/**
 * @category it checks user deatils for login
 */

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckLogin.class);
	PrintWriter out;
	public CheckLogin() {
		super();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			out= response.getWriter();
			String requestData = request.getParameter("user");
			Gson gson = new Gson();
			LoginDTO data = gson.fromJson(requestData, LoginDTO.class);
			int result=0;
			if(!Validation.validateEmail(data.getEmail())){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("You entered wrong email, please try again...");
				return;
			}
			data.setPassword(Encryptor.encryptSHA512(data.getPassword())); 
			
			DAO dao=DAOFactory.getDAOObject();
			List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
			PreparedStatementDTO psDTO=new PreparedStatementDTO();
			List<Map<String, Object>> resultList = null;
			
			
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(1);
			psDTO.setValue(data.getEmail());
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(2);
			psDTO.setValue(data.getPassword());
			psList.add(psDTO);
			resultList=dao.getAll(SQLConstants.CHECK_LOGIN,psList);
			
			if(null!=resultList){
				result=Integer.parseInt(resultList.get(0).get("count").toString());
			}
			if(1==result){
				response.setStatus(HttpServletResponse.SC_OK);
				out.print(EncoderDecoder.encode(data.getEmail()));
				return;

			}else{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("You entered wrong email or password, please try again...");
				return;

			}
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(),e);
			out.print("Some error occured, please try again !!!");
			return;
		}finally{
			out.close();
		}


	}

}
