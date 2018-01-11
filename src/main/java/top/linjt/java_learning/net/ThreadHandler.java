package top.linjt.java_learning.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadHandler implements Runnable {

	private Socket socket;
	
	private int index;
	
	public Socket getSocket() {
		return socket;
	}


	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	private ThreadLocal<String> ss=new ThreadLocal<String>(){
		@Override
		protected String initialValue() {
			return "初始值";
		}
	};
	
	
	public ThreadHandler() {
		super();
	}


	public ThreadHandler(Socket socket,int index) {
		super();
		this.index=index;
		this.socket = socket;
	}


	@Override
	public void run() {
		synchronized (this) {
		try {
			Thread.sleep(1000);
//			System.out.println("socket连接: "+ socket.getPort());
			BufferedReader input =new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String data= input.readLine();
			while(data != null){
				System.out.println(data+"-"+index);
				data = input.readLine();
			}
				
				System.out.println(Thread.currentThread().getName()+"-"+index);
				if(index%2 ==0){
					ss.set("偶数!");
				}
				System.out.println("ss: "+ss.get()+"-"+index);
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		}
	}

}
