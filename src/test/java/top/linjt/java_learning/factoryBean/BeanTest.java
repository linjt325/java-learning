package top.linjt.java_learning.factoryBean;

public class BeanTest {

	private int i = 0;
	
	private static int j = 0;

	
	public BeanTest() {
		super();
		System.out.println("bean 无参构造");
	}
	public BeanTest(int i) {
		super();
		System.out.println("带参构造");
		this.i = i;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		System.out.println("set---I--bean");
		this.i = i;
	}

	public static int getJ() {
		return j;
	}

	public static void setJ(int j) {
		System.out.println("set---J--bean");
		BeanTest.j = j;
	}
	
	
}
