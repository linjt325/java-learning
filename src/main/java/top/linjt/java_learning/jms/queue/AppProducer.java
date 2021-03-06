package top.linjt.java_learning.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppProducer {

	private static final String url="tcp://10.10.28.222:61616";
	private static final String queueName="queue-test";
	
	
	public static void main(String[] args) throws JMSException {
		
		//1. 创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);
		//2. 创建连接
		Connection conn=connectionFactory.createConnection();
		//3. 启动连接
		conn.start();
		//4. 创建会话
		Session session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5. 创建目标
		Destination dest=session.createQueue(queueName);
		//6. 创建一个生产者
		MessageProducer producer=session.createProducer(dest);
		for(int i=0;i<100;i++){
			//7. 创建消息 
			TextMessage message=session.createTextMessage("test" +i);
			producer.send(message);
			System.out.println("发送消息: " +message.getText());
		}
		
		conn.close();
		
	}
}
