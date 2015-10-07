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
 * Servlet Filter implementation class ForSessionLessFilter
 */

public class ForSessionLessFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ForSessionLessFilter.class);
    /**
     * Default constructor. 
     */
    public ForSessionLessFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			if(null == session){
				chain.doFilter(request, response);
				return;
			}
			SessionDTO sessionDTO = (SessionDTO) session.getAttribute("user");
			
			if( null == sessionDTO){
				chain.doFilter(request, response);
				return;
			}else{
				String user=null;
				switch(sessionDTO.getType()){
				case 1:user="institute";
				break;
				case 2:user="faculty";
				break;
				case 3:user="student";
				break;
				default:break;
				}
				((HttpServletResponse) response).sendRedirect(""+user.toLowerCase()+"/dashboard.jsp");
				return;
			}
		}catch(Exception e){
			((HttpServletResponse) response).sendRedirect("index.jsp");
			LOGGER.error("exception occured during session check", e);
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
