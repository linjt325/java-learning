package top.linjt.java_learning.xml;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.dom4j.DocumentException;
import org.jdom2.JDOMException;
import org.junit.Test;

import top.linjt.java_learning.xml.dom.DomTest;
import top.linjt.java_learning.xml.dom4j.DOM4JTest;
import top.linjt.java_learning.xml.jdom.JDOMTest;
import top.linjt.java_learning.xml.sax.SAXTest;

public class ParseTest {

	@Test
	public void testPerformance() throws FileNotFoundException, JDOMException, IOException, DocumentException {
		
		long start = System.currentTimeMillis();
		DomTest.parse();;
		long t1 = System.currentTimeMillis()-start;
		start = System.currentTimeMillis();
		SAXTest.parse();
		long t2 = System.currentTimeMillis()-start;
		start = System.currentTimeMillis();
		JDOMTest.parse();
		long t3 = System.currentTimeMillis()-start;
		start = System.currentTimeMillis();
		DOM4JTest.parse();
		long t4 = System.currentTimeMillis()-start;
		
		System.out.println("DOM用时: " +t1);
		System.out.println("SAX用时: " +t2);
		System.out.println("JDOM用时: " +t3);
		System.out.println("DOM4J用时: " +t4);
	}
}
