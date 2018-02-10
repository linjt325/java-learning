package top.linjt.java_learning.xml.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomTest {

	public static void main(String[] args) {
		parse();
	}

	public static void parse() throws FactoryConfigurationError {
		//创建一个 DocumentBuilderFactory 对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//创建 DocumentBuilder 对象
			DocumentBuilder db = dbf.newDocumentBuilder();
			//通过DocumentBuilder 对象的parse 方法加载对应路径下的xml文件到当前项目中
			Document document = db.parse("src/main/java/top/linjt/java_learning/xml/books.xml");
			//获取所有 book标签的节点
			NodeList list = document.getElementsByTagName("book");
			System.out.println("book 数量:" + list.getLength());
			System.out.println("=================================================");
			for(int i =0 ; i < list.getLength();i++){
				//通过item(index) 获取对应的节点 
				Node book = list.item(i);
				//获取book节点的所有属性
				NamedNodeMap attributes = book.getAttributes();
				//遍历, 在不知道有多少属性,属性名称的时候使用
				System.out.println("正在遍历 第 " + (i+1) + "本书的属性: ");
				for (int j = 0; j < attributes.getLength(); j++) {
					Node attr = attributes.item(j);
					System.out.print("属性名: " + attr.getNodeName());
					System.out.println("属性值: " + attr.getNodeValue());
					System.out.println("-------------------------------");
				}
				//知道属性名时的获取方法 1 
				System.out.println("通过attributes获取指定属性:"+attributes.getNamedItem("id"));
				System.out.println("-------------------------------");
				//知道属性名时的获取方法 2
				//node 强转为Element  .提供了直接获取属性的方法
				Element e = (Element)book;
				String id = e.getAttribute("id");
				System.out.println("通过node 直接获取属性 : id= "+ id );
				 
				System.out.println("********************************************");
				//解析子节点
				NodeList childNodes = book.getChildNodes();
				//在<book></book> 之间的内容都被认为是节点, 包括了 空格和换行符组成的节点
				System.out.println("第 " + (i+1) + "本书的子节点个数为:" + childNodes.getLength() + "个");
				for (int j = 0; j < childNodes.getLength(); j++) {
					Node item = childNodes.item(j);
//					System.out.println(item.getNodeName());
					if(item.getNodeType() != Node.TEXT_NODE){
//						item.getNodeValue() 此时item 为element 类型,直接获取值返回null ,需要获取子节点,再取值
						System.out.println(item.getNodeName() + ":" + (null==item.getFirstChild() ?"":item.getFirstChild().getNodeValue()));
					}
				}
				System.out.println("=================================================");
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
