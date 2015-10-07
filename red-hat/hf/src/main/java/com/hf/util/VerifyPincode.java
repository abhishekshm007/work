package com.hf.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hf.dao.PincodeDAO;

/**
 * Servlet implementation class VerifyPincode
 */
@WebServlet("/VerifyPincode")
public class VerifyPincode extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger LOGGER = LoggerFactory.getLogger(VerifyPincode.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyPincode() {
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
			String pincode = request.getParameter("code").trim();
			boolean isVerified = PincodeDAO.verify(pincode);
			if(isVerified){
				response.setStatus(HttpServletResponse.SC_OK);
				out.print("Your pincode is acceptable");
				return;
			}else{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("Your pincode is not acceptable");
				return;
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("There is some problem, please try after some time");
			return;
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
