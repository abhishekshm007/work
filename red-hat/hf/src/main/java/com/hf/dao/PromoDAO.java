package com.hf.dao;

import com.hf.persistentClass.Promo;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hf.util.HibernateUtil;

public class PromoDAO {

	public static boolean addPromo(Promo promo){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(promo);
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
