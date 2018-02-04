# 类


## SqlSession

**作用:**
1. 向SQL语句传入参数
2. 执行SQL语句
3. 获取执行SQL语句的结果
4. 事务的控制

**如何得到SqlSession:**
1. 传统方法
    1. 通过配置文件获取数据库连接相关的信息
    2. 通过配置信息构建SqlSessionFactory
    3. 通过SqlSessionFactory获取构建SqlSession

    ```
        Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/stringlist/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
    ```
2. spring+mybatis
    1. 在spring配置文件中创建DataSource实例
    2. 将dataSource作为属性注入到org.mybatis.spring.SqlSessionFactoryBean中(实现了initializingBean接口)
    3. spring自动 调用工厂类的getObject()创建SqlSessionFactory实例
    4. 通过SqlSessionFactory.openSession()获取sqlsession实例
    
    ```xml
    <beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:c="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.1.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.1.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd">

	<!-- 初始化dbcp连接池 使用jdbc操作数据库 不是mybatis -->
	<bean id="JdbcDataSource"
		class="top.linjt.java_learning.jdbc.connection.DataSourceFactory"
		factory-method="createDataSource">
		<constructor-arg name="dirveClass"
			value="top.linjt.java_learning.jdbc.connection.DBCPUtil"></constructor-arg>
		<!-- dbcp的时候用的到 -->
		<constructor-arg name="configFile" value="/mybatis/dataSource/dbcp.properties"></constructor-arg>
	</bean>


    <!-- 	读取配置文件,使其他bean可以使用el 表达式获取值 -->
	<bean id="propertyConfigurer"
		class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="locations">
			<list>
				<value>/mybatis/dataSource/c3p0.properties</value>
			</list>
		</property>
		<property name="fileEncoding">
			<value>UTF-8</value>
		</property>
	</bean>

    <!-- c3p0连接池, 通过读取配置文件加载参数 -->
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="DriverClass" value="${c3p0.driverClass}"></property>
		<property name="jdbcUrl" value = "${c3p0.jdbcUrl}"></property>
		<property name="user" value = "${c3p0.user}" ></property>
		<property name="password" value = "${c3p0.password}"></property>
	</bean>

	<!-- 需要在容器的配置文件中配置JDNI content.xml server.xml 等  
	JDNI 对象会在服务器启动时初始化 当工厂类 org.springframework.jndi.JndiObjectFactoryBean 
		初始化时会调用 接口:InitializingBean 中的afterPropertiesSet方法初始化实例,
		然后通过调用 factroyBean 的 getObject() 获取到jndi的实例  -->
     	<bean id="commonDataSource" class="org.springframework.jndi.JndiObjectFactoryBean"> 
    		<property name="jndiName"> 
    			<value>java:comp/env/weChat</value> 
    		</property> 
     	</bean> 

	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<!--<property name="dataSource" ref="commonDataSource" />-->
	</bean>

</beans>
    ```

JDNI配置
```xml
<Resource name="weChat" 
type="javax.sql.DataSource" 
auth="Container"
username="root" 
password="123" 
driverClassName="com.mysql.jdbc.Driver"
url="jdbc:mysql://localhost:3306/weChat"
maxIdle="40" maxWait="4000" maxActive="250"
factory="org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory" />
```

## sqlSession的使用

```java

```