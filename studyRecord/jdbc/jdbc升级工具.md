	- [Dbutils](#dbutils)
	- [Hibernate](#hibernate)
	- [mybatis](#mybatis)

## Dbutils

```xml
<!-- 	jdbc封装 -->
	<dependency>
	  <groupId>commons-dbutils</groupId>
	  <artifactId>commons-dbutils</artifactId>
	  <version>1.7</version>
	</dependency>
```

```java
	public static void main(String[] args) throws SQLException  {
		
		Pool p = C3P0Util.newInstance();
		Connection connection = null;
		try {
			connection = p.getConnection();
			System.out.println(connection);
//			Dbutils 的QueryRunner 封装了jdbc 操作,简化了数据库操作
			
			int	i = new QueryRunner().update(connection, "insert into trans_info (source_id,source_account,destination_id,destination_account,amount,create_time) "
					+ "values(2,'小明',1,'老王',?,now())",100
					);
			if(i > 0 ){
				System.out.println("插入成功!");
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(connection!= null){
				DbUtils.close(connection);
			}
		}

```

## Hibernate

## mybatis

特点:
	1. 易于上手和掌握
	2. sql写在xml中,便于统一管理和优化
	3. 接触sql与程序代码的耦合
	4. 提供映射标签,支持对象与数据库的orm字段映射
	5. 提供对象关系映射标签,支持对象关系组件维护
	6. 提供xml标签,支持编写动态sql