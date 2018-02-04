package top.linjt.java_learning.mybatis.weChat.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.linjt.java_learning.mybatis.weChat.dao.MessageDao;
import top.linjt.java_learning.mybatis.weChat.mapper.MessageMapper;
import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;

@Repository("mybatisMessageDao")
public class MybatisMessageDao implements MessageDao {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	@Autowired
	private MessageMapper messageMapper;
	
	
	
	@Override
	public MessageBean get(int id) {
		//1. 通过sqlSessionFactory获取sqlSession;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MessageBean bean = sqlSession.selectOne("get",id);
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
	public List<MessageBean> list() {
	//1. 通过sqlSessionFactory获取sqlSession;
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				List<MessageBean> bean = sqlSession.selectList("list" );
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
		System.out.println(messageMapper);
		//1. 通过sqlSessionFactory获取sqlSession;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			//使用spring 启动时创建mapper 并注入到对应的引用 ; 无需每次执行时调用getMapper重新创建;
//			MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
//			List<MessageBean> bean = mapper.query(command, description);
			
			List<MessageBean> bean = messageMapper.query(command, description);
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

}
