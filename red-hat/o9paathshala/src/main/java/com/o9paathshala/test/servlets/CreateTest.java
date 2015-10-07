package com.o9paathshala.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import com.google.gson.Gson;
import com.o9paathshala.constants.ErrorMessageConstants;
import com.o9paathshala.dao.ConfiguredQuery;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;

/**
 * Servlet implementation class CreateTest
 */
@WebServlet("/CreateTest")
public class CreateTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateTest.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String[] myquestion=null;
		String[] purchasedquestion=null;
		if(!(null==request.getParameter("myquestion")||request.getParameter("myquestion").isEmpty())){
		myquestion=request.getParameter("myquestion").split(",");
		}
		if(!(null==request.getParameter("purchasedquestion")||request.getParameter("purchasedquestion").isEmpty())){
		purchasedquestion=request.getParameter("purchasedquestion").split(",");
		}
		
		PrintWriter out=response.getWriter();
		DAO dao=DAOFactory.getDAOObject();
		List<PreparedStatementDTO> psList=null;
		PreparedStatementDTO psDTO=null;
		List<ConfiguredQuery> cqList=new ArrayList<ConfiguredQuery>();
		ConfiguredQuery cq=null;
		String message=null;
		Gson gson=new Gson();
		String jsonString=null;
		try{
		for(int m=0;myquestion!=null&&m<myquestion.length;m++){
			cq=new ConfiguredQuery();
			psList=new ArrayList<PreparedStatementDTO>();
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(1);
			psDTO.setValue(Integer.parseInt(myquestion[m]));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(2);
			psDTO.setValue(Integer.parseInt(myquestion[++m]));
			psList.add(psDTO);
			cq.setPsList(psList);
			cq.setQuery(SQLConstants.SECTION_MY_QUESTION_MAP.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
			cqList.add(cq);
		}
		for(int m=0;purchasedquestion!=null&&m<purchasedquestion.length;m++){
			cq=new ConfiguredQuery();
			psList=new ArrayList<PreparedStatementDTO>();
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(1);
			psDTO.setValue(Integer.parseInt(purchasedquestion[m]));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(2);
			psDTO.setValue(Integer.parseInt(purchasedquestion[++m]));
			psList.add(psDTO);
			cq.setPsList(psList);
			cq.setQuery(SQLConstants.SECTION_PURCHASED_QUESTION_MAP.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
			cqList.add(cq);
		}
		
			int result=0;
			if(!cqList.isEmpty()){
			result=dao.cud(cqList);
			if(result>0){
				message=ErrorMessageConstants.SUCCESS;
			}else{
				message=ErrorMessageConstants.FAILED;
			}
			}else{
				message=ErrorMessageConstants.NO_QUESTION;
			}
			jsonString = gson.toJson(message);
			
		} catch (ClassNotFoundException | SQLException  e) {
			LOGGER.error(e.getMessage(), e);
			jsonString = gson.toJson(ErrorMessageConstants.INTERNAL_SERVER_ERROR);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}

}
