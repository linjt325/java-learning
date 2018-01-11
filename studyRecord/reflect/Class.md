# 反射

- [Class的详细信息获取](#getClass)
	- [Class的动态加载](#loadClass)
	- [反射获取Class方法](#getMethod)
	- [反射获取Class成员变量](#getField)
	- [反射获取Class构造函数](#getConstructor)
- [反射的操作](#operation)
	- [反射操作方法](#invokeMethod)
- [泛型](#T)
---
<h2 id="getClass">class的获取</h2>
Class可以理解为类的类型

```java
public class ClassDemo{

	public static void main(String[] args) {

		// 实例对象
		Hello demo = new Hello();

		// 任何一个类都是Class的实例对象, 有多种获取Class实例对象的方式
		// 1.通过类的一个隐含的静态成员变量class获取
		Class foo1 = Hello.class;
		// 2.通过类的实例获取
		Class foo2 = new Hello().getClass();
		// 3.
		Class foo3 = null;
		try {
			foo3 = Class.forName("top.linjt.java_learning.reflect.Hello");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 不管foo1 or foo2 都代表了ClassDemo类的类型,一个类只可能是Class类的一个实例对象,无论创建了多少实例都只有一个
		System.out.println(foo1 == foo2);//true
		System.out.println(foo1 == foo3);//true

		try {
			Hello demo1 = (Hello) foo1.newInstance();// 通过类类型创建类的实例,需要有无参构造器
			demo1.print();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}

}

class Hello {

	void print (){
		System.out.println("see");
	}
}
```


<h2 id="loadClass">class的动态加载</h2>

> Class c = Class.forName("类的全限定名");
> - 不仅仅返回了类的类型,还代表了动态加载类
> - 编译时刻加载类是静态加载类,运行时刻加载类是动态加载类

静态加载和动态加载的区别:  
- 静态加载:在编译时刻就需要加载所有**可能**使用到的类,如果任一类不存在,会报编译错误,整个程序都无法启动,尽管这个类最终**不会被用到**
- 动态加载,在运行时刻加载类,在编译时刻不会进行验证,只有当运行时加载某个不存在的类才会报运行时错误
```java
public class ClassDynamicLoading {

	public static void main(String[] args) {
		String className="PowerPoint";//Excel  Word PowerPoint
		String qualifiedName="top.linjt.java_learning.reflect.classes.impl."+className;
		try {
			Class<?> c = Class.forName(qualifiedName);
			OfficeAble office = (OfficeAble) c.newInstance();//所有要加载的类都实现OfficeAble接口,因此可以使用OfficeAble 接收实现类的实例
			office.start();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
```

<h2 id="getMethod">Class方法的反射</h2>

```java
public class ClassUtil {

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
	public static void main(String[] args) {
		int i = 1;
		ClassUtil.printClassMessage(i);
		//输出:
		//类名: java.lang.Integer
		//==========类方法=============
		//int numberOfLeadingZeros(int) 
		//int numberOfTrailingZeros(int) 
		//.....................
	}
	
}

```

<h2 id='getField'>获取Class成员变量</h2>

```java
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

	//输出
	//	=============变量start===============
	//MIN_VALUE:int
	//MAX_VALUE:int
	//TYPE:java.lang.Class
	//digits:[C
	//DigitTens:[C
	//DigitOnes:[C
	//sizeTable:[I
	//value:int
	//SIZE:int
	//serialVersionUID:long
	//$assertionsDisabled:boolean
	//=============变量end===============
```

<h2 id='getConstructor'>获取Class构造函数</h2>

```java
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
```

---
<h2 id='invokeMethod'>反射操作方法</h2>

```java
//一个用于输出内容的类
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
//测试方法
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
			method1 = c.getMethod("print", new Class[]{int.class,int.class});
			Object o1 = method1.invoke(c.newInstance(),new Object[]{10,20});
			//当需要获取的方法没有参数时,可以传入空数组,也可以不传
			method2 = c.getMethod("print");
			Object o2 = method1.invoke(c.newInstance(),new Object[]{});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
```

<h2 id ='T'>泛型:根据反射了解泛型</h2>

```java
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

```