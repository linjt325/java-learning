package top.linjt.java_learning.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Title: DBUtil Description:
 * 
 * @author XxX
 * @date 2018年1月12日上午10:21:42
 * @since
 */
public class DBUtil {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbcTest?useSSL=false";

	private static final String USERNAME = "root";

	private static final String PASSWORD = "123";

	private static Connection conn;

	static {
		try {
			// 1. 加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 连接数据库
//			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public static Connection getConnection() throws SQLException {
		if(conn == null||conn.isClosed()){
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return conn;
	}

	public static void setConnection(Connection conn) {
		DBUtil.conn = conn;
	}
	public static void main(String[] args) throws Exception {
		
		// 3. 通过数据库的连接操作数据库,实现增删改查
		Statement statement = conn.createStatement();
		
		ResultSet result = statement
				.executeQuery("select * from imooc_goddess");
		
		while (result.next()) {
			System.out.println(result.getString("user_name") + ":"
					+ result.getInt("age"));
		}
	}

}
