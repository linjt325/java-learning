package top.linjt.java_learning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import top.linjt.java_learning.IO.inputStreamTemplate.InputStreamProcessingTemplate;
import top.linjt.java_learning_util.exception.MyException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	public static void main(String[] args) throws MyException {

		int sum =0;
		int max =100000;
	
		long t1 = System.currentTimeMillis();
				
		
		for (int i = 0; i < max; i++) {

		sum+=i;
	}
		long t2 = System.currentTimeMillis();
	sum = (1+max)*max/2;

	long t3 = System.currentTimeMillis();
		System.out.println(t2-t1);
		
		System.out.println(t3-t2);
	}
}
