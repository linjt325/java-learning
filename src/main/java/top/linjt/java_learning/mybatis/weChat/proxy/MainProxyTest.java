package top.linjt.java_learning.mybatis.weChat.proxy;

import java.util.List;

import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;

public class MainProxyTest {

	public static void main(String[] args) {
		
		System.out.println("模拟:加载配置文件----");
		System.out.println("模拟:创建sqlSessionFactroy实例");
		System.out.println("模拟:获取SqlSession实例");
		SqlSession sqlSession = new SqlSession();
		
		System.out.println("通过SqlSession.getMapper()获取mapper的代理类");
		IMessage iMessage = sqlSession.getMapper(IMessage.class);
		
		System.out.println("直接调用接口的方法");
		List<MessageBean> list = iMessage.list();
		
	}
}
