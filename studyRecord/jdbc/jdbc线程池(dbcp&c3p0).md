- [线程池](#%E7%BA%BF%E7%A8%8B%E6%B1%A0)
    - [dbcp](#dbcp)
    - [c3p0](#c3p0)

# 线程池


## dbcp 

1. 导入相关jar包

```xml
		<!-- dbcp连接池需要的jar包 同时会引入 commons-pool ,commons-logging -->
		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency>
```
2. 在项目根目录增加配置文件 dbcp.properties

属性:  
1. driverClassName:驱动全类名
2. url:数据库连接
3. username:用户名
4. password:密码
5. maxActive:最大活动连接数
6. maxIdle:
7. maxWait:
8. initialSize:初始连接数

3. 创建数据库连接池

```java
public class DBCPUtil {
	
	private static DataSource DS;
	
	private volatile static DBCPUtil instance = null;
	
	private ThreadLocal<Connection> conn = new ThreadLocal<Connection>();
	
	private static final String configFile= "/jdbc/dbcp.properties";
	
	public static DBCPUtil newInstance(){
		if(instance == null){
			synchronized (DBCPUtil.class){
				if(instance == null){
					instance = new DBCPUtil();
				}
			}
		}
		return instance;
	}
	
	private DBCPUtil() {
		//初始化
		initDbcp();
	}
	
	private static void initDbcp(){
		Properties prop = new Properties();
		try {
			prop.load(Object.class.getResourceAsStream(configFile));
			DS = BasicDataSourceFactory.createDataSource(prop);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn() throws SQLException{
		Connection connection = conn.get();
		if(connection != null&&!connection.isClosed()){
			return connection;
		}
		if(DS != null){
			try {
				connection = DS.getConnection();
				//关闭连接的自动提交
				connection.setAutoCommit(false);
				//保存到threadLocal 变量
				conn.set(connection);
				return connection;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
```

4. 通过连接池的getConn()方法获取连接,进行数据库操作

> 
```java

        //通过连接池获取连接
        DBCPUtil instance = DBCPUtil.newInstance();
        Connection conn = instance.getConn();
        
        //conn.preperstatement()....
        
        //归还连接到连接池
        conn.close()
```

[返回顶部](#%E7%BA%BF%E7%A8%8B%E6%B1%A0)

## c3p0

```java
public class C3P0Util extends Pool{

	protected static ComboPooledDataSource DS = null;
	
	protected volatile static C3P0Util instance = null;
	
	protected ThreadLocal<Connection> conn = new ThreadLocal<Connection>(); 
	
	public static C3P0Util newInstance(){
		if(instance == null){
			synchronized (C3P0Util.class){
				if(instance == null){
					instance = new C3P0Util();
				}
			}
		}
		return instance;
	}
	
	private C3P0Util() {
		//初始化
		initC3p0();
		
	}
	
	private static void initC3p0(){
		try {
            //创建c3p0连接池 ,需要根目录下有c3p0.properties文件
			DS = new ComboPooledDataSource();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Connection getConnection() throws SQLException{
		Connection connection = conn.get();
		if(connection != null && !connection.isClosed()){
			return connection;
		}
		if(DS != null){
			try {
				connection = DS.getConnection();
				//关闭连接的自动提交
				connection.setAutoCommit(false);
				//保存到threadLocal 变量
				conn.set(connection);
				return connection;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
```

```properties
# c3p0
c3p0.driverClass=com.mysql.jdbc.Driver
c3p0.jdbcUrl=jdbc\:mysql\://127.0.0.1\:3306/jdbcTest?useSSL=false
c3p0.user=root
c3p0.password=123

```

[返回顶部](#%E7%BA%BF%E7%A8%8B%E6%B1%A0)