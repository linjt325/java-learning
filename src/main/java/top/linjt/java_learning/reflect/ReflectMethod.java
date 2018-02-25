package top.linjt.java_learning.reflect;

import java.lang.reflect.Method;

public class ReflectMethod {

	
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public static void main(String[] args) {
		//1. 获取printUtil的类型
		Class c = printUtil.class;
		
		Method method;
		Method method1;
		Method method2;
		try {
			//2.根据方法名和参数类型获取指定的方法
			method = c.getMethod("print", String.class,String.class);
			//3.使用Method.invoke() 调用该方法 ,传入要调用该对象的实例(如果方法为静态方法,则可以传入null),和方法指定的参数,进行调用,
			Object o = method.invoke(c.newInstance(),"3", "nihao");
			
			//args 可以传入数组也可以一个一个传入
			method1 = c.getMethod("print", int.class,int.class);
			Object o1 = method1.invoke(c.newInstance(), 10,20);
			//当需要获取的方法没有参数时,可以传入空数组,也可以不传
			method2 = c.getMethod("print");
			Object o2 = method1.invoke(c.newInstance());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class printUtil{
	
	public void print(){
		System.out.println("none params");
	}
	
	public void print(int a, int b ){
		System.out.println(a + b);
	}
	
	public void print(String a, String b ){
		System.out.println(a + b.toUpperCase());
	}
}