package top.linjt.java_learning.IO.changeSystemStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ChangeSystemOut {

	/**
	 * 修改系统控制台输出
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream output = new FileOutputStream("c:\\system.out.txt");

		PrintStream printOut = new PrintStream(output);

		System.setOut(printOut);//替换系统输出流
		
		System.out.println("错误输出!");
		
		printOut.flush();//关闭前 确保内容输出到文件
		printOut.close();
	}
}
