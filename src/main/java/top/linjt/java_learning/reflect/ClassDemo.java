package top.linjt.java_learning.reflect;

/**
 * Title: ClassDemo Description:
 * 
 * @author XxX
 * @date 2018年1月9日下午3:55:52
 * @since
 */
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
		System.out.println(foo1 == foo2);
		System.out.println(foo1 == foo3);

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