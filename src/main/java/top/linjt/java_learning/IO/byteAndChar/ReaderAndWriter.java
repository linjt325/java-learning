package top.linjt.java_learning.IO.byteAndChar;

import java.beans.Encoder;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import top.linjt.java_learning.IO.inputStreamTemplate.InputStreamProcessingTemplate;
import top.linjt.java_learning.IO.inputStreamTemplate.InputStreamProcessingTemplate_Interface;
import top.linjt.java_learning.IO.inputStreamTemplate.InputStreamProcessor;
import top.linjt.java_learning_util.exception.MyException;


public class ReaderAndWriter {
	
	/**
	 * 字符流的输入和输出 
	 * @param args
	 * @throws IOException
	 * @throws MyException
	 */
	public static void main(String[] args) throws IOException, MyException {
		FileWriter writer=null;
		writer=new FileWriter("D:\\xxx1.txt",true);
		writer.write("hello world!");
		writer.write("1");
		writer.flush();
		writer.close();
		
		FileReader reader=new FileReader("D:\\xxx.txt");
//		FileInputStream reader=new FileInputStream("D:\\xxx.txt");
		
		//确保资源关闭的方式1
		new InputStreamProcessingTemplate() {//自定义模板类,确保文件IO操作后流的关闭,并且抛出异常
			@Override
			public void doProcess(InputStream input) throws IOException {
				int data=input.read();
				while(data!=-1){
					System.out.print((char)data);
					data=input.read();
				}
			}
		}.process("D:\\xxx.txt");
		
		//确保资源关闭的方式2
		new InputStreamProcessingTemplate_Interface()
			.process("D:\\xxx.txt", new InputStreamProcessor() {
			
			@Override
			public void process(InputStream input) throws IOException {
				int data=input.read();
				while(data!=-1){
					System.out.print((char)data);
					data=input.read();
				}				
			}
		});
		
		//确保资源关闭的方式3 try-with-resource 
		try(FileReader reader_1=new FileReader("D:\\xxx.txt")){
			int data=reader_1.read();
			while(data!=-1){
				System.out.print((char)data);
				data=reader_1.read();
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("结束了! 在关闭资源之后执行");
		}
		
	}
}
