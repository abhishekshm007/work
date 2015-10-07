package com.o9paathshala.buy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.o9paathshala.buy.dto.BuyDTO;
import com.o9paathshala.dao.ConfiguredQuery;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;

/**
 * Servlet implementation class AddSets
 */
@WebServlet("/AddSets")
public class AddSets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSets() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(AddSets.class);

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(null==session.getAttribute("user")||null==session.getAttribute("pack")){
			response.sendRedirect("index.jsp");
		}
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		List<ConfiguredQuery> cqList=new ArrayList<ConfiguredQuery>();
		ConfiguredQuery cq=null;
		List<PreparedStatementDTO> psList=null;
		PreparedStatementDTO psDTO=null;
		SessionDTO sessiondto=(SessionDTO)session.getAttribute("user");
		BuyDTO buydto=(BuyDTO)session.getAttribute("pack");
		Integer pack=buydto.getPack();
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.MONTH, pack);
		Timestamp time=new Timestamp(cal.getTimeInMillis());
		String[] s=buydto.getSets();
		for(String d:s){
		cq=new ConfiguredQuery();
		psList=new ArrayList<PreparedStatementDTO>();
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(Integer.parseInt(d));
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.TIMESTAMP);
		psDTO.setPosition(2);
		psDTO.setValue(time);
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(3);
		psDTO.setValue(sessiondto.getEmail());
		psList.add(psDTO);
		cq.setPsList(psList);
		cq.setQuery(SQLConstants.ADD_SET.replace("instituteid", sessiondto.getCurrentInstituteId().toString()));
		cqList.add(cq);
		}
		cq=new ConfiguredQuery();
		psList=new ArrayList<PreparedStatementDTO>();
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(1);
		psDTO.setValue(pack);
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(2);
		psDTO.setValue(gson.toJson(s));
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.INTEGER);
		psDTO.setPosition(3);
		psDTO.setValue(sessiondto.getCurrentInstituteId());
		psList.add(psDTO);
		psDTO=new PreparedStatementDTO();
		psDTO.setDataType(DBConstants.STRING);
		psDTO.setPosition(4);
		psDTO.setValue(sessiondto.getEmail());
		psList.add(psDTO);
		cq.setPsList(psList);
		cq.setQuery(SQLConstants.ADD_SALE);
		cqList.add(cq);
	}

}
