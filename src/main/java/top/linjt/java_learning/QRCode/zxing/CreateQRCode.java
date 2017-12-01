package top.linjt.java_learning.QRCode.zxing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateQRCode {
	public static void main(String[] args) {
		
		int width=300;//宽高
		int height=300;
		String type="png";
		
		String content="啊朋友再见";
		
		//定义二维码的参数
		HashMap<EncodeHintType,Object> hints=new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET,"utf-8");//字符集
		hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);//容错等级
		hints.put(EncodeHintType.MARGIN, 2);//外边距
		
		try {
			BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hints);
			File path=new File("D:\\upload\\qrcode.png");
//			MatrixToImageWriter.writeToFile(encode, type, path);//desperate
			
			OutputStream out=new FileOutputStream(path);
			MatrixToImageWriter.writeToStream(encode, type, out);
			out.flush();//输出到文件
			out.close();
			
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
