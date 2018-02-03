package top.linjt.java_learning.mybatis.weChat.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.linjt.java_learning.mybatis.weChat.dao.MessageDao;
import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;

@Repository("mybatisMessageDao")
public class MybatisMessageDao implements MessageDao {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Override
	public MessageBean get(int id) {
		//1. 通过sqlSessionFactory获取sqlSession;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MessageBean bean = sqlSession.selectOne("get" );
			System.out.println(bean);
		} catch (Exception e) {
			
		}finally{
			//操作完之后关闭sqlSession, 此处还没有事务管理
			sqlSession.close();
		}
		return null;
	}

	@Override
	public List<MessageBean> list() {
	//1. 通过sqlSessionFactory获取sqlSession;
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				List<MessageBean> bean = sqlSession.selectList("get" );
				System.out.println(bean);
				return bean;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//操作完之后关闭sqlSession, 此处还没有事务管理
				sqlSession.close();
			}
			return null;
	}

	@Override
	public List<MessageBean> query(String command, String description)
			throws SQLException {
		return null;
	}

}
