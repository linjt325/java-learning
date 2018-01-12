package top.linjt.java_learning.jms.spring.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerController {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("conf/jms/consumer.xml");
	}
}
