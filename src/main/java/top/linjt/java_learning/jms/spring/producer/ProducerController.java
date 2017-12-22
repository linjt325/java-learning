package top.linjt.java_learning.jms.spring.producer;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerController {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("conf/jms/producer.xml");
		ProducerService service=(ProducerService) context.getBean(ProducerService.class);
		for(int i=0;i<100;i++){
			service.sendMessage(String.valueOf(i));
		}
		context.close();
	}
}
