package top.linjt.java_learning.upload.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {

	private String uploadPath;
	private String filePath;
	
	File tempPathFile;
	public FileUploadServlet() throws IOException {
		super();
		InputStream input=this.getClass().getResourceAsStream("/upload.properties");
		Properties pro=new Properties();
		pro.load(input);
		uploadPath=pro.get("filePath").toString().trim();
		File path=new File(uploadPath);
		if (!path.exists()) {
			path.mkdirs();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 protected void doPost(HttpServletRequest request,  
	            HttpServletResponse response) throws ServletException, IOException {  
	        try {  
	            // Create a factory for disk-based file items  
	            DiskFileItemFactory factory = new DiskFileItemFactory();  
	  
	            // Set factory constraints  
	            factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb  
	            factory.setRepository(tempPathFile);// 设置缓冲区目录  
	  
	            // Create a new file upload handler  
	            ServletFileUpload upload = new ServletFileUpload(factory);  
	  
	            // Set overall request size constraint  
	            upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB  
//	  
	            @SuppressWarnings("unchecked")
				List<FileItem> items = upload.parseRequest(request);// 得到所有的文件  
	            Iterator<FileItem> i = items.iterator();  
	            while (i.hasNext()) {  
	                FileItem fi = (FileItem) i.next();  
	                String fileName = fi.getName();  
	                if (fileName != null) {  
	                    File fullFile = new File(new String(fi.getName().getBytes(), "utf-8")); // 解决文件名乱码问题  
	                    File savedFile = new File(uploadPath, fullFile.getName());  
	                    fi.write(savedFile);  
	                    filePath=savedFile.getAbsolutePath();
                    }  
	            }  
	            System.out.print("upload succeed");  
	            response.getWriter().print(filePath);//将文件路径返回
	        } catch (Exception e) {  
	  
	        }  
	    }  
}
