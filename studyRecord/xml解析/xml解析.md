

## DOM 方式解析

常用的节点类型
节点类型|NodeType|Named Cibstabt| nodeName的返回值|nodeValue的返回值
---|---|---|---|---
Element|1|ELEMENT_NODE| element name | null
attr |2 | ATTRIBUTE_NODE|属性名称|属性值
text|3 |TEXT_NODE|#text|节点内容

```java
public class DomTest {

	public static void main(String[] args) {
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
```

## SAX 方式解析xml

1. 解析

2. 将解析的结果保存到java对象

```java
public class SAXTest {

	public static void main(String[] args) {
		
		//创建SAX解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			//实例化SAX解析器
			SAXParser parser = factory.newSAXParser();
			//实例化自定义的 ParserHandler
			SAXParserHandler handler = new SAXParserHandler();
			//解析
			parser.parse("src/main/java/top/linjt/java_learning/xml/books.xml" , handler);
			List<Book> bookList = handler.getBookList();
			System.out.println(bookList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}


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
```

## JDOM

maven
```xml
    <dependency>
        <groupId>org.jdom</groupId>
        <artifactId>jdom</artifactId>
        <version>2.0.2</version>
    </dependency>
```
```java
public class JDOMTest {

	public static void main(String[] args) throws FileNotFoundException, JDOMException, IOException {
		
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
```

## DOM4J

maven
```xml
		<!--dom4j -->
		<dependency>
			<groupId>dom4j</groupId>
			<artifactId>dom4j</artifactId>
			<version>1.6.1</version>
		</dependency>
```

```java
public class DOM4JTest {

	public static void main(String[] args) throws DocumentException {
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
```

# 四种解析方式PK

1. 基础方法: (DOM(平台无关的官方结息方式),SAX(基于时间驱动的结息方式))
2. 扩展方式:(JDOM,DOM4J): 是在基础方法上扩展出的,只有java能够使用

## 分析:

DOM: 
> 优点:   
    > 1. 形成了树结构,容易理解,代码容易编写
    > 2. 解析过程树结构整个保留在内存中没方便修改  
>     
> 缺点:
    > 1. 当xml文件较大时,对内存耗费比较大,容易影响解析性能并造成内存溢出

SAX:
> 优点:   
    > 1. 采用事件驱动模式,对内存耗费较小
    > 2. 适用于只需要处理xml中数据时
> 
> 缺点: 
    > 1. 不易编码
    > 很难同时访问同一个xml中的多出不同数据

JDOM
> 1. 仅适用集体类而不是用接口
> 2. API 大量适用了Collections 类

DOM4J
> 1. JDOM的一种智能分支,他合并了许多超出基本xml文档表示的功能
> 2. DOM4J适用接口和抽象基本类方法,是一个优秀的JavaXML API
> 3. 具有性能优异,灵活性好, 功能强大,和极端易用的特点
> 4. 开源

性能比较:
- DOM用时: 28
- SAX用时: 7
- JDOM用时: 47
- DOM4J用时: 54