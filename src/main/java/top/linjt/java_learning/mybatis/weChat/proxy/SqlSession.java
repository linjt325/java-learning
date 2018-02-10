package top.linjt.java_learning.mybatis.weChat.proxy;


public class SqlSession {
	
	
	public SqlSession() {
		super();
		
	}

	public <T> T getMapper(Class<T> clz){
		
		System.out.println("创建代理工厂类,传入接口的class");
		MapperProxyFactory<T> factory = new MapperProxyFactory<T>(clz);
		System.out.println("利用工厂类根据sqlSession创建mapper 实例");
		T mapper = factory.getMapper(this);
		
		System.out.println(mapper.getClass());
		
		return mapper;
		
	}

}
