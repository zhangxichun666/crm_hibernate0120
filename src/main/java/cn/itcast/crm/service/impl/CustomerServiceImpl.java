package cn.itcast.crm.service.impl;

import cn.itcast.crm.dao.CstCustomerDao;
import cn.itcast.crm.dao.impl.CstCustomerDaoImpl;
import cn.itcast.crm.domain.CstCustomer;
import cn.itcast.crm.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    @Override
    public Long findCustomerCount(CstCustomer cstCustomer) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CstCustomer.class);
        if(cstCustomer != null){
            if(cstCustomer.getCustName() != null && !cstCustomer.getCustName().equals("")){
                detachedCriteria.add(Restrictions.like("custName","%"+cstCustomer.getCustName()+"%"));
            }
            if(cstCustomer.getCustLinkman()!=null && !cstCustomer.getCustLinkman().equals("")){
                detachedCriteria.add(Restrictions.eq("custLinkman",cstCustomer.getCustLinkman()));
            }
        }

        return cstCustomerDao.findCustomerCount(detachedCriteria);
    }

    @Override
    public List<CstCustomer> findCustomerList(CstCustomer cstCustomer, int firstResult, int maxResults) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CstCustomer.class);
        //拼接查询条件
        if(cstCustomer != null){
            //拼接客户名称查询条件，使用like
            if(cstCustomer.getCustName() != null && !cstCustomer.getCustName().equals("")){
                detachedCriteria.add(Restrictions.like("custName","%"+cstCustomer.getCustName()+"%"));
            }
            //拼接联系人查询条件，使用eq
            if(cstCustomer.getCustLinkman() != null && !cstCustomer.getCustLinkman().equals("")){
                detachedCriteria.add(Restrictions.eq("custLinkman",cstCustomer.getCustLinkman()));
            }
        }
        return cstCustomerDao.findCustomerList(detachedCriteria,firstResult,maxResults);
    }
}
