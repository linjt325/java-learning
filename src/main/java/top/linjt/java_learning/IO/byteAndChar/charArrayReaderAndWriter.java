package top.linjt.java_learning.IO.byteAndChar;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class charArrayReaderAndWriter {
	
	/**
	 * 字符数组输入输出
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		CharArrayWriter output=new CharArrayWriter();
		output.write("hello world!");
		
		char[] data=output.toCharArray();
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]);
		}
		System.out.println(output.toString());
		FileWriter fileOut=new FileWriter("D:\\xxx2.txt");
		output.writeTo(fileOut);//将字符数组流输出到其他流中
		fileOut.flush();
		fileOut.close();
		//====================================//
		CharArrayReader input=new CharArrayReader(output.toCharArray());
		char[] chars=new char[10];
		int res=input.read(chars);
		while (res!=-1){
			System.out.print(chars);
			chars=new char[10];
			 res=input.read(chars);
		}
	}
}
