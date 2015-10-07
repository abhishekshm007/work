package com.o9paathshala.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.o9paathshala.questions.dto.QuestionDTO;
import com.o9paathshala.test.dto.SectionDTO;
import com.o9paathshala.test.dto.TestDTO;

@WebServlet("/StartTest")
public class StartTest extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public StartTest() {
		super();
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(StartTest.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Gson gson = new Gson();
		String jsonString=null;
		HttpSession session=request.getSession();
		DAO dao = DAOFactory.getDAOObject();
		List<Map<String, Object>> resultList = null;
		PreparedStatementDTO psDTO=new PreparedStatementDTO();
		List<PreparedStatementDTO> psList=new ArrayList<PreparedStatementDTO>();
		Integer id=Integer.parseInt(request.getParameter("test"));
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(id);
		psList.add(psDTO);
		TestDTO data=new TestDTO();
		List<String> section=new ArrayList<String>();
		List<SectionDTO> sList=new ArrayList<SectionDTO>();
		List<QuestionDTO> qList=null;
		QuestionDTO qDTO=null;
		SectionDTO sDTO=null;
		String var=null;
		Integer testid=null;
		List<Integer> userAnswer=new ArrayList<Integer>();
		try {
			resultList=dao.getAll(SQLConstants.GET_TEST.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
			if(null!=resultList){
				testid=Integer.parseInt(resultList.get(0).get("test_id").toString());

//				if(null!=session.getAttribute("test")&&testid==Integer.parseInt(resultList.get(0).get("test_id").toString())){
//					jsonString = gson.toJson("NO");
//					
//					return;
//				}
				data.setDuration(Integer.parseInt(resultList.get(0).get("test_duration").toString())*60);
				data.setId(testid);
				data.setNegativeMark(Float.parseFloat(resultList.get(0).get("test_negative_mark").toString())*-1);
				data.setPositiveMark(Float.parseFloat(resultList.get(0).get("test_positive_mark").toString()));
				data.setTestName(resultList.get(0).get("test_name").toString());
				resultList=dao.getAll(SQLConstants.START_TEST.replace("instituteid", ((SessionDTO)session.getAttribute("user")).getCurrentInstituteId().toString()),psList);
//				if(null==resultList.get(0).get("question_content")){
//					jsonString = gson.toJson("NO");
//					out.println(jsonString);
//					out.close();
//					return;
//				}
				if(null!=resultList){
					qList=new ArrayList<QuestionDTO>();
					section.add(resultList.get(0).get("section_name").toString());
					int i=0;
					for(i=0;i<resultList.size();i++){
						if(null==resultList.get(i).get("question_content")){
							continue;
						}
						var=resultList.get(i).get("section_name").toString();
						if(!section.contains(var)){
							section.add(var);
							sDTO=new SectionDTO();
							Collections.shuffle(qList);
							sDTO.setQuestions(qList);
							sDTO.setSectionID(Integer.parseInt(resultList.get(i-1).get("section_id").toString()));
							sDTO.setSectionName(resultList.get(i-1).get("section_name").toString());
							sList.add(sDTO);
							qList=new ArrayList<QuestionDTO>();
						}
						
						qDTO=new QuestionDTO();
						qDTO.setAttempted(false);
						qDTO.setContent(new String((byte[])resultList.get(i).get("question_content")));
						System.out.println(qDTO.getContent());
						qDTO.setCorrectOptions(parseCorrect(new String((byte[])resultList.get(i).get("correct_answer"))));
						qDTO.setId(Integer.parseInt(resultList.get(i).get("question_id").toString()));
						qDTO.setMarks(0.0f);
						List<String> options=new ArrayList<String>();
						for(int l=1;l<=6;l++){
							boolean r=new String((byte [])resultList.get(i).get("option"+l)).length()>0;
							if((null!=resultList.get(i).get("option"+l))&& r){
								String option=new String((byte[])resultList.get(i).get("option"+l));
								if(!(" ".equalsIgnoreCase(option)||null==option||option.isEmpty())){
									options.add(option);
								}
							}
						}
						qDTO.setOptions(options);
						qDTO.setUserAnswers(userAnswer);
						qList.add(qDTO);

					}
					sDTO=new SectionDTO();
					Collections.shuffle(qList);
					sDTO.setQuestions(qList);
					sDTO.setSectionID(Integer.parseInt(resultList.get(i-1).get("section_id").toString()));
					sDTO.setSectionName(resultList.get(i-1).get("section_name").toString());
					sList.add(sDTO);
				}
				data.setSections(sList);
			}
			session.setAttribute("test", testid);
			jsonString = gson.toJson(data);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}finally{
			out.println(jsonString);
			out.close();
		}
	}

	private List<Integer> parseCorrect(String data){
		Set<Integer> d=new HashSet<Integer>();
		String[] r=data.split(",");
		for(String a:r){
			d.add(Integer.parseInt(a));
		}
		return new ArrayList<Integer>(d);
	}
}

