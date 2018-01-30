package top.linjt.java_learning.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtil extends Pool{
	
	private static DataSource DS;
	
	private volatile static DBCPUtil instance = null;
	
	private ThreadLocal<Connection> conn = new ThreadLocal<Connection>();
	
	private static final String configFile= "/jdbc/dbcp.properties";
	
	public static DBCPUtil newInstance(){
		if(instance == null){
			synchronized (DBCPUtil.class){
				if(instance == null){
					instance = new DBCPUtil();
				}
			}
		}
		return (DBCPUtil) instance;
	}
	
	private DBCPUtil() {
		//初始化
		initDbcp();
	}
	
	private static void initDbcp(){
		Properties prop = new Properties();
		try {
			prop.load(Object.class.getResourceAsStream(configFile));
			DS = BasicDataSourceFactory.createDataSource(prop);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Connection getConnection() throws SQLException{
		Connection connection = conn.get();
		if(connection != null&&!connection.isClosed()){
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

}
