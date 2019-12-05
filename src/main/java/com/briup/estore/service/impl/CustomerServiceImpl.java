package com.briup.estore.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.bean.CustomerExample;
import com.briup.estore.mapper.CustomerMapper;
import com.briup.estore.service.ICustomerService;
import com.briup.estore.util.MybatisSqlSessionUtil;

public class CustomerServiceImpl implements ICustomerService{

	@Override
	public void saveOrUpdate(Customer customer) throws RuntimeException {

		if(customer == null) {
			throw new RuntimeException("参数为空");
		}
		
		SqlSession sqlSession = MybatisSqlSessionUtil.getSqlSession();
		CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
		
		if(customer.getId() == null) {
			
			CustomerExample example = new CustomerExample();
			List<Customer> list = customerMapper.selectByExample(example);
			
			if(list != null && list.size() > 0) {
				throw new RuntimeException("用户名已存在");
			}
			
			// 保存
			customerMapper.insert(customer);
		} else {
			// 更新
			customerMapper.updateByPrimaryKey(customer);
		}
		sqlSession.commit();
		
	}

	@Override
	public Customer findCustomerByUsernameAndPassword(String username, String password) {

		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return null;
		}
		// 调用dao层代码去数据库查询
		SqlSession sqlSession = MybatisSqlSessionUtil.getSqlSession();
		CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
		
		// 根据条件查询    根据模板查询
		//  创建模板对象
		CustomerExample example = new CustomerExample();
		// 附带条件
		example.createCriteria().andNameEqualTo(username).andPasswordEqualTo(Integer.parseInt(password));
		
		List<Customer> list = customerMapper.selectByExample(example);
		
		if(list == null || list.size() <= 0) {
			return null;
		}
		return list.get(0);
	}


}
