package com.hf.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import com.hf.persistentClass.StockCotegory;
import com.hf.util.HibernateUtil;

public class StockCotegoryDAO {

	public static boolean addCotegory(StockCotegory stockCotegory){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(stockCotegory);
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
	public static List<StockCotegory> getStockList(){
		List<StockCotegory> stockCotegoryList = new ArrayList<StockCotegory>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);
        Session session = sessionFactory.openSession();
		Transaction tx  = null;
		try {
		   tx = session.beginTransaction();
		   Query query = session.getNamedQuery("getStockList");
		   stockCotegoryList = query.list();
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheHitCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheMissCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCachePutCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheRegionNames()[0]);
		   tx.commit();
		  return stockCotegoryList;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   return null;
		}finally {
		   session.close();
		}
	}
	
	public static StockCotegory getStockCotegory(Integer id){
		StockCotegory stockCotegory;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);
        Session session = sessionFactory.openSession();
		Transaction tx  = null;
		try {
		   tx = session.beginTransaction();
		   stockCotegory = (StockCotegory) session.get(StockCotegory.class, id);
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheHitCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheMissCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCachePutCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheRegionNames()[0]);
		   tx.commit();
		  return stockCotegory;
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   return null;
		}finally {
		   session.close();
		}
	}
}
