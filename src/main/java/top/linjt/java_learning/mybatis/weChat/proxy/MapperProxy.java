package top.linjt.java_learning.mybatis.weChat.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler{
	
	private SqlSession sqlSession ;
	
	private boolean isCache;
	
	private Class<T> clazz;

	public MapperProxy(SqlSession sqlSession, Class<T> clazz) {
		super();
		this.sqlSession = sqlSession;
		this.clazz = clazz;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}


	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}


	public boolean isCache() {
		return isCache;
	}


	public void setCache(boolean isCache) {
		this.isCache = isCache;
	}


	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("sqlSession: "+sqlSession);
		System.out.println("调用方法: "+method);
		System.out.println("xml配置文件中的方法名(namespace+id):"+clazz.getName()+"."+method.getName());
		System.out.println("因为接口中没有具体实现,所以需要根据 方法名和参数找到xml中对应的配置方法,根据方法的标签以及属性等,判断由sqlSession 执行怎么样的方法");
		return null;
	}
}
