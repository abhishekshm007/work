package com.hf.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.hf.dao.StockCotegoryDAO;
import com.hf.persistentClass.Product;
import com.hf.persistentClass.ProductCotegory;
import com.hf.persistentClass.StockCotegory;

/**
 * Servlet implementation class GetProductListForStockCotegory
 */
@WebServlet("/GetProductListForStockCotegory")
public class GetProductListForStockCotegory extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(GetProductListForStockCotegory.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductListForStockCotegory() {
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
			Integer id = Integer.parseInt(request.getParameter("id").trim());
			StockCotegory stockCotegory = StockCotegoryDAO.getStockCotegory(id);
			List<ProductCotegory> productCotegoryList = (List<ProductCotegory>) stockCotegory.getProductCotegoryList(); 
			List<Product> productList = new ArrayList<Product>();
			
			
			Iterator< ProductCotegory> iterator = productCotegoryList.iterator();
			while(iterator.hasNext()){
				productList.addAll(iterator.next().getProductList());
			}
		
			String jsonData = new Gson().toJson(productList);
			response.setStatus(HttpServletResponse.SC_OK);
			out.print(jsonData);
			return;
		
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.print("There is some problem ,please try again !!!");
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
		// TODO Auto-generated method stub
	}

}
