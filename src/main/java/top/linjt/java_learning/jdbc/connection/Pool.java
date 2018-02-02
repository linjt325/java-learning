package top.linjt.java_learning.jdbc.connection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Pool {

//	protected volatile static Pool instance = null;
	
	public abstract Connection getConnection() throws SQLException;
}
