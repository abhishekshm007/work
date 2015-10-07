package com.hf.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hf.dao.ProductDAO;
import com.hf.persistentClass.Product;

/**
 * Servlet implementation class GetProductList
 */
@WebServlet("/GetProductListForHome")
public class GetProductListForHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(GetProductListForHome.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductListForHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    PrintWriter out;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Integer first = Integer.parseInt(request.getParameter("first").trim());
			out = response.getWriter();
			Gson gson = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation() 
			.create();
			List<Product> productList = ProductDAO.getProductListForHome(first);
			String jsonData = gson.toJson(productList);
			response.setContentType("application/json; charset=UTF-8");
			out.println(jsonData);
			return;
			
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.print("There is some problem, please try again !!!");
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
