package com.o9paathshala.batches.servlets;

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
import com.o9paathshala.dao.ConfiguredQuery;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;

/**
 * Servlet implementation class AddFaculty
 */
@WebServlet("/AllotBatches")
public class AllotBatches extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllotBatches() {
        super();
        // TODO Auto-generated constructor stub
    }

	private static final Logger LOGGER = LoggerFactory.getLogger(AllotBatches.class);
	    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		DAO dao=DAOFactory.getDAOObject();
		List<PreparedStatementDTO> psList=null;
		List<ConfiguredQuery> cqList=new ArrayList<ConfiguredQuery>();
		ConfiguredQuery cq=null;
		PreparedStatementDTO psDTO=null;
		Gson gson=new Gson();
		Integer facultyid=Integer.parseInt(request.getParameter("facultyid"));
		String[] batchid=request.getParameterValues("batch");
		String jsonString=null;
		Boolean message=false;
		try {
			for (String id: batchid){
		        cq=new ConfiguredQuery();
				psList=new ArrayList<PreparedStatementDTO>();
				psDTO=new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(2);
				psDTO.setValue(Integer.parseInt(id));
				psList.add(psDTO);
				psDTO=new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(1);
				psDTO.setValue(facultyid);
				psList.add(psDTO);
		        cq.setPsList(psList);
		        cq.setQuery(SQLConstants.ALLOT_BATCH_TO_FACULTY.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()));
		        cqList.add(cq);
		      }
			int result=0;
			result=dao.cud(cqList);
			if(result>0){
				message=true;
			}
			 jsonString = gson.toJson(message);
			
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
			 jsonString = gson.toJson(message);
		}finally{
			out.println(jsonString);
			out.close();
		}
	

	}

}
