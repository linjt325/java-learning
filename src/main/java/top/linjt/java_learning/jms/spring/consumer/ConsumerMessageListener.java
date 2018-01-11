package top.linjt.java_learning.jms.spring.consumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		
		if(message instanceof TextMessage){
			TextMessage text=(TextMessage) message;
			try {
				System.out.println("接收消息: "+text.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}else if(message instanceof StreamMessage){
			StreamMessage stream=(StreamMessage) message;
			try {
				File file =new File("D:\\软件\\2.jpg");
				if(!file.exists()){
					file.createNewFile();
				}
				FileOutputStream output=new FileOutputStream(file);
				byte[] bytes=new byte[1024];
				while(stream.readBytes(bytes)>0){
					output.write(bytes);
				}
				output.flush();
				output.close();
			} catch (JMSException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
