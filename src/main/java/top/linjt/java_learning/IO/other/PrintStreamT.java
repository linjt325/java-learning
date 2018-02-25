package top.linjt.java_learning.IO.other;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class PrintStreamT {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		PrintStream output = new PrintStream(new FileOutputStream("D:\\xxx1.txt"),true,"utf-8");//指定自动flush,和输出的字符集

		output.print(true);

		output.print(123);

		output.print((float) 123.456);
		
		output.print("你好!");
		
		output.print(0x61);// print 会调用String.valueOf() 将结果 写到输出流  相当于 十进制的97 
		
		output.print(97);
		
		output.write(97);// 将值为97的字节写入输出流, 相当于输出了字符a

		output.close();

	}
}
