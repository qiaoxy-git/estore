package com.briup.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.OrderForm;
import com.briup.estore.bean.OrderFormExample;
import com.briup.estore.bean.OrderLine;
import com.briup.estore.mapper.OrderFormMapper;
import com.briup.estore.mapper.OrderLineMapper;
import com.briup.estore.service.IOrderFormService;
import com.briup.estore.util.MybatisSqlSessionUtil;

public class OrderFormServiceImpl implements IOrderFormService{

	@Override
	public OrderForm addOrderForm(OrderForm orderForm) throws RuntimeException {

		SqlSession sqlSession = MybatisSqlSessionUtil.getSqlSession(true);
		OrderFormMapper orderFormMapper = sqlSession.getMapper(OrderFormMapper.class);
		
		orderFormMapper.insert(orderForm);
		
		return orderForm;
		
	}

	@Override
	public void addOrderLine(OrderLine line) throws RuntimeException {

		SqlSession sqlSession = MybatisSqlSessionUtil.getSqlSession();
		
		OrderLineMapper lineMapper = sqlSession.getMapper(OrderLineMapper.class);
		
		lineMapper.insert(line);
		
		sqlSession.commit();
	}

	@Override
	public List<OrderForm> findAllOrderByCustomerId(int customerId) throws RuntimeException {

		SqlSession sqlSession = MybatisSqlSessionUtil.getSqlSession();
		OrderFormMapper orderFormMapper = sqlSession.getMapper(OrderFormMapper.class);
		
		OrderFormExample example = new OrderFormExample();
		example.createCriteria().andCustomerIdEqualTo(customerId);
		
		List<OrderForm> list = orderFormMapper.selectByExample(example);
		
		
		
		return list;
	}

	@Override
	public OrderForm findOrderFormById(int id) throws RuntimeException {

		SqlSession sqlSession = MybatisSqlSessionUtil.getSqlSession(true);
		OrderFormMapper orderFormMapper = sqlSession.getMapper(OrderFormMapper.class);
		
		return orderFormMapper.selectByPrimaryKey(id);
	}

}
