package top.linjt.java_learning.mybatis.weChat.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.linjt.java_learning.mybatis.weChat.dao.MessageDao;
import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;

@Repository
public class MybatisMessageDao implements MessageDao {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Override
	public MessageBean get(int id) {
		//1. 通过sqlSessionFactory获取sqlSession;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
		} catch (Exception e) {
			
		}finally{
			//操作完之后关闭sqlSession, 此处还没有事务管理
			sqlSession.close();
		}
		return null;
	}

	@Override
	public List<MessageBean> list() {
		return null;
	}

	@Override
	public List<MessageBean> query(String command, String description)
			throws SQLException {
		return null;
	}

}
