package top.linjt.java_learning.jdbc.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtil extends Pool{
	
	private static DataSource DS;
	
	private volatile static DBCPUtil instance = null;
	
	private ThreadLocal<Connection> conn = new ThreadLocal<Connection>();
	
	private static String configFile= "/jdbc/dbcp.properties";//default
	
	public static DBCPUtil newInstance(){
		if(instance == null){
			synchronized (DBCPUtil.class){
				if(instance == null){
					instance = new DBCPUtil();
				}
			}
		}
		return instance;
	}
	
	public static DBCPUtil newInstance(String configFile){
		DBCPUtil.configFile = configFile;
		if(instance == null){
			synchronized (DBCPUtil.class){
				if(instance == null){
					instance = new DBCPUtil();
				}
			}
		}
		return instance;
	}
	
	private DBCPUtil() {
		//初始化
		initDbcp();
	}
	
	private static void initDbcp(){
		Properties prop = new Properties();
		try {
//			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(configFile));
			
			//读取配置文件推荐 class.getResource() 同时文件路径为classpath 下的绝对路径 以"/"开头
//			System.out.println(DBCPUtil.class.getResource("/")); 
			prop.load(DBCPUtil.class.getResourceAsStream(configFile));
			//classLoader 要用相对路径
//			System.out.println(DBCPUtil.class.getClassLoader().getResource("/"));
//			prop.load(DBCPUtil.class.getClassLoader().getResourceAsStream(configFile));
			DS = BasicDataSourceFactory.createDataSource(prop);
//			System.out.println(DS.getConnection());
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

	public static String getConfigFile() {
		return configFile;
	}

	public static void setConfigFile(String configFile) {
		DBCPUtil.configFile = configFile;
	}

}
