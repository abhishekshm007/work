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
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.forum.dto.TagDTO;

/**
 * Servlet implementation class GetTags
 */
@WebServlet("/GetTags")
public class GetTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER=LoggerFactory.getLogger(GetTags.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTags() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	PrintWriter out;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

			DAO dao = DAOFactory.getDAOObject();
			List<PreparedStatementDTO> psList  = new ArrayList<PreparedStatementDTO>();
			PreparedStatementDTO psDTO = null;
			TagDTO tag = null;
			List<TagDTO> tags = new ArrayList<TagDTO>();
			List<Map<String, Object>> outputList = new ArrayList<Map<String,Object>>();
			response.setContentType("application/json");

			String like = request.getParameter("like").trim();
			Integer limit = Integer.parseInt(request.getParameter("limit").trim());

			String sql = SQLConstants.GET_TAGS;
			if(like != null) {
				sql=sql.replaceAll("LIKE_DATA", " like '%"+like+"%' ");
				sql=sql.replaceAll("ORDER_DATA", " order by `o9_INSTITUTE_ID_tags`.`tag_name` asc limit ?,? ");
				sql=sql.replaceAll("INSTITUTE_ID", user.getCurrentInstituteId().toString());
				psDTO =new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(1);
				psDTO.setValue(0);
				psList.add(psDTO);
				psDTO =new PreparedStatementDTO();
				psDTO.setDataType(DBConstants.INTEGER);
				psDTO.setPosition(2);
				psDTO.setValue(limit);
				psList.add(psDTO);

			}	
			outputList = dao.getAll(sql,psList);
			if(null != outputList){
				for(Map<String, Object> output : outputList){
					tag=new TagDTO();
					tag.setTagId((Integer) output.get("tag_id"));
					tag.setTagName((String) output.get("tag_name"));
					tag.setTagDesc((String) output.get("tag_desc"));
					tag.setTagReputation((Integer) output.get("tag_reputation"));
					tags.add(tag);
				}
				Gson gson = new Gson();
				String jsonString = gson.toJson(tags);
				out.write(jsonString);
			}
		}catch(Exception e){
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			LOGGER.error("error occured ", e);
			out.print("There is some problem, please try again !!!");
			return;
		}finally{
			if(null != out){
				out.close();
			}
		}

	}

}
