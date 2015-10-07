package com.hf.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private HibernateUtil(){
		
	}
	
	private static SessionFactory sessionFactory =  null;
	
	private synchronized static void buildSessionFactory(){
		try{
			if(null == sessionFactory){
				Configuration configuration = new Configuration();
				configuration.configure("hibernate.cfg.xml");
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				        .applySettings(configuration.getProperties()).build();
				sessionFactory = configuration
				        .buildSessionFactory(serviceRegistry);
			}
		}catch(Throwable e){
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		if(null == sessionFactory){
			buildSessionFactory();
		}
		return sessionFactory;
	}
	
//	 private static final SessionFactory sessionFactory = buildSessionFactory();
//
//	    private static SessionFactory buildSessionFactory() {
//	        try {
//	            // Create the SessionFactory from hibernate.cfg.xml
//	        	Configuration configuration = new Configuration();
//	        	configuration.configure("hibernate.cfg.xml");
//	        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//	        	        .applySettings(configuration.getProperties()).build();
//	        	return configuration
//	        	        .buildSessionFactory(serviceRegistry);
//	        }
//	        catch (Throwable ex) {
//	            // Make sure you log the exception, as it might be swallowed
//	            System.err.println("Initial SessionFactory creation failed." + ex);
//	            throw new ExceptionInInitializerError(ex);
//	        }
//	    }
//
//	    public static SessionFactory getSessionFactory() {
//	        return sessionFactory;
//	    }
	
}
