package top.linjt.java_learning.QRCode.zxing.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.linjt.java_learning.QRCode.zxing.CreateQRCode;

@Controller
@RequestMapping("zxing")
public class QRCodeController {

	@RequestMapping("index")
	@ResponseBody
	public ModelAndView index(){
		ModelAndView model=new ModelAndView("QRCode/zxing/createByZxing");
		return model;
	}
		
	
	@RequestMapping("getQRCode")
	@ResponseBody
	public String getQRCode(String content,Boolean withLogo,String logo,boolean isFile) throws IOException{
//		boolean isFile=false;
//		withLogo=false;
		int width=300;//宽高
		int height=300;
		String type="png";
		File path=new File("D:\\upload\\qrcode.png");//二维码存放路径
		
		CreateQRCode create=new CreateQRCode();
		
		if(content==null||content.equals("")){
			content="朋友 就算你没输内容,我也是不会提醒你的!";
		}
		String data=null;
		if(withLogo!=null&&withLogo){
			BufferedImage image=null;
			//处理不同类型的logo输入方式 1.文件上传提供路径2. base64
			if(isFile){
//				logo="D:\\upload\\verifycode\\b9w2.jpg";
				File logoFile=new File(logo);
				image=ImageIO.read(logoFile);
			}else{
				//如果是base64编码
				String[] arr=logo.split(";base64,");
				if(arr.length>1){
					byte[] logoBytes=Base64.decodeBase64(arr[1]);
					image=ImageIO.read(new ByteArrayInputStream(logoBytes));
				}
			}
			
			data=create.getLogoedQRCode(content, path, width, height, type, image);		
			
		}else{
			 data=create.getSimpleQRCode(content,path,width,height,type);
		}
		return data;
	}
}
