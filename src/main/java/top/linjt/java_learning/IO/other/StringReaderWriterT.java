package top.linjt.java_learning.IO.other;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

public class StringReaderWriterT {

	public static void main(String[] args) throws IOException {

		Reader reader = new StringReader("input string...");

		int data = reader.read();

		while(data != -1) {

		    //do something with data...

		    System.out.print((char)data);

		    data = reader.read();

		}

		reader.close();
		//----------------------------------------------
		StringWriter writer = new StringWriter();

		//write characters to writer.
		writer.write("你好!");
		String data_out = writer.toString();
		System.out.println(data_out);
		StringBuffer dataBuffer = writer.getBuffer();
	}

}
