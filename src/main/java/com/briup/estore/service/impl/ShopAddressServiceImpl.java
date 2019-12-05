package com.briup.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.bean.ShopAddress;
import com.briup.estore.bean.ShopAddressExample;
import com.briup.estore.mapper.ShopAddressMapper;
import com.briup.estore.service.IShopAddressService;
import com.briup.estore.util.MybatisSqlSessionUtil;

public class ShopAddressServiceImpl implements IShopAddressService{

	@Override
	public List<ShopAddress> findAllShopAddressByCustomerId(int customerId) throws RuntimeException {

		SqlSession sqlSession = MybatisSqlSessionUtil.getSqlSession();
		ShopAddressMapper shopAddressMapper = sqlSession.getMapper(ShopAddressMapper.class);
		
		
		ShopAddressExample example = new ShopAddressExample();
		example.createCriteria().andCustomerIdEqualTo(customerId);
		return shopAddressMapper.selectByExample(example);
	}

}
