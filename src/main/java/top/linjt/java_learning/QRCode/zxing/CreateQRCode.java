package top.linjt.java_learning.QRCode.zxing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author XxX
 *
 */
public class CreateQRCode {
	
    private static final int QRCOLOR = 0xFF000000;   //默认是黑色
    private static final int BGWHITE = 0xFFFFFFFF;   //背景颜色
    
	public static void main(String[] args) throws IOException {
		boolean withLogo=true;
		FileInputStream fis= new FileInputStream(new File("D:\\软件\\1-10000\\Konachan.com - 28 - glasses moon original school_uniform sky 112.jpg"));
		byte[] b=new byte[fis.available()];
		fis.read(b);
		String logo=Base64.encodeBase64String(b);
		String content="啊朋友你好";
		
		boolean isFile=false;
		
		int width=300;//宽高
		int height=300;
		String type="png";
		File path=new File("D:\\upload\\qrcode.png");
		
		CreateQRCode create=new CreateQRCode();
		
		if(content==null||content.equals("")){
			content="朋友 就算你没输内容,我也是不会提醒你的!";
		}
		String data=null;
		if(withLogo){
			BufferedImage image=null;
			//处理不同类型的logo输入方式 1.文件上传提供路径2. base64
			if(isFile){
				logo="D:\\upload\\verifycode\\b9w2.jpg";
				File logoFile=new File(logo);
				image=ImageIO.read(logoFile);
			}else{
				//如果是base64编码
				byte[] logoBytes=Base64.decodeBase64(logo);
				image=ImageIO.read(new ByteArrayInputStream(logoBytes));
			}
			
			data=create.getLogoedQRCode(content, path, width, height, type, image);		
			
		}else{
			 data=create.getSimpleQRCode(content,path,width,height,type);
		}
		
	}
	
	
	/**
	 * @param content
	 * @param picPath
	 * 输出路径
	 * @param width
	 * @param height
	 * @param type 图片类型 
	 * @return base64 编码的字符串
	 */
	public String getSimpleQRCode(String content,File picPath,int width,int height,String type){
		
		try {
			BitMatrix encode = createSimpleQRCode(content,width,height);
			
//			MatrixToImageWriter.writeToFile(encode, type, path);//desperate 
			
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(encode, type, baos);
			baos.flush();//输出到字节数组
			
			String imageString=Base64.encodeBase64String(baos.toByteArray());//没做过转换的字符串
			@SuppressWarnings("unused")
			String imageStringSafe= Base64.encodeBase64URLSafeString(baos.toByteArray());//会将 + / = 这些符号替换成URL不会被转码的字符 在收到数据之后再替换回来
			
			FileOutputStream out=new FileOutputStream(picPath);
			out.write(baos.toByteArray());//输出到文件
			out.flush();
			out.close();
			return imageString;
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	

	public String getLogoedQRCode(String content,File picPath,int width,int height,String type,BufferedImage logo){
		//先画出完整的二维码
		BufferedImage bi=getQRCodeBuffered(content, width, height);
		//添加logo
		addLogo_QRCode(bi,logo);
		
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		String data=null;
		try {
			ImageIO.write(bi, type, baos);
			baos.flush();
			FileOutputStream out=new FileOutputStream(picPath);
			
			data=Base64.encodeBase64String(baos.toByteArray());
			
			out.write(baos.toByteArray());
			out.flush();
			out.close();
			
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 return data;
	}
	/**
	 * @param content
	 * 编码内容
	 * @param width
	 * 二维码宽度
	 * @param height
	 * 二维码高度
	 * @return
	 * 将二维码矩阵画到bufferedImage中
	 */
	public  BufferedImage getQRCodeBuffered(String content,int width,int height){
		BitMatrix encode=null;
		BufferedImage image=null;
		try {
			encode = createSimpleQRCode(content,width,height);
			int h=encode.getHeight();
			int w=encode.getWidth();
			image=new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			for(int x=0;x<w;x++){
				for(int y=0;y<h;y++){
					image.setRGB(x, y, encode.get(x, y)? QRCOLOR:BGWHITE);
				}
			}
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return image;		
	}
	
	public void addLogo_QRCode(BufferedImage image,BufferedImage logo){

		double maxPercent=0.24;
		
		
		Graphics2D g = image.createGraphics();
		if(logo!=null){
			int logoW;
			int logoH;
			double wp_logo=logo.getWidth()/image.getWidth();
			double hp_logo=logo.getHeight()/image.getHeight();
			double max=Math.max(wp_logo, hp_logo);
			if(max>maxPercent){//保持图片比例
				if(max==wp_logo){
					logoW=(int) (image.getWidth()*maxPercent);
					logoH=(int) (logo.getHeight()*(maxPercent/max));
				}else{
					logoW=(int) (image.getWidth()*(maxPercent/max));
					logoH=(int) (logo.getHeight()*maxPercent);
				}
			}else{
				logoW=logo.getWidth();
				logoH=logo.getHeight();
			}
			
			int w=(image.getWidth()-logoW)/2;
			int h=(image.getHeight()-logoH)/2;

			g.drawImage(logo.getScaledInstance(logoW, logoH, Image.SCALE_SMOOTH), w, h, logoW, logoH, null);//尽可能减少图片失真
//			g.drawImage(logo, w, h, logoW, logoH, null);
			g.dispose();
			
			image.flush();
			
		}
		

	}
	
	/**
	 * 生成简单的二维码矩阵(核心)
	 * @param content
	 * 编码内容
	 * @param width
	 * 二维码宽度
	 * @param height
	 * 二维码高度
	 * @return
	 * @throws WriterException 
	 */
	public BitMatrix createSimpleQRCode(String content,int width,int height) throws WriterException{
		
			//定义二维码的参数
			Map<EncodeHintType,Object> hints=getDecodeHintType();
			//生成
			BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hints);
			return encode;	
	}

	
	/**
	 * 生成zxing编码参数并返回
	 * @return
	 */
	public Map<EncodeHintType, Object> getDecodeHintType(){
		//定义二维码的参数
		HashMap<EncodeHintType,Object> hints=new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET,"utf-8");//设置编码方式
		hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);//容错等级
		hints.put(EncodeHintType.MARGIN, 2);//外边距
		
//        hints.put(EncodeHintType.MAX_SIZE, 350);
//        hints.put(EncodeHintType.MIN_SIZE, 100);
		return hints;
	}
}
