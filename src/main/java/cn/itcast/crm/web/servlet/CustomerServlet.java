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
		}else if(method.equals("list")){
			this.list(req, resp);
		}
	}

	//查询客户提交
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		CustomerService customerService = new CustomerServiceImpl();

		//----------封装查询条件--------------
		CstCustomer query_cstCustomer = new CstCustomer();
		//客户名称
		String custName = req.getParameter("custName");
		//客户联系人
		String custLinkman = req.getParameter("custLinkman");

		query_cstCustomer.setCustName(custName);
		query_cstCustomer.setCustLinkman(custLinkman);
		//查询记录总数
		long total = customerService.findCustomerCount(query_cstCustomer);

		//-----------分页参数-------------

		//每页显示个数
		String pageSizeString = req.getParameter("pageSize");
		int pageSize = Integer.parseInt(pageSizeString == null?"15":pageSizeString);
		//计算总页数
		Double num = Math.ceil(total*1.0/pageSize);
		int totalPage = num.intValue();
		//当前页码
		String pageString = req.getParameter("page");
		int page = Integer.parseInt(pageString == null||pageString.equals("")?"1":pageString);
		if(page<=0){
			page = 1;
		}
		if(page>totalPage){
			page = totalPage;
		}
		//根据分页参数计算出起始记录下标
		int firstResult = pageSize * (page - 1);

		List<CstCustomer> list = customerService.findCustomerList(query_cstCustomer, firstResult, pageSize);
		//当前页码
		req.setAttribute("page", page);
		//总页数
		req.setAttribute("totalPage", totalPage);
		//每页显示个数
		req.setAttribute("pageSize", pageSize);
		//总数
		req.setAttribute("total", total);
		//列表
		req.setAttribute("list", list);
		//成功
		req.getRequestDispatcher("/jsp/customer/list.jsp").forward(req, resp);
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

