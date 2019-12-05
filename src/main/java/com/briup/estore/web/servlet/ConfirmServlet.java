package com.briup.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.bean.ShopAddress;
import com.briup.estore.service.IShopAddressService;
import com.briup.estore.service.impl.ShopAddressServiceImpl;

public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfirmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 调用service去查询所有的收货地址
		IShopAddressService addressServiceImpl = new ShopAddressServiceImpl();
		
		HttpSession session = request.getSession();
		Customer cus =(Customer)session.getAttribute("customer");
		
		List<ShopAddress> list = addressServiceImpl.findAllShopAddressByCustomerId(cus.getId());
		
		request.getSession().setAttribute("address", list);
		
		response.sendRedirect(request.getContextPath() + "/after/confirm.jsp");

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
