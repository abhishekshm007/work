package com.o9paathshala.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.o9paathshala.dto.PaginationDTO;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.forum.dto.TagDTO;

/**
 * Servlet implementation class GetAllTagsForInstitute
 */
@WebServlet("/GetAllTags")
public class GetAllTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final  Logger LOGGER = LoggerFactory.getLogger(GetAllTags.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllTags() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	PrintWriter out;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			out = response.getWriter();
			HttpSession session = request.getSession(false);
			SessionDTO user = (SessionDTO) session.getAttribute("user");
			if(user == null){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				LOGGER.info("a bad request occuured");
				out.print("Bad Request occured!!!");
				return;
			}

			int startRow = Integer.parseInt(request.getParameter("iDisplayStart"));
			int noofRecords=Integer.parseInt(request.getParameter("iDisplayLength"));
			int echo = Integer.parseInt(request.getParameter("sEcho"));
			int totalRecords = 0;
			String search=request.getParameter("sSearch");

			DAO dao = DAOFactory.getDAOObject();
			List<PreparedStatementDTO> psList  = new ArrayList<PreparedStatementDTO>();
			PreparedStatementDTO psDTO = null;
			TagDTO tag = null;
			List<TagDTO> tags = new ArrayList<TagDTO>();
			List<Map<String, Object>> outputList = new ArrayList<Map<String,Object>>();
			response.setContentType("application/json");


			outputList=dao.getAll(SQLConstants.COUNT_ALL_TAGS.replace("INSTITUTE_ID", user.getCurrentInstituteId().toString()),null);
			if(null!=outputList){
				totalRecords= Integer.parseInt(outputList.get(0).get("count").toString());
			}
			StringBuilder sql = null;
			if(totalRecords > 0){
				sql = new StringBuilder(SQLConstants.GET_ALL_TAGS);
				if(null != search){
					sql.append(" like '"+search+"%' order by tag_id asc limit ?,?");
				}
				psDTO =new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(1);
				psDTO.setValue(startRow);
				psList.add(psDTO);
				psDTO =new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(2);
				psDTO.setValue(noofRecords);
				psList.add(psDTO);

				outputList = dao.getAll(sql.toString().replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString()),psList);
				if(null != outputList){
					for(Map<String, Object> output : outputList){
						tag=new TagDTO();
						tag.setTagId((Integer) output.get("tag_id"));
						tag.setTagName((String) output.get("tag_name"));
						tag.setTagDesc((String) output.get("tag_desc"));
						tag.setTagReputation((Integer) output.get("tag_reputation"));
						tags.add(tag);
					}
				}
			}
			PaginationDTO paginationDTO = new PaginationDTO();
			paginationDTO.setsEcho(echo);
			paginationDTO.setAaData(tags);
			paginationDTO.setiTotalDisplayRecords(totalRecords);
			paginationDTO.setiTotalRecords(totalRecords);
			Gson gson = new Gson();
			String jsonString = gson.toJson(paginationDTO);
			out.write(jsonString);
			

		}catch(Exception e){
			LOGGER.error("error occured during getting tags", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("There is some problem occured !!!");
			return;
		}finally{
			if(null != out){
				out.close();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
		return;
	}

}
