package com.hf.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.stat.Statistics;

import com.hf.persistentClass.Product;
import com.hf.util.HibernateUtil;

public class ProductDAO {

	public static boolean addProduct(Product product){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(product);
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
	
	@SuppressWarnings("unchecked")
	public static List<Product> getProductListForHome(Integer first){
		List<Product> bannerList = new ArrayList<Product>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);
        Session session = sessionFactory.openSession();
		Transaction tx  = null;
		try {
		   tx = session.beginTransaction();
		   Query query = session.getNamedQuery("getProductForHome");
		   query.setFirstResult(first);
		   query.setMaxResults(6);
		   bannerList = query.list();
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheHitCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheMissCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCachePutCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheRegionNames()[0]);
		   tx.commit();
		  return bannerList;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   return null;
		}finally {
		   session.close();
		}
	}
	
	
	public static Long getCountOfProducts(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
		Transaction tx  = null;
		try {
		   tx = session.beginTransaction();
		   Long counts = (Long) session.createCriteria("com.hf.persistentClass.Product").setProjection(Projections.rowCount()).uniqueResult();
		   tx.commit();
		  return counts;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   return null;
		}finally {
		   session.close();
		}
	}
}
