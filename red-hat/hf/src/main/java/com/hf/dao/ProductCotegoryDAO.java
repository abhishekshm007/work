package com.hf.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hf.persistentClass.ProductCotegory;
import com.hf.util.HibernateUtil;

public class ProductCotegoryDAO {

	public static boolean addProductCotegory(ProductCotegory productCotegory){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(productCotegory);
		   tx.commit();
		   return true;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   return false;
		}finally {
		   session.close();
		}
	}
	
}
