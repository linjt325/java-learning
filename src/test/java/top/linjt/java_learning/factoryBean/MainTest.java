package top.linjt.java_learning.factoryBean;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 用于测试 factory<T> 接口, 和initialize 接口 实现类 各个方法的调用顺序
 * 结果:
 * 1.  factory 构造器 
 * 2. 设置factory 属性;
 * 3. 调用initialize接口的afterPropertiesSet 方法
 * 4. 调用factory.getObject(), 该方法用于实例化 工厂类需要创建的实例
 * @author DELL
 *
 */
public class MainTest {


		@Before
		public void before() {

			@SuppressWarnings("resource")
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:config.xml");
			BeanTest bean = (BeanTest) context.getBean("beanFactory");
			// sqlSessionFactory = (SqlSessionFactory)
			// context.getBean("sqlSessionFactory");
			// ComboPooledDataSource d = (ComboPooledDataSource)
			// context.getBean("dataSource");
			System.out.println("1");
		}

		@Test
		public void test() {
		}
}
