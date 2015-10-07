package com.o9paathshala.batches.servlets;

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
import com.o9paathshala.batches.dto.BatchDTO;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;

@WebServlet("/GetBatches")
public class GetBatches extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetBatches() {
		super();
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(GetBatches.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		List<Map<String, Object>> resultList = null;
		List<BatchDTO> data=new ArrayList<BatchDTO>();
		Gson gson = new Gson();
		String jsonString=null;
		try {
			resultList=dao.getAll(SQLConstants.GET_BATCHES.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),null);
			if(null!=resultList){
				for(int i=0;i<resultList.size();i++){
					BatchDTO dto=new BatchDTO();
					dto.setId(Integer.parseInt(resultList.get(i).get("batch_id").toString()));
					dto.setName(resultList.get(i).get("batch_name").toString());
					data.add(dto);
				}
			}
			jsonString= gson.toJson(data);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}


