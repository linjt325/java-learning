package top.linjt.java_learning.mybatis.weChat.service;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.linjt.java_learning.mybatis.weChat.dao.MessageDao;
import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;

@Service
public class MessageService {

	@Autowired
//	@Qualifier("jdbcMessageDao")
	@Qualifier("mybatisMessageDao")
	private MessageDao messageDao;
	
	public MessageBean get(int id ){
		return messageDao.get(id);
	}
	
	public List<MessageBean> list(){
		return messageDao.list();
	}
	
	public List<MessageBean> query(String command,String description) throws SQLException{
		if((command == null || command.equals("")) && (description==null ||description.equals("")) ){
			return this.list();
		}
		return messageDao.query(command,description);
	}
	
	public void insert(MessageBean message){
		
		messageDao.insert(message);
		
	}
	
	public int delete(int[] id ){
		return	messageDao.delete(id);
		
	}
	public void test(){
		insert(new MessageBean("t1", "t1", "t1"));
		int  i = 1/ 0;
		insert(new MessageBean("t2", "t2", "t2"));
	}
	
	
}
