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

import com.o9paathshala.constants.ApplicationContants;
import com.o9paathshala.dto.SessionDTO;

/**
 * Servlet Filter implementation class UserDistinguishingFilterForFaculty
 */
public class UserDistinguishingFilterForFaculty implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDistinguishingFilterForFaculty.class);
    /**
     * Default constructor. 
     */
    public UserDistinguishingFilterForFaculty() {
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
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			if(null == session){
				((HttpServletResponse) response).sendRedirect("../index.jsp");
				return;
			}
			
			SessionDTO sessionDTO = (SessionDTO) session.getAttribute("user");
			if(null == sessionDTO){
				((HttpServletResponse) response).sendRedirect("../index.jsp");
				return;
			}
			if(sessionDTO.getType().equals(ApplicationContants.FACULTY)){
				chain.doFilter(request, response);
				return;
			}else{
				((HttpServletResponse) response).sendRedirect("../index.jsp");
				return;
			}
		}catch(Exception e){
			LOGGER.error("error while user distinguidhing filter", e);
			((HttpServletResponse) response).sendRedirect("../index.jsp");
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
