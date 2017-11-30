package top.linjt.java_learning.IO.byteAndChar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class ByteArrayInputStreamAndOutputStream {
	
	/**
	 * 字节数组的输入输出流
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		output.write("hello world!".getBytes());
		
		byte[] data=output.toByteArray();
		for(int i=0;i<data.length;i++){
			System.out.print((char)data[i]);
		}
		System.out.println(output.toString("UTF-8"));
		
		//====================================//
		ByteArrayInputStream input=new ByteArrayInputStream(output.toByteArray());
		byte[] bytes=new byte[10];
		int res=input.read(bytes);
		while (res!=-1){
			System.out.print(bytes);
//			bytes=new byte[10];
			res=input.read(bytes);
		}
	}
}
