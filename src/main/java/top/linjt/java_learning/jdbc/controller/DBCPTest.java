package top.linjt.java_learning.jdbc.controller;

import java.sql.SQLException;

import top.linjt.java_learning.jdbc.pojo.Account;
import top.linjt.java_learning.jdbc.service.DealService;

public class DBCPTest {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		long t1 = System.currentTimeMillis();
		for(int i = 0 ; i<100; i++){
			trans();
		}
		System.out.println("普通获取连接方式用时: " + (System.currentTimeMillis() - t1));
		long t2 = System.currentTimeMillis();
		for(int i = 0 ; i<100; i++){
			transDbcp();
		}
		System.out.println("连接池获取时间方式用时: " + (System.currentTimeMillis() - t2));
	}
	
	public static void trans() throws SQLException{ 
		
		AccountController ac = new AccountController();
		DealService ds = new DealService();
		Account boss =  ac.get(1);
		Account customer = ac.get(2);
		ds.deal_withTrans(customer, boss, 10.0);
	}
	
	public static void transDbcp() throws SQLException{
		AccountController ac = new AccountController();
		DealService ds = new DealService();
		Account boss =  ac.getDbcp(1);
		Account customer = ac.getDbcp(2);
		String done = ds.deal_withTransDbcp(customer, boss, 10.0);
	}

}
