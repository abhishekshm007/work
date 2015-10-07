package com.hf.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet Filter implementation class InvalidJspAccessFilter
 */
@WebFilter("/jhkjahsdkjashdkjahksdjh")
public class InvalidJspAccessFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvalidJspAccessFilter.class);
    /**
     * Default constructor. 
     */
    public InvalidJspAccessFilter() {
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
			if(null != request.getAttribute("fromServlet")){
				chain.doFilter(request, response);
				return;
			}else{
				((HttpServletResponse) response).sendRedirect("home");
				return;
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
