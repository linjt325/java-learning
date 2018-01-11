package top.linjt.java_learning.jms.spring.producer;


import java.io.File;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerController {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("conf/jms/producer.xml");
		ProducerService service=(ProducerService) context.getBean(ProducerService.class);
//		for(int i=0;i<100;i++){
//			service.sendMessage(String.valueOf(i));
//		}
			service.sendMessage(new File("D:\\软件\\1.jpg"));
		context.close();
	}
}
