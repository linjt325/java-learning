package top.linjt.java_learning.IO.objectStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStream {

	/**
	 * 将对象输入或者输出 , 该对象的类需要实现序列化接口
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("D:\\xxx1.txt"));

		TestClass object_out = new TestClass("XxX",18); 
		output.writeObject(object_out); //etc.

		output.close();
		
		
		ObjectInputStream input = new ObjectInputStream(new FileInputStream("D:\\xxx1.txt"));

		TestClass object = (TestClass) input.readObject(); //etc.
		System.out.println(object);
		System.out.println(object.equals(object_out));//不同了
		input.close();
	}
}
