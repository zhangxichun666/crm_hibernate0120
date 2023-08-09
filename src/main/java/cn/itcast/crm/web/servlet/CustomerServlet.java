package cn.itcast.crm.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.crm.domain.CstCustomer;
import cn.itcast.crm.service.CustomerService;
import cn.itcast.crm.service.impl.CustomerServiceImpl;

public class CustomerServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取请求的方法名
		String method = req.getParameter("method");
		if(method == null || method.equals("") || method.equals("add")){
			//转发到添加客户页面
			req.getRequestDispatcher("/jsp/customer/add.jsp").forward(req, resp);
		}else if(method.equals("addsubmit")){
			this.addsubmit(req, resp);
		}
	}

	
//添加客户提交
	private void addsubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//客户信息
		String custName = req.getParameter("custName");//客户名称
		String custLevel = req.getParameter("custLevel");//客户级别
		String custSource = req.getParameter("custSource");//信息来源
		String custLinkman = req.getParameter("custLinkman");//联系人
		String custPhone = req.getParameter("custPhone");//固定电话
		String custMobile = req.getParameter("custMobile");//移动电话
		
		//客户详细信息
		String custAddress = req.getParameter("custAddress");//联系地址
		String custZip = req.getParameter("custZip");//邮政编码
		
		CustomerService customerService = new CustomerServiceImpl();
		//客户信息
		CstCustomer cstCustomer = new CstCustomer();
		cstCustomer.setCustName(custName);
		cstCustomer.setCustPhone(custPhone);
		cstCustomer.setCustLinkman(custLinkman);
		cstCustomer.setCustMobile(custMobile);
		
		//调用新的 service接口
		try {
			customerService.insertCustomer(cstCustomer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//失败
			req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
			return ;
		}
		//成功
		req.getRequestDispatcher("/jsp/success.jsp").forward(req, resp);
	}
}

