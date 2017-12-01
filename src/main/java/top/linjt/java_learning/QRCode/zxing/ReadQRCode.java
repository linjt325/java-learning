package top.linjt.java_learning.QRCode.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class ReadQRCode {

	public static void main(String[] args) {
		MultiFormatReader reader=new MultiFormatReader();
		File file =new File("D:\\upload\\qrcode.png");
		BufferedImage image;
		try {
			image = ImageIO.read(file);
			 //读取图片
			BinaryBitmap bitMap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			
			Map<DecodeHintType, Object> hints=new HashMap<DecodeHintType, Object>();
			// 解码设置编码方式为：utf-8，
			hints.put(DecodeHintType.CHARACTER_SET,"utf-8");//字符集
			//优化精度
			hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
			//复杂模式，开启PURE_BARCODE模式 
			//输入中文需要开启 否则会报 com.google.zxing.NotFoundException
			hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
			
			Result res=reader.decode(bitMap, hints);//解析
			System.out.println("解析结果:"+res.toString());
			System.out.println("二维码格式类型:"+res.getBarcodeFormat());
			System.out.println("二维码文本内容:"+res.getText());
		}catch (IOException e1) {
			e1.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
}
