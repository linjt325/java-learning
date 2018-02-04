package top.linjt.java_learning.mybatis.weChat.dao;


import java.sql.SQLException;
import java.util.List;


import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;

public interface MessageDao {

	MessageBean get(int id );
	
	List<MessageBean> list();
	
	List<MessageBean> query(String command,String description) throws SQLException;
}
