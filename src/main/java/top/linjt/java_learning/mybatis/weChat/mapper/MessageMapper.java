package top.linjt.java_learning.mybatis.weChat.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;

public interface MessageMapper{

		MessageBean get(int id );
		
		List<MessageBean> list();
		
		List<MessageBean> query(@Param("command")String command,@Param("description")String description) throws SQLException;
		
		void insert(MessageBean message);
		
		int delete (int[] id );
}
