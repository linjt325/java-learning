package top.linjt.java_learning.mybatis.weChat.interceptor;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * 分页拦截器
* Title: PageInterceptor
* Description:
* @author XxX
* @date 2018年2月8日下午4:05:37
* @since 
*/
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class , Integer.class})})
public class PageInterceptor implements Interceptor{
	
	String test ;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		//反射获取对象, 使其可以操作一些正常访问不到的属性 ,如protected
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
		
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		//xml映射语句的id
		String id = mappedStatement.getId();
		//如果以ByPage结尾
		if(id.matches(".+ByPage$")){
			//获取sql语句
			String sql = statementHandler.getBoundSql().getSql();
			ParameterHandler parameterHandler = statementHandler.getParameterHandler();
			Map<?,?> param = (Map<?, ?>) parameterHandler.getParameterObject();
			
			String pageSql = sql +" limit "+param.get("offset") + "," + param.get("pageSize");
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		//让原本被拦截的方法继续执行
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		
		//如果在mybatis 配置文件的plugins 中配置了 属性 ,可以在该方法中将属性赋值给test
//		this.test = properties.getProperty("test");
		
	}

}
