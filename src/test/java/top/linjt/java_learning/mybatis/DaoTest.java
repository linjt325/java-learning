package top.linjt.java_learning.mybatis;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DaoTest {
	
	SqlSessionFactory sqlSessionFactory = null;
	SqlSession sqlSession = null;
	@Before
	public void  before() throws SQLException  {
		
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/mybatis/spring-dbConfig.xml");
		sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
		ComboPooledDataSource d = (ComboPooledDataSource) context.getBean("dataSource");
		System.out.println(d);
		System.out.println(d.getJdbcUrl());
		sqlSession = sqlSessionFactory.openSession();
//		try {
//			System.out.println(sqlSession.getConnection().getSchema());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
	@Test
	public void test() throws SQLException{
//		System.out.println(sqlSession);
//		System.out.println(sqlSession.getConnection().getSchema());
	}
	
}
