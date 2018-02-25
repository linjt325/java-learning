package top.linjt.java_learning.jdbc.connection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DataSourceFactory {

	
	/**
	 * 创建数据源的静态工厂方法
	 * @param driveClass,数据源的全类名,top.linjt.java_learning.jdbc.connection.DBCPUtil
	 * @param configFile 配置文件路径 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Pool> T createDataSource(String driveClass,String... configFile) {
		try {
			Class<?> clz = Class.forName(driveClass);
			Method method;
			if(configFile != null && configFile.length>0){
				method = clz.getMethod("newInstance",String.class);
				return (T)method.invoke(null,configFile[0]);
			}else{
				method = clz.getMethod("newInstance");
				return (T)method.invoke(null);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
