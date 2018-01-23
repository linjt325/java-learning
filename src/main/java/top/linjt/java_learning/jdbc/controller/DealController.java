package top.linjt.java_learning.jdbc.controller;

import java.sql.SQLException;

import top.linjt.java_learning.jdbc.pojo.Account;
import top.linjt.java_learning.jdbc.service.DealService;

public class DealController {

	public static void main(String[] args) throws SQLException {
		AccountController ac = new AccountController();
		DealService ds = new DealService();
		Account boss =  ac.get(1);
		Account customer = ac.get(2);
		String done = ds.deal_withTrans(customer, boss, 10.0);
//		String done = ds.deal_noTrans(customer, boss, 10.0);
		System.out.println(done);
	}
}
