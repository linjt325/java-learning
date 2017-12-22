package top.linjt.java_learning.jms.spring.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

public class ConsumerController {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("conf/jms/consumer.xml");
//		DefaultMessageListenerContainer listener=(DefaultMessageListenerContainer) context.getBean("jmsContainer");
//		listener.onMessage(message);
	}
}
