package top.linjt.java_learning.IO.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeExample {

	public static void main(String[] args) throws IOException {
		final PipedOutputStream output=new PipedOutputStream();
		final PipedInputStream input=new PipedInputStream(output);
//		output.connect(input); //可以在构造中关联 也可以调用connect方法关联
		
		Thread thread1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					output.write("Hello World!".getBytes());
				} catch (Exception e) {
				}
			}
		});
		Thread thread2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					int data=input.read();
					while (data!=-1){
						System.out.print((char)data);
						data=input.read();
					}
				} catch (Exception e) {
				}
			}
		});
		thread1.start();
		thread2.start();
	}

}
