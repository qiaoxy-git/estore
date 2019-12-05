package com.briup.estore.web.lisenter;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.briup.estore.bean.Book;
import com.briup.estore.service.ICategoryService;
import com.briup.estore.service.impl.BookServiceImpl;
import com.briup.estore.service.impl.CategoryServiceImpl;

public class IndexLisenter implements ServletContextListener {

    public IndexLisenter() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent sce)  { 
         // tomcat启动时调用这个方法
        ServletContext application = sce.getServletContext();
        
        ICategoryService categoryService = new CategoryServiceImpl(); 	
        
        BookServiceImpl bookService = new BookServiceImpl();
        
        application.setAttribute("categories", categoryService.findAllCategoryEX());
        application.setAttribute("books", bookService.findAllSomeBook());
    }
	
}
