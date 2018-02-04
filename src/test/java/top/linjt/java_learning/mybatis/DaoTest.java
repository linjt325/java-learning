package top.linjt.java_learning.mybatis;


import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import top.linjt.java_learning.mybatis.weChat.mapper.MessageMapper;
import top.linjt.java_learning.mybatis.weChat.service.MessageService;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DaoTest {
	
	SqlSessionFactory sqlSessionFactory = null;
	SqlSession sqlSession = null;
	private MessageService service;
	@Before
	public void  before() throws SQLException  {
		
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/mybatis/spring-dbConfig.xml");
		sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
		ComboPooledDataSource d = (ComboPooledDataSource) context.getBean("dataSource");
		service = (MessageService) context.getBean("service");
//		System.out.println(d);
//		System.out.println(d.getJdbcUrl());
		sqlSession = sqlSessionFactory.openSession();
		MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
		
	}
	@Test
	public void test() throws SQLException{
		service.test();
	}
	
}
