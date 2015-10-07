package com.o9paathshala.result.servlets;

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
import com.o9paathshala.result.dto.ResultDTO;


@WebServlet("/MyResult")
public class MyResult extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public MyResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(MyResult.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		List<Map<String, Object>> resultList = null;
		List<ResultDTO> data=new ArrayList<ResultDTO>();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=new PreparedStatementDTO();
		Gson gson = new Gson();
		String jsonString=null;
		try {
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		if(3==((SessionDTO)session.getAttribute("user")).getType()){
			psDTO.setValue(((SessionDTO)session.getAttribute("user")).getId());
		}else{
			psDTO.setValue(Integer.parseInt(request.getParameter("id")));
		}
		psList.add(psDTO);
		
			resultList=dao.getAll(SQLConstants.GET_MY_RESULT.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList){
				for(int i=0;i<resultList.size();i++){
					ResultDTO dto=new ResultDTO();
					dto.setTest(resultList.get(i).get("test_name").toString());
					dto.setScore(Float.parseFloat(resultList.get(i).get("score").toString()));
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
