package top.linjt.java_learning.mybatis.weChat.service;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import top.linjt.java_learning.entity.Page;
import top.linjt.java_learning.mybatis.weChat.dao.MessageDao;
import top.linjt.java_learning.mybatis.weChat.mapper.MessageMapper;
import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;

@Service
public class MessageService {

	@Autowired
//	@Qualifier("jdbcMessageDao")
	@Qualifier("mybatisMessageDao")
	private MessageDao messageDao;
	
	@Autowired
	private MessageMapper messageMapper;
	
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
	
	public void queryByPage(String command,String description,Page<MessageBean> page) throws SQLException{
		
		int pageSize = page.getPageSize();
		int pageNumber = page.getPageNumber()-1;
		int offset = pageSize*pageNumber;
		int count = messageMapper.count(command, description);
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("command", command);
		param.put("description", description);
		param.put("offset", offset);
		param.put("pageSize", pageSize);
		
		List<MessageBean> list = messageMapper.queryByPage(param);
		page.setRows(list);
		page.setTotal(count);
	}
	
	public void insert(MessageBean message){
		
		messageDao.insert(message);
		
	}
	
	public void insertBatch(List<MessageBean> message){
		messageDao.insertBatch(message);
	}
	
	public int delete(int[] id ){
		return	messageDao.delete(id);
		
	}
	
	public int update(MessageBean message){
		return messageDao.update(message);
	}
	public void test(){
//		insert(new MessageBean("t1", "t1", "t1"));
//		int  i = 1/ 0;
//		insert(new MessageBean("t2", "t2", "t2"));
	}
	
	
}
