package cn.itcast.hibernate.first;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.cfg.Configuration;


import cn.itcast.crm.domain.CstCustomer;
import org.junit.Before;
import org.junit.Test;


public class CrudTest {
	// 会话工厂
	private SessionFactory sessionFactory;

	@Before
	public void setUp()
	{

		// 创建sessionFactory
		// 默认加载classpath下的hibernate.cfg.xml
	    sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	// 添加
	@Test
	public void insert() {
		//SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		// 通过sessionFactory创建session
		// 创建一个新session
		Session session = sessionFactory.openSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		try {
			// 通过session操作数据库
			// 创建customer对象
			CstCustomer cstCustomer = new CstCustomer();
			cstCustomer.setCustName("中国人寿");
			session.save(cstCustomer);
			// 提交事务
			transaction.commit();
		} catch (Exception e) {
			// 打印异常日志
			e.printStackTrace();
			// 回滚
			transaction.rollback();
		} finally {
			// 释放资源
			session.close();
		}

	}
	//根据主键查询
	@Test
	public void getById(){
		Session session = sessionFactory.openSession();
		CstCustomer cstCustomer = session.get(CstCustomer.class, 94L);
		System.out.println(cstCustomer);
		session.close();
	}
	//更新操作
	@Test
	public void update(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {

			CstCustomer cstCustomer = session.get(CstCustomer.class, 94L);
			cstCustomer.setCustName("中国人寿财险");
			cstCustomer.setCustPhone("1384140868");
			session.update(cstCustomer);
			transaction.commit();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}

	}
	//删除操作
	@Test
	public void delete(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			CstCustomer cstCustomer = new CstCustomer();
			cstCustomer.setCustId(94L);
			session.delete(cstCustomer);
			transaction.commit();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
