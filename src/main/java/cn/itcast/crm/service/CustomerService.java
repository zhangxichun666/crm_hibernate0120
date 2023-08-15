package cn.itcast.crm.service;

import cn.itcast.crm.domain.CstCustomer;

import java.util.List;

public interface CustomerService {
    //新增客户
    public void insertCustomer(CstCustomer cstCustomer);
    //查询记录总数
    public Long findCustomerCount(CstCustomer cstCustomer);
    //查询记录列表
    public List<CstCustomer> findCustomerList(CstCustomer cstCustomer,int firstResult,int maxResults);
}
