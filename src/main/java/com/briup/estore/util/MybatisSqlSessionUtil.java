package com.briup.estore.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSqlSessionUtil {

	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static SqlSession getSqlSession() {
		return getSqlSession(false);
	}

	public static SqlSession getSqlSession(boolean auto) {
		return sqlSessionFactory.openSession(auto);
	}

}
