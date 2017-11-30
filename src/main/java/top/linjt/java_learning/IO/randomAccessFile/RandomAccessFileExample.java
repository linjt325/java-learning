package top.linjt.java_learning.IO.randomAccessFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

public class RandomAccessFileExample {

	public static void main(String[] args) throws IOException {
		
		RandomAccessFile file=new RandomAccessFile("D:\\xxx.txt","rw");//"r", "rw", "rws", or "rwd"

		int aByte = file.read();
		System.out.println((char)aByte);
		file.write("Hello World".getBytes());
		file.close();
	}
}
