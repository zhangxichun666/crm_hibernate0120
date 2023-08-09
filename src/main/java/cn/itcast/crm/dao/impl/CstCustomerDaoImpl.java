package cn.itcast.crm.dao.impl;

import cn.itcast.crm.dao.CstCustomerDao;
import cn.itcast.crm.domain.CstCustomer;
import cn.itcast.crm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @ClassName CstCustomerDaoImpl
 * @Description
 * @Author LENOVO
 * @Date 2023/8/9 14:47
 * @Version 1.0
 */
public class CstCustomerDaoImpl implements CstCustomerDao {
    public void insertCustomer(CstCustomer cstCustomer) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(cstCustomer);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
    }
}
