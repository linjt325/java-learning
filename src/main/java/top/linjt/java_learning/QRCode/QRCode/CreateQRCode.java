package top.linjt.java_learning.QRCode.QRCode;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;


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
