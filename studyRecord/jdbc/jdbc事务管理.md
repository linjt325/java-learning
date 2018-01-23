# 事务

## 事务的概念
事务(transaction) 是作为**单个逻辑工作单元**执行的一系列操作.  
即: 这些操作作为一个整体一起向系统提交,要么都执行,要么都不执行.

## 事务的特点

1. 原子性(Atomicity)  :事务是一个完整的操作
2. 一致性(Consistency) :事务完成时,数据必须处于抑制状态
3. 隔离性(Isolation):  对数据进行修改的所有并发事务是彼此隔离的.
4. 永久性(Durability):事务完成后,它对数据库的修改被永久保持

## jdbc的事务管理

1. 通过**提交commit()**  或 **回退rollback()** 来管理事务的操作
2. 事务操作默认是自动提交
3. 可以通过**调用setAutoCommit(false) 来禁止自动提交**

### 不进行事务管理的代码!

```java
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
```


### 进行事务管理的代码!

```java
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
		int i = 1/0;
		
		accountDao.update(to);
		
		trans.setAmount(amount);
		trans.setSource_id(from.getId());
		trans.setSource_account(from.getAccount());
		trans.setDestination_id(to.getId());
		trans.setDestination_account(to.getAccount());
		transDao.insert(trans);
		//手动提交
		conn.commit();
		return "success";
		} catch (Exception e) {
			//报错回滚
			conn.rollback();
			e.printStackTrace();
			return "failed";
			
		}
	}
```
