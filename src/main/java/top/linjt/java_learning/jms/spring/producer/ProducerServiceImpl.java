package top.linjt.java_learning.jms.spring.producer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.jms.BytesMessage;
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
		
		jmsTemplate.send(queueDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
		System.out.println("发送消息 :"+ message );
	}
	@Override
	public void sendMessage(final File file) {
		jmsTemplate.send(queueDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				
				BytesMessage message=session.createBytesMessage();
				InputStream input;
				try {
					input = new FileInputStream(file);
					byte[] bytes=new byte[1024];
					while(input.read(bytes)>0){
						//每次都被覆盖了
						message.writeBytes(bytes);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return message;
			}
		});
		System.out.println("发送消息 ");
	}
	
public static void main(String[] args) throws IOException {
	DataOutputStream output = new DataOutputStream(new FileOutputStream("D:\\软件\\bin.data"));

	output.write(45);//byte data
//
//	output.writeInt(4545);//int data 
//	output.writeInt(456);//int data 
//
//	output.writeDouble(109.123);//double data  

	output.close();
	
//	DataInputStream in=new DataInputStream(new FileInputStream("D:\\软件\\bin.data"));
//	System.out.println(in.read());
//	byte[] bytes=new byte[4];
//	in.read(bytes);
//	System.out.println();
//	System.out.println(bytes);
//	System.out.println(in.readInt());
//	System.out.println(in.readDouble());
}
}
