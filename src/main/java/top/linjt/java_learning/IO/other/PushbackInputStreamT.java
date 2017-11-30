package top.linjt.java_learning.IO.other;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PushbackInputStream;

public class PushbackInputStreamT {

	public static void main(String[] args) throws IOException {
		PushbackInputStream input = new PushbackInputStream(new FileInputStream("d:\\xxx.txt"),1);
		byte[] b=new byte[2];
		int data = input.read(b);

		input.unread(b);
		System.out.println((char)input.read(b));
		
	}

}
