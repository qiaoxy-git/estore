package com.briup.estore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.estore.bean.Book;
import com.briup.estore.bean.OrderLine;
import com.briup.estore.bean.ex.ShopCart;
import com.briup.estore.service.impl.BookServiceImpl;

public class AddCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCarServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String bookId = request.getParameter("id");
		String num = request.getParameter("num");
		
		BookServiceImpl bookservice = new BookServiceImpl();
		Book book = bookservice.findBookById(Integer.parseInt(bookId));
		// 将订单项添加到购物车
		OrderLine orderLine = new OrderLine();
		orderLine.setBookId(book.getId());
		orderLine.setNum(Integer.parseInt(num));
		orderLine.setCost(book.getPrice() * Integer.parseInt(num));
		// 订单项与book发生关系
		orderLine.setBook(book);
		
		HttpSession session = request.getSession();
		ShopCart cart =(ShopCart)session.getAttribute("cart");
		// 如果购物车没有就创建一个新的
		if(cart == null) {
			cart = new ShopCart();
			session.setAttribute("cart", cart);
		}
		cart.add(orderLine);
		response.sendRedirect(request.getContextPath() +"/after/shopCart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
