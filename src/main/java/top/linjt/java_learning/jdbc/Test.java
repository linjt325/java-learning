package top.linjt.java_learning.jdbc;

import java.sql.SQLException;

import top.linjt.java_learning.jdbc.controller.GoddessController;

public class Test {

	
	public static void main(String[] args) throws SQLException {
		
		GoddessController c = new GoddessController();
		System.out.println(c.get(2));
		
	}
	
}
