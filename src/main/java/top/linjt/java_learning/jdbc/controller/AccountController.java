package top.linjt.java_learning.jdbc.controller;

import java.sql.SQLException;

import top.linjt.java_learning.jdbc.dao.AccountDao;
import top.linjt.java_learning.jdbc.pojo.Account;

public class AccountController {

	private AccountDao dao;
	
	public AccountController() {
		dao = new AccountDao();
	}
	
	public Account get(int id) throws SQLException{
		Account account = dao.get(id);
		return account;
		
	}
	
}
