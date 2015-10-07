package com.o9paathshala.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.o9paathshala.constants.ApplicationContants;

/**
 * Servlet implementation to get the Security Key
 */
@WebServlet("/Security")
public class Security extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Security() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		if(null==session.getAttribute("user")){
		out.print(false);
		out.close();
		}
		Gson gson=new Gson();
	    String data=ApplicationContants.SECURITYKEY;
		String json=gson.toJson(data);
		out.println(json);
		out.close();
	}

}
