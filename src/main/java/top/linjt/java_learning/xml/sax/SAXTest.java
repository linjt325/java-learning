package top.linjt.java_learning.xml.sax;

import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import top.linjt.java_learning.xml.entity.Book;

public class SAXTest {

	public static void main(String[] args) {
		
		parse(); 
	}

	public static void parse() throws FactoryConfigurationError {
		//创建SAX解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
				try {
			//实例化SAX解析器
			SAXParser parser = factory.newSAXParser();
			//实例化自定义的 ParserHandler
			SAXParserHandler handler = new SAXParserHandler();
//			System.out.println(System.getProperty("user.dir"));
			//解析
			parser.parse("src/main/java/top/linjt/java_learning/xml/books.xml" , handler);
			List<Book> bookList = handler.getBookList();
			System.out.println(bookList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
