package top.linjt.java_learning.QRCode.QRCode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

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
