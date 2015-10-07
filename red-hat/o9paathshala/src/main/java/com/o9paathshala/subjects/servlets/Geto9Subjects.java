package com.o9paathshala.subjects.servlets;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.subjects.dto.SubjectDTO;

/**
 * Servlet implementation class Geto9Subjects
 */
@WebServlet("/Geto9Subjects")
public class Geto9Subjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Geto9Subjects() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(Geto9Subjects.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		DAO dao = DAOFactory.getDAOObject();
		List<Map<String, Object>> resultList = null;
		List<SubjectDTO> data=new ArrayList<SubjectDTO>();
		String jsonString=null;
		try {
			resultList=dao.getAll(SQLConstants.O9_SUBJECT_LIST,null);
			if(null!=resultList){
				for(int i=0;i<resultList.size();i++){
					SubjectDTO dto=new SubjectDTO();
					dto.setId(Integer.parseInt(resultList.get(i).get("subject_id").toString()));
					dto.setName(resultList.get(i).get("subject_name").toString());
					data.add(dto);
				}
			}
			Gson gson = new Gson();
			 jsonString = gson.toJson(data);
			
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}


