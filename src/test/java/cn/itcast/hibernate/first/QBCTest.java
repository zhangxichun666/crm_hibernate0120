package cn.itcast.hibernate.first;

import cn.itcast.crm.domain.CstCustomer;
import cn.itcast.crm.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.junit.Test;

import javax.script.ScriptEngine;
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
        //精确查询
        //criteria.add(Restrictions.eq("custName","中国人寿"));
        //模糊查询
        criteria.add(Restrictions.like("custName","%人寿%"));

        List list = criteria.list();

        //第一次打印
        System.out.println(list);
    }
    //分页查询数据
    @Test
    public void test2(){
        //创建session
        Session session = HibernateUtil.openSession();
        Criteria criteria = session.createCriteria(CstCustomer.class);

        //当前页
        int page = 2;
        //每页显示记录数
        int maxResults = 1;

        //求出起始记录下标
        int firstResult = maxResults*(page-1);
        //设置起始 记录下标
        criteria.setFirstResult(firstResult);

        //设置每页查询记录数
        criteria.setMaxResults(maxResults);

        List list = criteria.list();

        System.out.println(list);
    }

    //投影查询（不查询全部列，自定义查询的列）
    @Test
    public void test3(){
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(CstCustomer.class);

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("custId").as("custId"))
                        .add(Projections.property("custName").as("custName"))
                );

        criteria.setResultTransformer(new AliasToBeanResultTransformer(CstCustomer.class));

        List list = criteria.list();
        System.out.println(list);

    }
    //查询记录总数
    @Test
    public void test4(){
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(CstCustomer.class);

        criteria.add(Restrictions.like("custName","%人寿%"));

        criteria.setProjection(Projections.rowCount());

        Long total = (Long)criteria.uniqueResult();

        System.out.println(total);
    }

    //测试DetachedCriteria
    @Test
    public void testDetachedCriteria(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CstCustomer.class);

        detachedCriteria.add(Restrictions.like("custName","%人寿%"));

        Session session = HibernateUtil.openSession();

        Criteria criteria = detachedCriteria.getExecutableCriteria(session);

        List list = criteria.list();

        System.out.println(list);
    }
}
