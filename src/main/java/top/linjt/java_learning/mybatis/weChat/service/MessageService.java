package top.linjt.java_learning.mybatis.weChat.service;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import top.linjt.java_learning.mybatis.weChat.dao.MessageDao;
import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;

@Service
public class MessageService {

	@Autowired
	@Qualifier("jdbcMessageDao")
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
	
}
