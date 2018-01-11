package top.linjt.java_learning.net;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements AutoCloseable{

	Socket socket;
	private static final String DEFAULT_IP="localhost";
	private static final int DEFAULT_PORT=23334;
	BufferedOutputStream out;

	public Client() {
		try {
			socket=new Socket(DEFAULT_IP,DEFAULT_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Client(String ip,int port) {
		try {
			socket=new Socket(ip,port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String message) throws IOException{
		if( this.socket == null ){
			return ;
		}
		out=new BufferedOutputStream(socket.getOutputStream());
		out.write(message.getBytes("utf-8"));
		out.flush();
		out.close();
		
		
	}
	
	public void close() throws IOException{
		if(socket.isConnected()&&!socket.isClosed()){
			socket.close();
		}
	}
	
	public static void main(String[] args) {
		Client client=new Client("localhost",12345);
		try {
			client.send("你好啊\r\n 我很好");
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
