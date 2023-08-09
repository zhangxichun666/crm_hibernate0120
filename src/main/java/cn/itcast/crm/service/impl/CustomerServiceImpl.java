package cn.itcast.crm.service.impl;

import cn.itcast.crm.dao.CstCustomerDao;
import cn.itcast.crm.dao.impl.CstCustomerDaoImpl;
import cn.itcast.crm.domain.CstCustomer;
import cn.itcast.crm.service.CustomerService;

/**
 * @ClassName CustomerServiceImpl
 * @Description
 * @Author LENOVO
 * @Date 2023/8/9 14:52
 * @Version 1.0
 */
public class CustomerServiceImpl implements CustomerService {
    private CstCustomerDao cstCustomerDao = new CstCustomerDaoImpl();

    public void insertCustomer(CstCustomer cstCustomer) {
        cstCustomerDao.insertCustomer(cstCustomer);
    }
}
