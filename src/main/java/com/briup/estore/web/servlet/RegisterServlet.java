package com.briup.estore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.service.ICustomerService;
import com.briup.estore.service.impl.CustomerServiceImpl;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		try {

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String zipCode = request.getParameter("zipCode");
			String telephone = request.getParameter("telephone");
			String email = request.getParameter("email");

			// 调用service
			CustomerServiceImpl customerService = new CustomerServiceImpl();

			Customer customer = new Customer();
			customer.setName(name);
			customer.setPassword(Integer.parseInt(password));
			customer.setZip(zipCode);
			customer.setTelephone(telephone);
			customer.setEmail(email);

			customerService.saveOrUpdate(customer);
			
			session.setAttribute("msg", "注册成功请登录");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} catch (Exception e) {
			// 发生异常需要对异常进行处理，并且把异常
			session.setAttribute("msg", "注册失败，请重新注册");
			response.sendRedirect(request.getContextPath() + "/register.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
