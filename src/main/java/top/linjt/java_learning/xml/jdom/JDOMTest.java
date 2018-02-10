package top.linjt.java_learning.xml.jdom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import top.linjt.java_learning.xml.entity.Book;

public class JDOMTest {

	public static void main(String[] args) throws FileNotFoundException, JDOMException, IOException {
		
		parse();
	}

	public static void parse() throws JDOMException, IOException,
			FileNotFoundException {
		List<Book> bookList = new ArrayList<Book>();
		
		//创建xml接解析器
		SAXBuilder saxBuilder = new SAXBuilder();
		//通过build方法将xml文件加载到解析其中 返回一个Document 对象
		//可以将inputstream转换为inputStreamReader 指定字符集 ,解决乱码问题
		Document build = saxBuilder.build(new FileInputStream(new File("src/main/java/top/linjt/java_learning/xml/books.xml")));
		//通过Document 对象获取xml文件的根节点
		Element rootElement = build.getRootElement();
		//获取根节点下的子节点集合
		List<Element> children = rootElement.getChildren();
		
		for(Element e : children){
			//初始化book
			Book book =new Book();
			
			System.out.println("正在解析第 "+ (children.indexOf(e)+1) + "本书");
			
			book.setId(e.getAttributeValue("id"));
	//		已知属性名
	//		e.getAttributeValue("id");
			//解析子节点的属性
			List<Attribute> attrs = e.getAttributes();
			//遍历属性
			for (Attribute attribute : attrs) {
				System.out.println("属性名: " + attribute.getName());
				System.out.println("属性值: " + attribute.getValue());
			}
			
			//遍历子节点
			List<Element> eChildren = e.getChildren();
			for (Element element : eChildren) {
				System.out.print(element.getName() + " : ");
				System.out.println( element.getValue());
				if(element.getName().equals("name")){
					book.setName(element.getValue());
				}else if(element.getName().equals("author")){
					book.setAuthor(element.getValue());
				}else if(element.getName().equals("year")){
					book.setYear(element.getValue());
				}else if(element.getName().equals("price")){
					book.setPrice(element.getValue());
				}
				
				
			}
			bookList.add(book);
		}

		System.out.println(bookList.toString());
	}

}
