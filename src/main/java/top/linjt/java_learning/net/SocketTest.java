package top.linjt.java_learning.net;

import java.io.IOException;

public class SocketTest {

	private static int SOCKET_PORT=12345;
	
	private static int times=10;
	
	public static void main(String[] args) {
		
		for(int i=0; i<times ;i++){
			
			try (Client client =new Client("localhost", SOCKET_PORT)){
				client.send("diudiudiu:" + i);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
