package top.linjt.java_learning.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import top.linjt.java_learning.jdbc.connection.C3P0Util;
import top.linjt.java_learning.jdbc.connection.DBCPUtil;
import top.linjt.java_learning.jdbc.connection.DBUtil;
import top.linjt.java_learning.jdbc.dao.AccountDao;
import top.linjt.java_learning.jdbc.dao.TransDao;
import top.linjt.java_learning.jdbc.pojo.Account;
import top.linjt.java_learning.jdbc.pojo.TransInfo;

public class DealService {
	private TransDao transDao;
	private AccountDao accountDao;
	
	public DealService() {
		transDao = new TransDao();
		accountDao = new AccountDao();
	}

	/**
	 * 没有事务管理的交易处理方法
	 * @param from
	 * @param to
	 * @param amount
	 * @return
	 * @throws SQLException
	 */
	public String deal_noTrans(Account from , Account to ,Double amount) throws SQLException{
		
		TransInfo trans = new TransInfo();
		from.setAmount(from.getAmount()-amount);
		accountDao.update(from);
		to.setAmount(to.getAmount()+amount);
		
		//此处会报错 ,如果没有进行事务管理, 客户的钱被扣除而老板的钱却没有增加导致数据不一致
//		int i = 1/0;
		
		accountDao.update(to);
		
		trans.setAmount(amount);
		trans.setSource_id(from.getId());
		trans.setSource_account(from.getAccount());
		trans.setDestination_id(to.getId());
		trans.setDestination_account(to.getAccount());
		transDao.insert(trans);
		
		return "success";
	}
	
	
	/**
	 * 进行事务管理的交易处理方法
	 * @param from
	 * @param to
	 * @param amount
	 * @return
	 * @throws SQLException
	 */
	public String deal_withTrans(Account from , Account to ,Double amount) throws SQLException{
		Connection conn = DBUtil.getConnection();
		//关闭数据库操作的自动提交
		conn.setAutoCommit(false);
		try {
			
			TransInfo trans = new TransInfo();
			from.setAmount(from.getAmount()-amount);
			accountDao.update(from);
			to.setAmount(to.getAmount()+amount);
			
			//此处会报错 ,如果没有进行事务管理, 客户的钱被扣除而老板的钱却没有增加导致数据不一致
	//		int i = 1/0;
			
			accountDao.update(to);
			
			trans.setAmount(amount);
			trans.setSource_id(from.getId());
			trans.setSource_account(from.getAccount());
			trans.setDestination_id(to.getId());
			trans.setDestination_account(to.getAccount());
			transDao.insert(trans);
			//手动提交
			conn.commit();
			conn.close();
			return "success";
		} catch (Exception e) {
			//报错回滚
			conn.rollback();
			e.printStackTrace();
			return "failed";
			
		}
	}
	
	/**
	 * 进行事务管理的交易处理方法,使用连接池方式获取连接
	 * @param from
	 * @param to
	 * @param amount
	 * @return
	 * @throws SQLException
	 */
	public String deal_withTransDbcp(Account from , Account to ,Double amount) throws SQLException{
		Connection conn = DBCPUtil.newInstance().getConnection();
//		Connection conn = C3P0Util.newInstance().getConnection();
		try {
			TransInfo trans = new TransInfo();
			from.setAmount(from.getAmount()-amount);
			accountDao.updateDbcp(from);
			to.setAmount(to.getAmount()+amount);
			
			//此处会报错 ,如果没有进行事务管理, 客户的钱被扣除而老板的钱却没有增加导致数据不一致
//			int i = 1/0;
			
			accountDao.updateDbcp(to);
			
			trans.setAmount(amount);
			trans.setSource_id(from.getId());
			trans.setSource_account(from.getAccount());
			trans.setDestination_id(to.getId());
			trans.setDestination_account(to.getAccount());
			transDao.insertDbcp(trans);
			//手动提交
			conn.commit();
			conn.close();
			return "success";
		} catch (Exception e) {
			//报错回滚
			conn.rollback();
			e.printStackTrace();
			return "failed";
			
		}
	}
}
