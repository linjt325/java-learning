package top.linjt.java_learning.reflect;

import top.linjt.java_learning.reflect.classes.OfficeAble;

/**
 * 动态加载类的demo
* Title: ClassDynamicLoading
* Description:
* @author XxX
* @date 2018年1月9日下午4:48:43
* @since 
*/
public class ClassDynamicLoading {

	public static void main(String[] args) {
		String className="PowerPoint";//Excel  Word PowerPoint
		String qualifiedName="top.linjt.java_learning.reflect.classes.impl."+className;
		try {
			//根据全类名动态加载类
			Class<?> c = Class.forName(qualifiedName);
			OfficeAble office = (OfficeAble) c.newInstance();//所有要加载的类都实现OfficeAble接口,因此可以使用OfficeAble 接收实现类的实例
			office.start();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
