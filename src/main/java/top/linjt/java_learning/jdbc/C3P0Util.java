package top.linjt.java_learning.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.jms.ConnectionMetaData;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util extends Pool{

	protected static ComboPooledDataSource DS = null;
	
	protected volatile static C3P0Util instance = null;
	
	protected ThreadLocal<Connection> conn = new ThreadLocal<Connection>(); 
	
	public static C3P0Util newInstance(){
		if(instance == null){
			synchronized (C3P0Util.class){
				if(instance == null){
					instance = new C3P0Util();
				}
			}
		}
		return instance;
	}
	
	private C3P0Util() {
		//初始化
		initC3p0();
		
	}
	
	private static void initC3p0(){
		try {
			DS = new ComboPooledDataSource();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Connection getConnection() throws SQLException{
		Connection connection = conn.get();
		if(connection != null && !connection.isClosed()){
			return connection;
		}
		if(DS != null){
			try {
				connection = DS.getConnection();
				//关闭连接的自动提交
				connection.setAutoCommit(false);
				//保存到threadLocal 变量
				conn.set(connection);
				return connection;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws SQLException  {
		
		Pool p = C3P0Util.newInstance();
		Connection connection = null;
		try {
			connection = p.getConnection();
			System.out.println(connection);
//			Dbutils 的QueryRunner 封装了jdbc 操作,简化了数据库操作
			
			int	i = new QueryRunner().update(connection, "insert into trans_info (source_id,source_account,destination_id,destination_account,amount,create_time) "
					+ "values(2,'小明',1,'老王',?,now())",100
					);
			if(i > 0 ){
				System.out.println("插入成功!");
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(connection!= null){
				DbUtils.close(connection);
			}
		}
		
		
		
	}
}
