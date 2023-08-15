package cn.itcast.crm.dao;

import cn.itcast.crm.domain.CstCustomer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CstCustomerDao {
    //新增客户
    public void insertCustomer(CstCustomer cstCustomer);

    //查询记录总数
    public Long findCustomerCount(DetachedCriteria detachedCriteria);
    //分页查询数据列表
    public List<CstCustomer> findCustomerList(DetachedCriteria detachedCriteria, int firstResult, int maxResults);
}
