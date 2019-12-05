package com.briup.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.ex.CategoryEX;
import com.briup.estore.mapper.ex.CategoryEXMapper;
import com.briup.estore.service.ICategoryService;
import com.briup.estore.util.MybatisSqlSessionUtil;

public class CategoryServiceImpl implements ICategoryService{

	@Override
	public List<CategoryEX> findAllCategoryEX() throws RuntimeException {
		
       SqlSession sqlSession = MybatisSqlSessionUtil.getSqlSession();
       CategoryEXMapper categoryEXMapper = sqlSession.getMapper(CategoryEXMapper.class);
		
		return categoryEXMapper.selectAllCategoryEx();
	}

}
