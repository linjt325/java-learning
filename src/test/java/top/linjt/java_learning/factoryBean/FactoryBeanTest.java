package top.linjt.java_learning.factoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class FactoryBeanTest implements FactoryBean<BeanTest>, InitializingBean {

	
	private int i = 0;
	
	private static int j = 0;
	
	public FactoryBeanTest() {
		System.out.println("factoryBean 无参构造");
	}
	public FactoryBeanTest(int i) {
		super();
		System.out.println("factoryBean 有参构造");
		this.i = i;
	}

	@Override
	public void afterPropertiesSet() {
		System.out.println("afterPropertiesSet");
	}
	
	@Override
	public BeanTest getObject() {
		System.out.println("getObject");
		return new BeanTest();
	}
	@Override
	public Class<?> getObjectType() {
		
		return null;
	}
	@Override
	public boolean isSingleton() {
		
		return true;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		System.out.println("set---I"+i);
		this.i = i;
	}

	public static int getJ() {
		return j;
	}

	public static void setJ(int j) {
		System.out.println("set---j"+j);
		FactoryBeanTest.j = j;
	}

}
