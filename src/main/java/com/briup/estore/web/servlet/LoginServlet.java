package com.briup.estore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.bean.ex.ShopCart;
import com.briup.estore.service.impl.CustomerServiceImpl;
import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		CustomerServiceImpl customerService = new CustomerServiceImpl();
		Customer customer = customerService.findCustomerByUsernameAndPassword(name, password);
		
		HttpSession session = request.getSession();
		
		if(customer == null) {
			session.setAttribute("msg", "登录失败：用户名密码错误");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else {
			ShopCart cart = new ShopCart();
			session.setAttribute("cart", cart);
			// 存放登陆数据
			session.setAttribute("customer", customer);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
