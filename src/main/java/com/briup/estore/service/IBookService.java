package com.briup.estore.service;

import java.util.List;

import com.briup.estore.bean.Book;

public interface IBookService {
	
	List<Book> findAllSomeBook() throws RuntimeException;
	
	Book findBookById(int id) throws RuntimeException;

}
