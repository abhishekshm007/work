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
import com.o9paathshala.constants.ErrorMessageConstants;
import com.o9paathshala.dao.ConfiguredQuery;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;

@WebServlet("/AdvanceTest")
public class AdvanceTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdvanceTest() {
        super();
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvanceTest.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		DAO dao=DAOFactory.getDAOObject();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		PreparedStatementDTO psDTO=null;
		List<ConfiguredQuery> cqList=new ArrayList<ConfiguredQuery>();
		ConfiguredQuery cq=new ConfiguredQuery();
		Gson gson=new Gson();
		String message=null;
		List<Map<String, Object>> resultList = null;
		String jsonString=null;
		try {
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(1);
			psDTO.setValue(request.getParameter("testname"));
			psList.add(psDTO);
			resultList=dao.getAll(SQLConstants.CHECK_TEST_NAME.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList&&1==Integer.parseInt(resultList.get(0).get("count").toString())){
				jsonString = gson.toJson(ErrorMessageConstants.ALREADY_EXIST_NAME_ERROR);
				return;
			}
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(2);
			psDTO.setValue(Integer.parseInt(request.getParameter("duration")));
			psList.add(psDTO);
			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.TIMESTAMP);
			psDTO.setPosition(3);
			psDTO.setValue(java.sql.Timestamp.valueOf(request.getParameter("startdate").replace("T", " ")+":00"));
			psList.add(psDTO);

			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.TIMESTAMP);
			psDTO.setPosition(4);
			if(null!=request.getParameter("enddate")&&request.getParameter("enddate").length()>0){
				psDTO.setValue(java.sql.Timestamp.valueOf(request.getParameter("enddate").replace("T", " ")+":00"));
			}else{
				psDTO.setValue(null);
			}
			psList.add(psDTO);

			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.FLOAT);
			psDTO.setPosition(5);
			psDTO.setValue(Float.parseFloat(request.getParameter("positivemark")));
			psList.add(psDTO);

			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.FLOAT);
			psDTO.setPosition(6);
			psDTO.setValue(Float.parseFloat(request.getParameter("negativemark")));
			psList.add(psDTO);

			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(7);
			psDTO.setValue(((SessionDTO)session.getAttribute("user")).getId());
			psList.add(psDTO);

			psDTO=new PreparedStatementDTO();
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(8);
			psDTO.setValue(((SessionDTO)session.getAttribute("user")).getName());
			psList.add(psDTO);
			cq.setQuery(SQLConstants.CREATE_ADVANCE_TEST.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
			cq.setPsList(psList);
			cqList.add(cq);


			String[] batches=request.getParameterValues("batch");
			for(String batch:batches){
				psList=new ArrayList<PreparedStatementDTO>();
				psDTO=new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(1);
				psDTO.setValue(Integer.parseInt(batch));
				psList.add(psDTO);
				cq=new ConfiguredQuery();
				cq.setPsList(psList);
				cq.setQuery(SQLConstants.TEST_BATCH.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
				cqList.add(cq);
			}

			int result=0;
			result=dao.cud(cqList);
			if(result>0){
				message=ErrorMessageConstants.SUCCESS;
			}else{
				message=ErrorMessageConstants.FAILED;
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
