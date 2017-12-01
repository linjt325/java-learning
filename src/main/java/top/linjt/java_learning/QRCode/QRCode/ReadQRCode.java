package top.linjt.java_learning.QRCode.QRCode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;



public class ReadQRCode {

	public static void main(String[] args) throws IOException {
		File file=new File("D:\\upload\\qrcode.png");
		
		BufferedImage bufferedImage=ImageIO.read(file);
		
		QRCodeDecoder codeDecoder=new QRCodeDecoder();
		byte[] b=codeDecoder.decode(new MyQRCodeImage(bufferedImage));
		String result =new String(b,Charset.forName("utf-8"));
		System.out.println(result);
		
	}
}
