## 二维码
---
### 二维码的概念 
使用某种特定的集合图形按一定规律在平面分布的黑白相间的图形记录数据符号信息的图形
## 二维码的发展历史

### 二维码的分类
1. 线性堆叠式二维码
    1. 在一维码的基础上,按照需要堆积成两行或多行
    2. 种类: PDF417,Ultracode,Code49 ,Code16K
2. 矩阵式二维码
    1. 在一个矩形空间通过黑白像素在矩阵中不同分部进行编码
    2. 在举证相应元素位置上,用点出现表示1,点的不出现表示0.
    3. 种类:Data Matrix ,Maxi Code, Aztec Code ,QR Code , VeriCode
3. 邮政码

### 二维码优缺点
> 优点: 
> - 高密度编码,信息容量大 (500多汉字)
> - 编码范围广
> - 容错能力强
> - 译码可靠性高
> - 可加入加密措施
> - 成本低,易制作,持久耐用.  
---
> 缺点:
> - 二维码技术成为手机病毒,钓鱼网站传播的新渠道
> - 信息泄露

### 目前流行的三大国际标准:
PDF417: 不支持中文
DM:专利未公开,需支付专利费用
QR Code:专利公开,支持中文(具有识读速度快,数据密度大,占用空间小的优势)
> QR Code 是如本的Denso 公司与1994年研制的一种矩阵二维码符号码(Quick Response Code)  
&emsp;有四种纠错能力:  
- L级:约可纠错7%的数据码字
- M级:约可纠错15%的数据码字
- Q级:约可纠错25%的数据码字
- H级:约可纠错30%的数据码字

### jsp生成二维码的方法
- 借助第三方jar: zxing和qrcodejar
- JavaScript: jquery.qrcode.js

### zxing生成/读取二维码
- 生成二维码  
	- <p>/java-learning/src/main/java/top/linjt/java_learning/QRCode/zxing/CreateQRCode.java</p>
- 读取二维码
	- <p>/java-learning/src/main/java/top/linjt/java_learning/QRCode/zxing/ReadQRCode.java</p>
	- > **==在读取包含中文的二维码时出现了 com.google.zxing.NotFoundException的错误==**   
	- > **解决方案**: 在读取二维码时开启复杂模式    
	
### qrcode生成/读取二维码
首先 需要下载两个jar包 一个用于生成二维码 一个用于读取  

