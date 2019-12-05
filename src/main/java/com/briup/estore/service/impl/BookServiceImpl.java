package com.briup.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Book;
import com.briup.estore.bean.BookExample;
import com.briup.estore.mapper.BookMapper;
import com.briup.estore.service.IBookService;
import com.briup.estore.util.MybatisSqlSessionUtil;

public class BookServiceImpl implements IBookService{

	@Override
	public List<Book> findAllSomeBook() throws RuntimeException {

		SqlSession sqlSession = MybatisSqlSessionUtil.getSqlSession();
		BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
		
		// 得到数据中所有的数据
		BookExample example = new BookExample();
		long total = bookMapper.countByExample(example );
		
		example.createCriteria().andIdGreaterThan((int)(total - 6));
		bookMapper.selectByExample(example);
		
		return bookMapper.selectByExample(example);
	}

	@Override
	public Book findBookById(int id) throws RuntimeException {

		SqlSession sqlSession = MybatisSqlSessionUtil.getSqlSession();
		BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
		
		return bookMapper.selectByPrimaryKey(id);
	}

}
