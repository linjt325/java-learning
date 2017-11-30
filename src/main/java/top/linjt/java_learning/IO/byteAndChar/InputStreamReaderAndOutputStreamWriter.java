package top.linjt.java_learning.IO.byteAndChar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class InputStreamReaderAndOutputStreamWriter {

	/**
	 * 将字节流转换为字符流 ,可以指定字符集
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		InputStream inputStream = new FileInputStream("D:\\xxx.txt");

		Reader reader = new InputStreamReader(inputStream,"utf-8");
		int data = reader.read();

		while(data != -1){

		    char theChar = (char) data;
		    System.out.print(theChar);
		    data = reader.read();

		}

		reader.close();
	}
}