- [生成二维码jar 下载](http://swetake.com/qrcode/java/qr_java.html)
- [读取二维码jar 下载](https://zh.osdn.net/projects/qrcode/)
#### 生成二维码
```
public class CreateQRCode {

	/**
	 * TODO 使用qrcode 创建二维码
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		int version=7;
	
		Qrcode q=new Qrcode();
		q.setQrcodeErrorCorrect('M');
		q.setQrcodeEncodeMode('B');//N代表数字,A代表a-Z, B表示其他字符
		q.setQrcodeVersion(version);// qrcode 的版本  1-40
		String data="www.imooc.com";
		int pxOff=2; //偏移量  为二维码添加两个像素的偏移量  
		//版本1  21*21 
		//版本40 21+4(40-1)=177*177
		int mulit=3;
		int height=mulit*(21+4*(version-1))+2*pxOff;//将图片放大三倍
		int width=mulit*(21+4*(version-1))+2*pxOff;

		BufferedImage bufferedImage=new BufferedImage( width,height, BufferedImage.TYPE_INT_RGB);
		Graphics2D gs=bufferedImage.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.setColor(Color.black);
		gs.clearRect(0, 0, width, height);
		
		byte[] d =data.getBytes("utf-8");
		
		if (d.length>0 && d.length <120){
		    boolean[][] s = q.calQrcode(d);//将字符 计算后转换为 二维数组

		    for (int i=0;i<s.length;i++){
				for (int j=0;j<s.length;j++){
				    if (s[j][i]) {
					gs.fillRect(pxOff+j*mulit,pxOff+i*mulit,mulit,mulit); //计算偏移量和放大倍数
				    }
				}
		    }
		}
		gs.dispose();
		bufferedImage.flush();
		try {
			ImageIO.write(bufferedImage, "png", new File("D:\\upload\\qrcode.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}  

```
### 读取二维码
```
public static void main(String[] args) throws IOException {
		File file=new File("D:\\upload\\qrcode.png");
		
		BufferedImage bufferedImage=ImageIO.read(file);
		
		QRCodeDecoder codeDecoder=new QRCodeDecoder();
		byte[] b=codeDecoder.decode(new MyQRCodeImage(bufferedImage));
		String result =new String(b,Charset.forName("utf-8"));
		System.out.println(result);
		
	}
	
	
public class MyQRCodeImage implements QRCodeImage{

	BufferedImage bufferedImage;
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	public MyQRCodeImage(BufferedImage bufferedImage) {
		this.bufferedImage=bufferedImage;
				
	}
	@Override
	public int getHeight() {
		return bufferedImage.getHeight();
	}

	@Override
	public int getPixel(int arg0, int arg1) {
		return bufferedImage.getRGB(arg0, arg1);
	}

	@Override
	public int getWidth() {
		return bufferedImage.getWidth();
	}
}
```
#### ***==常见问题==*** :
> 二维码正常生成,但是读取二维码的时候报错 :   
 ```
Exception in thread "main" jp.sourceforge.qrcode.exception.DecodingFailedException: Invalid number of Finder Pattern detected
at jp.sourceforge.qrcode.QRCodeDecoder.decode(QRCodeDecoder.java:148)
at jp.sourceforge.qrcode.QRCodeDecoder.decode(QRCodeDecoder.java:70)
at top.linjt.java_learning.QRCode.QRCode.ReadQRCode.main(ReadQRCode.java:22)
 ```
 > **原因** :没有为二维码添加偏移量,从(0,0)开始画导致读取时出现解析错误  
 > **解决方案**: 为二维码添加偏移量,即为二维码添加白边 增加二维码解析成功率
 
###  js生成二维码
[jquery-qrcode的github](https://github.com/jeromeetienne/jquery-qrcode)

[使用说明](https://github.com/jeromeetienne/jquery-qrcode/blob/master/README.md)
---
肥肠简单!  
1. 引用jquery和jquery-qrcode  
2. 添加div  
3. 调用$('#div').qrcode(content) 即可生成  

---
## 中文问题
JqueryQrcode.js有一个小小的缺点，就是默认不支持中文。
这跟js的机制有关系，jquery-qrcode这个库是采用 charCodeAt() 这个方式进行编码转换的，
而这个方法默认会获取它的 Unicode 编码，一般的解码器都是采用UTF-8, ISO-8859-1等方式，
英文是没有问题，如果是中文，一般情况下Unicode是UTF-16实现，长度2位，而UTF-8编码是3位，这样二维码的编解码就不匹配了。
解决方式当然是，在二维码编码前把字符串转换成UTF-8，具体代码如下：
```
function utf16to8(str) {  
    var out, i, len, c;  
    out = "";  
    len = str.length;  
    for(i = 0; i < len; i++) {  
    c = str.charCodeAt(i);  
    if ((c >= 0x0001) && (c <= 0x007F)) {  
        out += str.charAt(i);  
    } else if (c > 0x07FF) {  
        out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));  
        out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));  
        out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));  
    } else {  
        out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));  
        out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));  
    }  
    }  
    return out;  
}
```

## 二维码扩展

1. 二维码添加logo(待深入学习)
2. 页面跳转  
    需要添加网页的完整路径(http:\\www.immoc.com)否则会被认为是文本
3. 实现二维码扫描安装手机软件
    - 苹果:访问itunes(url) 实现跳转
    - 安卓:访问接口(如:http://www.imooc.com/downloadApp)
        - (http://www.imooc.com/download/app.apk 在微信中不能下载软件,为了安全性考虑 只能下载腾讯域名下的apk)
4. 实现二维码扫描名片
    - vCard : 规范容许公开交换个人数据交换 (Personal Data Interchange PDI) 信息，在传统纸质商业名片可找到这些信息。
    - 将名片信息组装成符合vCard规范的文本即可 使用'\n'实现换行

```
BEGIN:VCARD
VERSION:2.1
N:姓;名
FN:姓名
NICKNAME:nickName
ORG:公司;部门
TITLE:职位
NOTE;ENCODING=QUOTED-PRINTABLE:=C6=E4=CB=FB
TEL;WORK;VOICE:电话1
TEL;WORK;VOICE:电话2
TEL;HOME;VOICE:电话1
TEL;HOME;VOICE:电话2
TEL;CELL;VOICE:
TEL;PAGER;VOICE:0755
TEL;WORK;FAX:传真
TEL;HOME;FAX:传真
ADR;WORK:;;单位地址;深圳;广东;433000;国家
LABEL;WORK;ENCODING=QUOTED-PRINTABLE:=B5=A5=CE=BB=B5=D8=D6=B7
=C9=EE=DB=DA
=B9=E3=B6=AB
433000
=B9=FA=BC=D2
ADR;HOME;POSTAL;PARCEL:;;街道地址;深圳;广东;433330;中国
LABEL;HOME;ENCODING=QUOTED-PRINTABLE:=BD=D6=B5=C0=B5=D8=D6=B7
=C9=EE=DB=DA
=B9=E3=B6=AB
433330
=D6=D0=B9=FA
URL:网址
URL:单位主页
EMAIL;PREF;INTERNET:邮箱地址
X-QQ:38394246  --自定义内容'X-'' 为前缀
X-ICQ:icq
X-WAB-GENDER:2
REV:20080424T195243Z
END:VCARD
```