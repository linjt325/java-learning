package top.linjt.java_learning.mybatis.weChat.proxy;

import java.lang.reflect.Proxy;


public class MapperProxyFactory<T>{
	
	
	private final Class<T> type ;
	
	
	
	public MapperProxyFactory(Class<T> type) {
		super();
		this.type = type;
	}


	@SuppressWarnings("unchecked")
	private T newInstance(MapperProxy<T> mapperProxy){
		System.out.println("利用Proxy创建接口对应的代理对象");
		return  (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, mapperProxy);
	}


	public   T getMapper(SqlSession sqlSession) {
		System.out.println("创建MapperProxy实例,传入sqlSession以及接口Class");
		MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession,type);
		return newInstance(mapperProxy);
	}

}
