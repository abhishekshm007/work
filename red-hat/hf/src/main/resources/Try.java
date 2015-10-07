import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.hf.persistentClass.Banner;
import com.hf.persistentClass.Product;
import com.hf.persistentClass.ProductCotegory;
import com.hf.util.HibernateUtil;


public class Try {

	public static void main(String[] args) {
//		StockCotegory fruitstockCotegory = new StockCotegory("Fruits");
//		StockCotegory vegetablestockCotegory = new StockCotegory("Vegetables");
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		session.save(fruitstockCotegory);
//		session.save(vegetablestockCotegory);
//		session.getTransaction().commit();
//		session.close();
//		System.out.println("done");
		
		
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		StockCotegory stockCotegory = (StockCotegory) session.get(StockCotegory.class, 2);
//		Collection<ProductCotegory> productCotegories = new ArrayList<ProductCotegory>();
//		ProductCotegory productCotegory = new ProductCotegory("onion");
//		productCotegories.add(productCotegory);
//		productCotegory.setStockCotegory(stockCotegory);
//		stockCotegory.setProductCotegoryList(productCotegories);
//		session.save(stockCotegory);
//		session.save(productCotegory);
//		productCotegory = new ProductCotegory("pea");
//		productCotegories.add(productCotegory);
//		productCotegory.setStockCotegory(stockCotegory);
//		stockCotegory.setProductCotegoryList(productCotegories);
//		session.save(stockCotegory);
//		session.save(productCotegory);
//		productCotegory = new ProductCotegory("potato");
//		productCotegories.add(productCotegory);
//		productCotegory.setStockCotegory(stockCotegory);
//		stockCotegory.setProductCotegoryList(productCotegories);
//		session.save(stockCotegory);
//		session.save(productCotegory);
//		productCotegory = new ProductCotegory("cucumber");
//		productCotegories.add(productCotegory);
//		productCotegory.setStockCotegory(stockCotegory);
//		stockCotegory.setProductCotegoryList(productCotegories);
//		session.save(stockCotegory);
//		session.save(productCotegory);
//		productCotegory = new ProductCotegory("radish");
//		productCotegories.add(productCotegory);
//		productCotegory.setStockCotegory(stockCotegory);
//		stockCotegory.setProductCotegoryList(productCotegories);
//		session.save(stockCotegory);
//		session.save(productCotegory);
//		productCotegory = new ProductCotegory("mushroom");
//		productCotegories.add(productCotegory);
//		productCotegory.setStockCotegory(stockCotegory);
//		stockCotegory.setProductCotegoryList(productCotegories);
//		session.save(stockCotegory);
//		session.save(productCotegory);
//		productCotegory = new ProductCotegory("sweet corn");
//		productCotegories.add(productCotegory);
//		productCotegory.setStockCotegory(stockCotegory);
//		stockCotegory.setProductCotegoryList(productCotegories);
//		session.save(stockCotegory);
//		session.save(productCotegory);
//		productCotegory = new ProductCotegory("tomato");
//		productCotegories.add(productCotegory);
//		productCotegory.setStockCotegory(stockCotegory);
//		stockCotegory.setProductCotegoryList(productCotegories);
//		session.save(stockCotegory);
//		session.save(productCotegory);
//		session.getTransaction().commit();
//		session.close();
//		System.out.println("done");
		
		
		
//		ProductCotegory productCotegory;
//		
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		productCotegory = (ProductCotegory) session.get(ProductCotegory.class, 11);
//		Product product = new Product("Yellow onion", "Yellow onion (Allium cepa) (Latin 'cepa' = onion), also known as the bulb onion or common onion, is used as a vegetable and is the most widely cultivated species of the genus Allium. This genus also contains several other species variously referred to as onions and cultivated for food, such as the Japanese bunching onion (A. fistulosum), the Egyptian onion (A. ×proliferum), and the Canada onion (A. canadense). "
//				, 543.2, false, false, true, 33, productCotegory);
//		List<Product> productList = new ArrayList<Product>();
//		productList.add(product);
//		product.setProductCotegory(productCotegory);
//		Product product2 = new Product("Red onion", "Red onion (Allium cepa) (Latin 'cepa' = onion), also known as the bulb onion or common onion, is used as a vegetable and is the most widely cultivated species of the genus Allium. This genus also contains several other species variously referred to as onions and cultivated for food, such as the Japanese bunching onion (A. fistulosum), the Egyptian onion (A. ×proliferum), and the Canada onion (A. canadense). "
//				, 543.2, false, false, true, 33, productCotegory);
//		productList.add(product2);
//		productCotegory.setProductList(productList);
//		product2.setProductCotegory(productCotegory);
//		session.save(product);
//		session.save(product2);
//		session.save(productCotegory);
//		session.getTransaction().commit();
//		session.close();
		
//		Banner banner;
//		Product product;
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		product = (Product) session.get(Product.class, 1);
//		banner = new Banner(product.getProductName(), product.getProductDesc(), product.getProductId());
//		session.save(banner);
//		product = (Product) session.get(Product.class, 6);
//		banner = new Banner(product.getProductName(), product.getProductDesc(), product.getProductId());
//		session.save(banner);
//		product = (Product) session.get(Product.class, 13);
//		banner = new Banner(product.getProductName(), product.getProductDesc(), product.getProductId());
//		session.save(banner);
//		session.getTransaction().commit();
//		session.close();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
		Transaction tx  = null;
		try {
		   tx = session.beginTransaction();
		   Long counts = (Long) session.createCriteria("com.hf.persistentClass.Product").setProjection(Projections.rowCount()).uniqueResult();
		   System.out.println("counts "+counts);
		   tx.commit();
		}
		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace();
		}finally {
		   session.close();
		}
	}
}
