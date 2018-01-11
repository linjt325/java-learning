package top.linjt.java_learning.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


/**
* Title: Server
* Description:
* @author XxX
* @date 2018年1月5日下午3:19:23
* @since 
*/
public class Server {

	ServerSocket socketServer;
	
	Socket socket;
	
	int index=0;
	
	BufferedReader in;
	
	private static final int  DEFAULT_PORT=23333;
	
	public Server()  {
		try {
			socketServer=new ServerSocket(DEFAULT_PORT);
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public Server(int port) {
		try {
			socketServer=new ServerSocket(port);
			multiThreadListen();
//			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单线程实现 socket连接
	 * @throws IOException
	 */
	private void listen() throws IOException{
		while(true){
			socket =socketServer.accept();
			in =new BufferedReader( new InputStreamReader(socket.getInputStream(),"utf-8"));
			int data= in.read();
			while(data!=-1){
				System.out.print((char) data);
				data=in.read();
			}
			System.out.println();
		}
	}
	
	/**
	 * 多线程实现 socket连接
	 * 每次接收到客户端的请求,创建或者从线程池中获取一个线程, 主线程不参与业务逻辑的处理
	 * @throws IOException
	 */
	private void multiThreadListen() throws IOException{
		while(true){
			socket =socketServer.accept();
			index++;
			Thread work=new Thread(new ThreadHandler(socket,index));
			work.start();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server(12345);
	}
	
	
	
}
