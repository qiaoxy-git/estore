package com.briup.estore.web.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.bean.OrderForm;
import com.briup.estore.bean.OrderLine;
import com.briup.estore.bean.ex.ShopCart;
import com.briup.estore.service.impl.OrderFormServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String addId = request.getParameter("shipAddId");
		
		HttpSession session = request.getSession();
		
		ShopCart shopCart = (ShopCart) session.getAttribute("cart");
		Customer cus = (Customer) session.getAttribute("customer");
		
		
		OrderForm orderForm = new OrderForm();
		orderForm.setCost(shopCart.getCost());
		orderForm.setCustomerId(cus.getId());
		orderForm.setOrderdate(new Date());
		orderForm.setShopaddressId(Integer.parseInt(addId));
		
		OrderFormServiceImpl orderFormService = new OrderFormServiceImpl();
		orderFormService.addOrderForm(orderForm);
		
		Collection<OrderLine> lines = shopCart.getLines();
		
		for (OrderLine orderLine : lines) {
			orderLine.setOrderformId(orderForm.getId());
			orderFormService.addOrderLine(orderLine);
		}
		
		// 跳轉訂單頁面
		// 查詢數據庫中所有訂單
		
		List<OrderForm> list = orderFormService.findAllOrderByCustomerId(cus.getId());
		
		session.setAttribute("orders", list);
		// 当你提交订单的时候清空购物车
		shopCart.clear();
		
		response.sendRedirect(request.getContextPath() + "/after/orderlist.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
