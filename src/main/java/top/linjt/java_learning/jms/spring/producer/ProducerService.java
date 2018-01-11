package top.linjt.java_learning.jms.spring.producer;

import java.io.File;

public interface ProducerService {

	void sendMessage(String message);
	
	void sendMessage(File file);
}
