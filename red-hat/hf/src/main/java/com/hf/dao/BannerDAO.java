package com.hf.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import com.hf.persistentClass.Banner;
import com.hf.util.HibernateUtil;

public class BannerDAO {

	public static boolean addBanner(Banner banner){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(banner);
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
	public static List<Banner> getBannerList(){
		List<Banner> bannerList = new ArrayList<Banner>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);
        Session session = sessionFactory.openSession();
		Transaction tx  = null;
		try {
		   tx = session.beginTransaction();
		   Query query = session.getNamedQuery("getBannerLimitFive");
		   bannerList = query.list();
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheHitCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheMissCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCachePutCount());
		   System.out.println(sessionFactory.getStatistics().getSecondLevelCacheRegionNames()[0]);
		   tx.commit();
		  return bannerList;
		}
		catch (Exception e) {
			e.printStackTrace();
		   if (tx!=null) tx.rollback();
		   return null;
		}finally {
		   session.close();
		}
	}
}
