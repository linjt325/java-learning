package top.linjt.java_learning.jms.spring.producer;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ProducerServiceImpl implements ProducerService {

	@Autowired
	JmsTemplate jmsTemplate;
	@Resource(name="queueDestination")
	Destination queueDestination;
	@Resource(name="topicDestination")
	Destination topicDestination;
	@Override
	public void sendMessage(final String message) {
		
		jmsTemplate.send(topicDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
		System.out.println("发送消息 :"+ message );
	}
	

}
