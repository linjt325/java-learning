package top.linjt.java_learning.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import top.linjt.java_learning.jdbc.connection.C3P0Util;
import top.linjt.java_learning.jdbc.connection.DBCPUtil;
import top.linjt.java_learning.jdbc.connection.DBUtil;
import top.linjt.java_learning.jdbc.pojo.Account;

public class AccountDao {

	public Account get(int id) throws SQLException{
		
		Connection conn = DBUtil.getConnection();
		StringBuilder hql = new StringBuilder("select * from account_info  where 1=1 and"
				+ " id = ?");
		PreparedStatement prepareStatement = conn.prepareStatement(hql.toString());
		
		prepareStatement.setInt(1, id);
		
		ResultSet rs = prepareStatement.executeQuery();
		if(rs.next()){
			Account a = loadData(rs);
			return a;
		}else{
			return null;
		}
		
	}
	
	public void update(Account account) throws SQLException{
		Connection conn = DBUtil.getConnection();
		
		StringBuilder hql = new StringBuilder("update account_info set "
				+ " amount = ? where id = ? ");
		PreparedStatement prepareStatement = conn.prepareStatement(hql.toString());
		prepareStatement.setDouble(1, account.getAmount());
		prepareStatement.setInt(2, account.getId());
		prepareStatement.execute();
	}
	
	public Account getDbcp(int id) throws SQLException{
		
//		Connection conn = C3P0Util.newInstance().getConnection();
		Connection conn = DBCPUtil.newInstance().getConnection();
		StringBuilder hql = new StringBuilder("select * from account_info  where 1=1 and"
				+ " id = ?");
		PreparedStatement prepareStatement = conn.prepareStatement(hql.toString());
		
		prepareStatement.setInt(1, id);
		
		ResultSet rs = prepareStatement.executeQuery();
		
		if(rs.next()){
			Account a = loadData(rs);
			return a;
		}else{
			return null;
		}
		
	}
	
	public void updateDbcp(Account account) throws SQLException{
		Connection conn = DBCPUtil.newInstance().getConnection();
//		Connection conn = C3P0Util.newInstance().getConnection();
		StringBuilder hql = new StringBuilder("update account_info set "
				+ " amount = ? where id = ? ");
		PreparedStatement prepareStatement = conn.prepareStatement(hql.toString());
		prepareStatement.setDouble(1, account.getAmount());
		prepareStatement.setInt(2, account.getId());
		prepareStatement.execute();
	}
	
	private Account loadData(ResultSet rs) throws SQLException{
		Account acc = new Account();
		acc.setId(rs.getInt("id"));
		acc.setAccount(rs.getString("account"));
		acc.setAmount(rs.getDouble("amount"));
		acc.setCreate_time(rs.getTimestamp("create_time"));
		return acc;
	}
}
