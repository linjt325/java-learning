package top.linjt.java_learning.xml.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import top.linjt.java_learning.xml.entity.Book;

public class SAXParserHandler extends DefaultHandler {

	
	Book book = null;
	String value ;
	
	List<Book> bookList = new ArrayList<Book>(); 
	
	public List<Book> getBookList() {
		return bookList;
	}

	/**
	 * 标识 解析开始
	 */
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("解析开始");
	}

	/**
	 * 标识解析结束
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("解析结束");
	}
	

	/**
	 * 解析xml元素
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//调用父类方法
		super.startElement(uri, localName, qName, attributes);
		
		if(qName.equals("book")){
			//创建一个book对象;
			book = new Book();
			System.out.println("=======开始遍历一本book=============");
			//已知属性名
//			String id = attributes.getValue("id");
//			System.out.println("id: " + id );
			//遍历属性
			for( int i = 0; i< attributes.getLength() ; i++){
				System.out.println("属性  " + attributes.getQName(i) + ": " + attributes.getValue(i));
				if(attributes.getQName(i).equals("id")){
					//将属性保存到对象
					book.setId(attributes.getValue(i));
				}
			}
		}else if(!qName.equals("book")&&!qName.equals("bookstore")){
			//子节点
			System.out.print(qName+":");
		}
		
	}

	/**
	 * 用来遍历xml文件的结束标签
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		
		
		//是否针对一本书已经遍历结束
		if(qName.equals("book")){
			bookList.add(book);
			book=null;
			System.out.println("=======结束遍历一本book=============");
		}else if(qName.equals("name")){
			//将属性保存到对象
			book.setName(value);
		}else if(qName.equals("author")){
			book.setAuthor(value);
		}else if(qName.equals("year")){
			book.setYear(value);
		}else if(qName.equals("price")){
			book.setPrice(value);
		}
	}

	/**
	 * 在调用startElement 之后执行
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		String str = String.valueOf(ch, start, length).trim();
		if(!"".equals(str)){
			System.out.println(str);
		}
		//将节点中的字符保存为全局变量
		value=str;
	}
}
