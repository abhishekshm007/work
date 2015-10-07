package com.o9paathshala.buy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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
import com.o9paathshala.questionset.servlets.GetAllSet;

/**
 * Servlet implementation class CalculateAmount
 */
@WebServlet("/CalculateAmount")
public class CalculateAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalculateAmount() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculateAmount.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(null==session.getAttribute("user")){
			response.sendRedirect("index.jsp");
		}
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		String jsonString=null;
		try{
		String s=request.getParameter("sets");
		if(s.length()>0){
		String [] sets=s.split(",");
		Integer pack=Integer.parseInt(request.getParameter("package"));
		int number=sets.length;
		float amount=0.0f;
		float totalAmount=0.0f;
		ResourceBundle rb = ResourceBundle.getBundle("RateList");
		float rate=Float.parseFloat(rb.getString(pack.toString()));
		float tax=Float.parseFloat(rb.getString("tax"));
	    amount=number*rate;
	    tax=(amount*tax)/100;
	    totalAmount=amount+tax;
		BuyDTO dto=new BuyDTO();
		dto.setPack(pack);
		dto.setSets(sets);
		dto.setAmount(amount);
		dto.setTax(tax);
		dto.setTotalAmount(totalAmount);
		if(!(totalAmount==0)){
		session.setAttribute("pack", dto);
		}
		jsonString = gson.toJson(dto);
		}else{
			jsonString = gson.toJson(0);
		}
		out.println(jsonString);
		out.close();
		}catch(MissingResourceException e){
			LOGGER.error(e.getMessage(),e);
			jsonString = gson.toJson("error");
			out.println(jsonString);
			out.close();
		}
		
	}

}
