package com.hf.listeners;

import java.util.List;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.hf.constants.ApplicationContants;
import com.hf.dao.BannerDAO;
import com.hf.dao.StockCotegoryDAO;
import com.hf.persistentClass.Banner;
import com.hf.persistentClass.StockCotegory;

/**
 * Application Lifecycle Listener implementation class ContextValueSetterListener
 *
 */
@WebListener
public class ContextValueSetterListener implements ServletContextListener, ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public ContextValueSetterListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         arg0.getServletContext().setAttribute("applicationName", ApplicationContants.APPLICATION_NAME);
         arg0.getServletContext().setAttribute("tagLine", ApplicationContants.TAG_LINE);
         arg0.getServletContext().setAttribute("mobileNo", ApplicationContants.MOBILE_NO);
         arg0.getServletContext().setAttribute("supportEmailId", ApplicationContants.SUPPORT_EMAIL_ID);
         arg0.getServletContext().setAttribute("bannerList", BannerDAO.getBannerList());
         arg0.getServletContext().setAttribute("stockCotegoryList", StockCotegoryDAO.getStockList());
    }
}
