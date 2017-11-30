package top.linjt.java_learning.IO.objectStream;

import java.io.Serializable;

public class TestClass implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2002155912067890678L;

	String name;
	
	Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public TestClass(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public TestClass() {
		super();
	}

	@Override
	public String toString() {
		return "TestClass [name=" + name + ", age=" + age + "]";
	}
	

}
