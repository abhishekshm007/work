package com.hf.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hf.persistentClass.Pincode;
import com.hf.util.HibernateUtil;

public class PincodeDAO {

	public static boolean verify(String pincode){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("verifyPincodeByPincode");
			query.setString("pincode", pincode);
			@SuppressWarnings("unchecked")
			List<Pincode> pincodeList = query.list();
			tx.commit();
			if(null != pincodeList && pincodeList.size() > 0){
				return true;
			}
			return false;
		}
		catch (Exception e) {
			if (tx!=null) tx.rollback();
			return false;
		}finally {
			session.close();
		}

	}
}
