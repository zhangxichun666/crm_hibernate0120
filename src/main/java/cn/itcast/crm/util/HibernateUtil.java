package cn.itcast.crm.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * @ClassName HibernateUtil
 * @Description
 * @Author LENOVO
 * @Date 2023/8/9 14:21
 * @Version 1.0
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    //SessionFactory 是线程安全的
    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    //创建sessionFactory
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    //获取session
    public static Session openSession(){
        return sessionFactory.openSession();
    }
}
