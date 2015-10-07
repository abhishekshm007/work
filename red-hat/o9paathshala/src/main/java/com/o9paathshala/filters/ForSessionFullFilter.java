package com.o9paathshala.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.o9paathshala.dto.SessionDTO;

/**
 * Servlet Filter implementation class SessionCheckFilter
 */

public class ForSessionFullFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ForSessionFullFilter.class);
    /**
     * Default constructor. 
     */
    public ForSessionFullFilter() {
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			LOGGER.error("full1");
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			LOGGER.error("full2");
			if(null != session ){
				LOGGER.error("full3");
				SessionDTO sessionDTO = (SessionDTO) session.getAttribute("user");
				LOGGER.error("full4");
				if(null != sessionDTO ){
					LOGGER.error("full5");
					chain.doFilter(request, response);
					LOGGER.error("full6");
					return;
				}else{
					LOGGER.error("full7");
					((HttpServletResponse) response).sendRedirect("../login.jsp");
					LOGGER.error("full8");
					return;
				}
			}else{
				LOGGER.error("full9");
				((HttpServletResponse) response).sendRedirect("../login.jsp");
				return;
			}
			
			
		}catch(Exception e){
			LOGGER.error("full10");
			LOGGER.error("exception occured during session check", e);
			((HttpServletResponse) response).sendRedirect("../index.jsp");
			
			return;
		}
	}
	

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
