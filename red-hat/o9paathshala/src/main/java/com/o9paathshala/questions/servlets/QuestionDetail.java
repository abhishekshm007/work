package com.o9paathshala.questions.servlets;

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
import com.o9paathshala.questions.dto.QuestionDTO;

/**
 * Servlet implementation class QuestionDetail
 */
@WebServlet("/QuestionDetail")
public class QuestionDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionDetail() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionDetail.class);
	
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
		QuestionDTO question=new QuestionDTO();
		Gson gson = new Gson();
		String jsonString=null;
		try{
			psDTO.setDataType(DBConstants.INTEGER);
			psDTO.setPosition(1);
			psDTO.setValue(Integer.parseInt(request.getParameter("questionid")));
			psList.add(psDTO);
			resultList=dao.getAll(SQLConstants.GET_QUESTION_ON_ID.replace("instituteid",((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList){
				question.setId(Integer.parseInt(resultList.get(0).get("question_id").toString()));
				question.setContent(new String((byte[])resultList.get(0).get("question_content")));
				List<String> options=new ArrayList<String>();
				for(int i=1;i<=6;i++){
					if(null!=resultList.get(0).get("option"+i)&&new String((byte [])resultList.get(0).get("option"+i)).length()>0){
						options.add(new String((byte[])resultList.get(0).get("option"+i)));
					}
				}
				question.setOptions(options);
				List<Integer> correctOptions=new ArrayList<Integer>();
				String[] answers=new String((byte[])resultList.get(0).get("correct_answer")).split(",");
				for(String c:answers){
					if(c.length()>0){
					correctOptions.add(Integer.parseInt(c));
					}
				}
				question.setCorrectOptions(correctOptions);
			}
			jsonString = gson.toJson(question);
			
		} catch (ClassNotFoundException | SQLException  e) {			
			LOGGER.error(e.getMessage(), e);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}
}

