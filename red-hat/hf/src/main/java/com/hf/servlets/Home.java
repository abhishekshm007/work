package com.hf.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hf.dao.BannerDAO;
import com.hf.dao.StockCotegoryDAO;
import com.hf.persistentClass.Banner;
import com.hf.persistentClass.StockCotegory;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Banner> bannerList = BannerDAO.getBannerList();
		request.setAttribute("bannerList", bannerList);
		List<StockCotegory> stockList = StockCotegoryDAO.getStockList();
		request.setAttribute("stockCotegoryList", stockList);
		request.setAttribute("fromServlet", true);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
		return;
	}

}
