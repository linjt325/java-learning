package top.linjt.java_learning.mybatis.weChat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.linjt.java_learning.jdbc.connection.Pool;
import top.linjt.java_learning.mybatis.weChat.dao.MessageDao;
import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;


/**
 * message持久化,仅通过jdbc实现
* Title: JdbcMessageDaoImpl
* Description:
* @author XxX
* @date 2018年2月1日上午11:45:58
* @since 
*/
@Repository("jdbcMessageDao")
public class JdbcMessageDaoImpl implements MessageDao{
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(this.getClass());
	//通过普通的jdbc +连接池 实现数据查询等操作
	/*
	 * 通过spring配置 ,实现注入
	 * */
	@Autowired
	private Pool dataSource;
	
	public MessageBean get(int id ){
		
		try {
			Connection  conn = dataSource.getConnection();
			
			List<MessageBean> result = new QueryRunner().query(conn, "select * from message where id =? ",	
					new BeanListHandler<MessageBean>(MessageBean.class),id);
			
			if(result != null && result.size()>0){
				return result.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MessageBean> list() {
		try {
			Connection  conn = dataSource.getConnection();
			
			List<MessageBean> result = new QueryRunner().query(conn, "select * from message  ",	
					new BeanListHandler<MessageBean>(MessageBean.class));
			if(result != null && result.size()>0){
				for (MessageBean messageBean : result) {
					System.out.println(messageBean);
				}
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MessageBean> query(String command, String description) throws SQLException {
		StringBuilder sql = new StringBuilder("select * from message  where 1=1 ");
		List<String> paramList = new ArrayList<String>();
		if(command != null && !command.equals("") ){
			sql.append("and command = ? ");
			paramList.add(command);			
		}
		if(description != null && !description.equals("")){
			sql.append("and description like '%' ? '%' ");
			paramList.add(description);		
		}
		
		PreparedStatement prepareStatement = dataSource.getConnection().prepareStatement(sql.toString());
		for (int i = 0; i < paramList.size(); i++) {
			prepareStatement.setString(1+i, paramList.get(i));
		}
		ResultSet result = prepareStatement.executeQuery();

		List<MessageBean> resultList = loadBean(result); 
		return resultList;
	}
	
	private List<MessageBean> loadBean(ResultSet result ) throws SQLException{
		List<MessageBean> list = new ArrayList<MessageBean>();
		while (result.next()){
			MessageBean message = new MessageBean();
			list.add(message);
			
			message.setId(result.getInt("id"));
			message.setCommand(result.getString("command"));
			message.setContent(result.getString("content"));
			message.setDescription(result.getString("description"));
		}
		
			return list;
	}
}
