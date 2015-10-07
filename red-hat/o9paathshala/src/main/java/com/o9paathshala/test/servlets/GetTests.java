package com.o9paathshala.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.o9paathshala.test.dto.TestDTO;

/**
 * Servlet implementation class GetTests
 */
@WebServlet("/GetTests")
public class GetTests extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTests() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(GetTests.class);

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

		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		List<Map<String, Object>> resultList = null;
		List<TestDTO> data=new ArrayList<TestDTO>();
		List<PreparedStatementDTO> psList=null;
		PreparedStatementDTO psDTO=null;
		StringBuilder sql1=null;
		Gson gson = new Gson();
		String jsonString=null;
		try {
			int type=((SessionDTO)session.getAttribute("user")).getType();
			int k=0;
			switch(type){
			case 3:sql1 = new StringBuilder(SQLConstants.STUDENT_TEST_LIST);
			psList=new ArrayList<PreparedStatementDTO>();
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(++k);
			psDTO.setValue(((SessionDTO)session.getAttribute("user")).getBatchid());
			psList.add(psDTO);
			break;
			default:
				sql1 = new StringBuilder(SQLConstants.TEST_LIST);
				break;
			}

			resultList=dao.getAll(sql1.toString().replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList){
				for(int i=0;i<resultList.size();i++){
					TestDTO dto=new TestDTO();
					dto.setId(Integer.parseInt(resultList.get(i).get("test_id").toString()));
					dto.setTestName(resultList.get(i).get("test_name").toString());
					data.add(dto);
				}
			}
			jsonString = gson.toJson(data);

		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);

		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}


