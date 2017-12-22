package top.linjt.java_learning.jms.spring.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage text=(TextMessage) message;
		try {
			System.out.println("接收消息: "+text.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
