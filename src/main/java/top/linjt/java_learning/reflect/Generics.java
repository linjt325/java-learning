package top.linjt.java_learning.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 反射了解泛型
* Title: Generics
* Description:
* @author XxX
* @date 2018年1月10日上午10:51:03
* @since 
*/
public class Generics {

	
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("hello");
//		编译报错
//		list1.add(1);
		
		System.out.println(list.getClass()==list1.getClass());
		/*list和list1的类型相同,说明编译之后的集合的泛型都是不存在的;
		 * 集合中的泛型是用于防止错误输入,只在编译阶段有效;
		 * 验证:如果通过方法反射来进行操作,泛型不会报错
		 */
		try {
			Method m = list1.getClass().getMethod("add", Object.class);
			//通过在泛型为String的集合中加入数值,没有编译报错
			m.invoke(list1, 123);
			//成功在泛型为String 的List中加入Integer类型数值
			System.out.println(list.size());
			System.out.println(list1);//
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//经过反射插入不符合泛型的值后,不能通过forEach进行遍历
		//java.lang.Integer cannot be cast to java.lang.String
		/*
		 for (String string : list1) {
		 
		}*/
		
	}
}
