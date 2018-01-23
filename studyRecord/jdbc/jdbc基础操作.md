- [JDBC  (java Data Base Connectivity)](#jdbc-java-data-base-connectivity)
    - [连接数据库的步骤:](#%E8%BF%9E%E6%8E%A5%E6%95%B0%E6%8D%AE%E5%BA%93%E7%9A%84%E6%AD%A5%E9%AA%A4)
# JDBC (java Data Base Connectivity)

jdbc 可以为多种数据库提供统一的访问。

## 连接数据库的步骤:
1. 导入数据库驱动程序(使用mysql作为测试数据库,注意驱动版本对jdk的要求,较高版本要求jdk8)
```
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.45</version>
    </dependency>
```
2. 代码实现
```java
public class DBUtil {
	
	private static final String URL="jdbc:mysql://127.0.0.1:3306/jdbcTest";

	private static final String USERNAME="root";
	
	private static final String PASSWORD="123";

	public static void main(String[] args) throws Exception {
		
		//1. 加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		//2. 连接数据库
		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		//3. 通过数据库的连接操作数据库,实现增删改查
		Statement statement = conn.createStatement();
		
		ResultSet result = statement.executeQuery("select * from imooc_goddess");
		
		while(result.next()){
			System.out.println(result.getString("user_name") + ":" + result.getInt("age"));
		}
	}
	
}
```


## JDBC各种连接方式对比

1. JDBC+ODBC桥的方式
> 特点:需要数据库的ODBC驱动,仅适用于微软的系统
2. JDBC+厂商API的形式:
> 厂商API一般使用C编写,
3. JDBC+厂商DataBase Connection Server +DateBase
> 特点: 在java 与database 之间架起了一台专门用于数据库连接的服务器(一般由数据库厂商提供)
4. JDBC+database的连接方式
5. > 特点:使application与数据库分开 ,开发者只需关心内部逻辑 
