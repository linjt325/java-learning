package top.linjt.java_learning.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;


/**
* Title: ClassInfoUtil
* Description:
* @author XxX
* @date 2018年1月9日下午5:18:46
* @since 
*/
public class ClassInfoUtil {

	public static void printClassMessage(Object obj){
		//1.获取类的信息, 首先获取类的类型.
		Class c= obj.getClass();
		//2. 获取类的名称
		String className = c.getName();
		System.out.println("类名: " + className);
		System.out.println("==========类方法=============");
		/*
		 * Method 类,方法对象
		 * 一个成员方法就是一个Method对象
		 * 
		 *  getMethods() 方法获取所有的public的方法,包括从父类中继承而来的
		 *	getDeclaredMethods(),获取该类自己声明的所有方法,包括所有访问权限,不包括父类的方法  
		 */
		Method[] methods = c.getMethods();
		for(int i=0; i<methods.length;i++){
			//得到方法的返回值的类型
			Class returnType = methods[i].getReturnType();
			System.out.print(returnType.getName());
			System.out.print(" " + methods[i].getName() + "(");
			Class[] params = methods[i].getParameterTypes();
			for(int j = 0;j < params.length;j++){
				System.out.print(params[j].getName());
				if(j != params.length-1){
					System.out.print(",");
				}
			}
			System.out.print(") ");
			Class[] exceptions = (Class[]) methods[i].getGenericExceptionTypes();
			if(exceptions.length>0){
				System.out.print(" throws " );
			}
			for(int k = 0; k<exceptions.length;k++){
				System.out.print(exceptions[k].getName());
				if(k != exceptions.length-1){
					System.out.print(",");
				}
			}
			System.out.println();
		}
		System.out.println("==========类方法END=============");
		
		
	}
	
	/**
	 * 成员变量也是对象,
	 * java.lang.reflect.Field
	 * Field 封装了关于成员变量的操作
	 * getFields() 方法获取的是所有的public的成员变量的信息
	 * getDeclaredFields 获取该类自己声明的成员变量的信息
	 */
	public static void printFieldMessage(Object obj) {
		Class c = obj.getClass();
		System.out.println("=============变量start===============");
		Field[] fields = c.getDeclaredFields();
		for( Field field : fields){
			Class fieldType = field.getType();
			String typeName = fieldType.getName();
			System.out.println(field.getName() + ":" + typeName);
		}
		
		System.out.println("=============变量end===============");
	}
	
	/**
	 * 构造函数也是对象
	 * java.lang.Constructor 中封装了构造函数的信息
	 * getConstructors() 获取所有的public的构造函数
	 * getDeclaredConstructors() 得到所有的构造函数
	 *  
	 */
	public static void printConstructorMessage(Object obj){
		Class c = obj.getClass();
		Constructor[] constructors  = c.getDeclaredConstructors();
		System.out.println("=============构造start===============");
		for(Constructor constructor : constructors){
			System.out.print(" " + constructor.getName() + "(");
			Class[] params = constructor.getParameterTypes();
			for(int j = 0;j < params.length;j++){
				System.out.print(params[j].getName());
				if(j != params.length-1){
					System.out.print(",");
				}
			}
			System.out.println(") ");
		}
		System.out.println("=============构造end===============");
	}
	
	public static void main(String[] args) {
		int i = 1;
		printClassMessage(i);
		printFieldMessage(i);
		printConstructorMessage("1");
		Class c = int.class;
		Class c1 = void.class;
		Class c2 = Type.class;
	}
	
}
