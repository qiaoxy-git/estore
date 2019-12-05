package com.briup.estore.bean.ex;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.briup.estore.bean.OrderLine;

public class ShopCart {
	// key 商品id value 订单项（商品，数量）
	private Map<Integer, OrderLine> map = new HashMap<>();

	/**
	 * 向购物车中添加产品 先判断购物车中有没有该产品的订单项目，如果有，在数量上做改变， 如果没有向map中添加
	 * 
	 * @param line
	 */
	public void add(OrderLine line) {
// 购物车有这个商品
		 if(map.containsKey(line.getBookId())) {
			 OrderLine newLine = map.get(line.getBookId());
			 newLine.setNum(newLine.getNum() + line.getNum());
			 newLine.setCost(line.getBook().getPrice() * newLine.getNum());
			 map.put(line.getBookId(), newLine);
		 } else {
			 line.setNum(1);
			 map.put(line.getBookId(), line);
		 }
 		
	}

	/**
	 * 删除订单项
	 * 
	 */
	public void delete(Integer bookId) {
		map.remove(bookId);
	}

	/**
	 * 获取价钱
	 */
	public double getCost() {
		
		Collection<OrderLine> orederLines = map.values();
		
		double sum = 0;
		for (OrderLine orderLine : orederLines) {
			sum += orderLine.getCost();
		}
		return sum;
	}

	/**
	 * 获取购物车中所有订单项 Collection<OrderLine>
	 */
	public Collection<OrderLine> getLines() {
		return map.values();
	}

	/**
	 * 清空购物车
	 */
	public void clear() {
		map.clear();
	}

	/**
	 * 修改指定line中的数量
	 */
	public void update(long key, int num) {
		OrderLine line = map.get(key);
		if(line != null) {
			line.setNum(line.getNum() + num);
		}
	}
	
	public int getSize() {
		return map.size();
	}

}
