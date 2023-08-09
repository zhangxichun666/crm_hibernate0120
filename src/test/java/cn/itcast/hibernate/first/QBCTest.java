package cn.itcast.hibernate.first;

import cn.itcast.crm.domain.CstCustomer;
import cn.itcast.crm.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName QBCTest
 * @Description
 * @Author LENOVO
 * @Date 2023/8/9 15:58
 * @Version 1.0
 */
public class QBCTest {
    //查询实体对象
    @Test
    public void test1(){
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(CstCustomer.class);

        criteria.add(Restrictions.eq("custName","中国人寿"));

        List list = criteria.list();

        System.out.println(list);
    }
}
