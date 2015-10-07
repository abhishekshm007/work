package com.o9paathshala.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.o9paathshala.constants.ApplicationContants;

/**
 * Application Lifecycle Listener implementation class ConstantsInsitializer
 *
 */
@WebListener
public class ConstantsInsitializer implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ConstantsInsitializer() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

    
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        sce.getServletContext().setAttribute("appName", ApplicationContants.APPNAME);
    }
	
}
