- [调用存储过程](#%E8%B0%83%E7%94%A8%E5%AD%98%E5%82%A8%E8%BF%87%E7%A8%8B)
	- [调用无参存储过程](#%E8%B0%83%E7%94%A8%E6%97%A0%E5%8F%82%E5%AD%98%E5%82%A8%E8%BF%87%E7%A8%8B)
		- [创建存储过程:](#%E5%88%9B%E5%BB%BA%E5%AD%98%E5%82%A8%E8%BF%87%E7%A8%8B)
		- [java代码实现调用](#java%E4%BB%A3%E7%A0%81%E5%AE%9E%E7%8E%B0%E8%B0%83%E7%94%A8)
	- [调用入参存储过程](#%E8%B0%83%E7%94%A8%E5%85%A5%E5%8F%82%E5%AD%98%E5%82%A8%E8%BF%87%E7%A8%8B)
		- [创建存储过程:](#%E5%88%9B%E5%BB%BA%E5%AD%98%E5%82%A8%E8%BF%87%E7%A8%8B)
		- [java 代码调用入参存储过程](#java-%E4%BB%A3%E7%A0%81%E8%B0%83%E7%94%A8%E5%85%A5%E5%8F%82%E5%AD%98%E5%82%A8%E8%BF%87%E7%A8%8B)
	- [调用出参存储过程](#%E8%B0%83%E7%94%A8%E5%87%BA%E5%8F%82%E5%AD%98%E5%82%A8%E8%BF%87%E7%A8%8B)
		- [创建存储过程:](#%E5%88%9B%E5%BB%BA%E5%AD%98%E5%82%A8%E8%BF%87%E7%A8%8B)
		- [java代码实现](#java%E4%BB%A3%E7%A0%81%E5%AE%9E%E7%8E%B0)

# 调用存储过程

## 调用无参存储过程

### 创建存储过程:

```sql
create PROCEDURE jdbctest.sp_select_nofilter()
BEGIN 
	select * from imooc_goddess 
END ;
```

### java代码实现调用

```java
	public void queryByProcedure() throws SQLException{
//		1. 获取连接
		Connection conn = DBUtil.getConnection();
//		2. 获得 CallableStatement
		CallableStatement cs = conn.prepareCall("call sp_select_nofilter()");
//		3.执行存储过程
		cs.execute();
//		4. 处理返回的结果:a.结果集;b.出参
		ResultSet rs = cs.getResultSet();
		while(rs.next()){
			System.out.println(rs.getString("user_name") + ":" + rs.getString("email") + "-" + rs.getString("mobile"));
		}
	}
```

## 调用入参存储过程

### 创建存储过程:

```sql
create PROCEDURE jdbctest.sp_select_filter(IN sp_name varchar(45))-- in 表示入参
BEGIN 
	IF sp_name IS NULL OR sp_name = '' THEN 
		SELECT * FROM imooc_goddess;
	ELSE 
		IF length(sp_name)=11 AND substring(sp_name,1,1)=1 THEN 
			SELECT * FROM imooc_goddess WHERE mobile=sp_name;
		ELSE 
			SELECT * FROM imooc_goddess Where user_name Like concat('%',sp_name,'%');
		END IF ;
	END IF;
END ;
```

### java 代码调用入参存储过程
```java
	public List<Goddess> queryByProcedure_withfilter(String sp_name) throws SQLException{
//		1. 获取连接
		Connection conn = DBUtil.getConnection();
//		2. 获得 CallableStatement
		CallableStatement cs = conn.prepareCall("call sp_select_filter(?)");
		cs.setString(1, sp_name);
//		3.执行存储过程
		cs.execute();
//		4. 处理返回的结果:a.结果集;b.出参
		ResultSet rs = cs.getResultSet();
		List<Goddess> list = new ArrayList<>();
		while(rs.next()){
			Goddess g =	loadData(rs);
			list.add(g);
		}
		return list;
	}
```

## 调用出参存储过程

### 创建存储过程:

```sql
create PROCEDURE jdbctest.sp_select_count(OUT count int(10))-- OUT 表示出参
BEGIN 
	SELECT count(*) into count FROM imooc_goddess;
END ;
```

### java代码实现
```java
	public int queryByProcedure_count() throws SQLException{
//		1. 获取连接
		Connection conn = DBUtil.getConnection();
//		2. 获得 CallableStatement
		CallableStatement cs = conn.prepareCall("call sp_select_count(?)");
		//注册出参的类型
		cs.registerOutParameter(1, Types.INTEGER);
//		3.执行存储过程
		cs.execute();
//		4. 处理返回的结果:a.结果集;b.出参
		//采用出参的方式获取结果
		return cs.getInt(1);
	}
```