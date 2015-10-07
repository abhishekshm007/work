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
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.test.dto.SectionDTO;
import com.o9paathshala.test.dto.TestDTO;

/**
 * Servlet implementation class GetTestDetail
 */
@WebServlet("/GetTestDetail")
public class GetTestDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTestDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(GetTestDetail.class);
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
		PreparedStatementDTO psDTO = new PreparedStatementDTO();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		List<Map<String, Object>> resultList = null;
		TestDTO test=new TestDTO();
		Gson gson = new Gson();
		String jsonString=null;
		try{
			psDTO.setDataType(DBConstants.STRING);
			psDTO.setPosition(1);
			psDTO.setValue(request.getParameter("testname"));
			psList.add(psDTO);
			resultList=dao.getAll(SQLConstants.GET_TEST_DETAIL.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList){
				test.setId(Integer.parseInt(resultList.get(0).get("test_id").toString()));
				test.setTestName(request.getParameter("testname"));
				List<SectionDTO> data=new ArrayList<SectionDTO>();
				for(int i=0;i<resultList.size();i++){
				SectionDTO dto=new SectionDTO();
				dto.setSectionID(Integer.parseInt(resultList.get(i).get("section_id").toString()));
				dto.setSectionName(resultList.get(i).get("section_name").toString());
				data.add(dto);
				}
				test.setSections(data);
			}
			jsonString = gson.toJson(test);
			
		} catch (ClassNotFoundException | SQLException e) {			
			LOGGER.error(e.getMessage(), e);
			request.setAttribute("errorString", ErrorMessageConstants.EXCEPTION_MESSAGE);
			request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}
