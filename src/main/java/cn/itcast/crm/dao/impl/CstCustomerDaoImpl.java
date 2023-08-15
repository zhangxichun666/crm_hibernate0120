package cn.itcast.crm.dao.impl;

import cn.itcast.crm.dao.CstCustomerDao;
import cn.itcast.crm.domain.CstCustomer;
import cn.itcast.crm.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import java.util.List;

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

    @Override
    public Long findCustomerCount(DetachedCriteria detachedCriteria) {
        Session session = HibernateUtil.openSession();
        Criteria criteria = detachedCriteria.getExecutableCriteria(session);
        //设置rowCount投影列
        criteria.setProjection(Projections.rowCount());

        Long total = (Long) criteria.uniqueResult();
        return total;
    }

    @Override
    public List<CstCustomer> findCustomerList(DetachedCriteria detachedCriteria, int firstResult, int maxResults) {
        Session session = HibernateUtil.openSession();
        Criteria criteria = detachedCriteria.getExecutableCriteria(session);
        //设置分页参数
        criteria.setFirstResult(firstResult);   //起始 记录下标
        criteria.setMaxResults(maxResults);     //每页显示个数
        List<CstCustomer> list = criteria.list();
        System.out.println(list);
        return criteria.list();
    }
}
