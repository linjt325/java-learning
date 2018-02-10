package top.linjt.java_learning.xml.dom4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import top.linjt.java_learning.xml.entity.Book;

public class DOM4JTest {

	public static void main(String[] args) throws DocumentException {
		parse();
	}

	public static void parse() throws DocumentException {
		List<Book> bookList = new ArrayList<Book>();
		//解析books.xml文件
		//创建SAXRead对象
		SAXReader reader = new SAXReader();
		//读取xml文件,返回Document 对象
		Document document = reader.read("src/main/java/top/linjt/java_learning/xml/books.xml");
		
		Element bookStore = document.getRootElement();
		
		Iterator iterator = bookStore.elementIterator();
		
		while (iterator.hasNext()) {
			Book book =new Book();
			Element e = (Element) iterator.next();
			List<Attribute> attributes = e.attributes();
			//指定属性名称
			book.setId(e.attribute("id").getValue());
			//遍历属性
			for (Attribute attr : attributes) {
				System.out.println(attr.getName()+ " : " + attr.getValue());
			}
			
			Iterator it2 = e.elementIterator();
			while (it2.hasNext()) {
				Element bookChild = (Element) it2.next();
				//输出节点名和 节点中的文本
				System.out.println(bookChild.getName() + " : " + bookChild.getStringValue());
				if(bookChild.getName().equals("name")){
					book.setName(bookChild.getStringValue());
				}else if(bookChild.getName().equals("year")){
					book.setYear(bookChild.getStringValue());
				}else if(bookChild.getName().equals("author")){
					book.setAuthor(bookChild.getStringValue());
				}else if(bookChild.getName().equals("price")){
					book.setPrice(bookChild.getStringValue());
				}
			}
			bookList.add(book);
		}
		System.out.println(bookList.toString());
	}
}
