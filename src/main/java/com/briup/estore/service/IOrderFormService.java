package com.briup.estore.service;

import java.util.List;

import com.briup.estore.bean.OrderForm;
import com.briup.estore.bean.OrderLine;

public interface IOrderFormService {
	
	OrderForm addOrderForm(OrderForm orderForm) throws RuntimeException;
	
//  返回包含自增長id
	
	void addOrderLine(OrderLine line) throws RuntimeException;
	
	List<OrderForm> findAllOrderByCustomerId(int customerId) throws RuntimeException;
	
	OrderForm findOrderFormById(int id) throws RuntimeException;
}
