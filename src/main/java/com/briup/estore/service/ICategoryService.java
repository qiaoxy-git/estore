package com.briup.estore.service;

import java.util.List;

import com.briup.estore.bean.ex.CategoryEX;

public interface ICategoryService {
	
	List<CategoryEX> findAllCategoryEX() throws RuntimeException;
	

}
