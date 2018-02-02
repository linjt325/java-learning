package top.linjt.java_learning.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;






import top.linjt.java_learning.jdbc.connection.C3P0Util;
import top.linjt.java_learning.jdbc.connection.DBCPUtil;
import top.linjt.java_learning.jdbc.connection.DBUtil;
import top.linjt.java_learning.jdbc.pojo.TransInfo;

public class TransDao {

	public void insert(TransInfo info) throws SQLException{
		Connection conn = DBUtil.getConnection();
		StringBuilder hql = new StringBuilder("INSERT INTO trans_info "
				+ "(source_id,source_account,destination_id,destination_account,amount) values ("
				+ "?,?,?,?,?)");
		PreparedStatement prepareStatement = conn.prepareStatement(hql.toString());
		prepareStatement.setInt(1, info.getSource_id());
		prepareStatement.setString(2, info.getSource_account());
		prepareStatement.setInt(3, info.getDestination_id());
		prepareStatement.setString(4, info.getDestination_account());
		prepareStatement.setDouble(5, info.getAmount());
		prepareStatement.execute();
	}
	
	public void insertDbcp(TransInfo info) throws SQLException{
		Connection conn = DBCPUtil.newInstance().getConnection();
//		Connection conn = C3P0Util.newInstance().getConnection();
		StringBuilder hql = new StringBuilder("INSERT INTO trans_info "
				+ "(source_id,source_account,destination_id,destination_account,amount) values ("
				+ "?,?,?,?,?)");
		PreparedStatement prepareStatement = conn.prepareStatement(hql.toString());
		prepareStatement.setInt(1, info.getSource_id());
		prepareStatement.setString(2, info.getSource_account());
		prepareStatement.setInt(3, info.getDestination_id());
		prepareStatement.setString(4, info.getDestination_account());
		prepareStatement.setDouble(5, info.getAmount());
		prepareStatement.execute();
//		conn.close();
	}
	
}
